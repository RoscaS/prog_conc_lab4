package com.ch.smartBike.thread;

import java.awt.geom.Point2D;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import com.ch.Helpers;
import com.ch.smartBike.MyCity;
import com.ch.smartBike.gui.Person;
import com.ch.smartBike.gui.Place;

public class PeopleThread extends Thread {
    private MyCity myCity;

    private boolean stillRunning;


    private Person person;
    private Place start;
    private Place dest;


    private double deltaX;
    private double deltaY;


    private ReentrantLock startLock;
    private Condition startCond;

    private ReentrantLock destLock;
    private Condition destCond;


    public PeopleThread(Person aPerson, Place aStart, Place aDest, MyCity aMyCity) {
        myCity = aMyCity;
        person = aPerson;
        start = aStart;
        dest = aDest;

        startLock = new ReentrantLock(true);
        startCond = startLock.newCondition();
        destCond = startLock.newCondition();

        // destLock = new ReentrantLock(true);

        stillRunning = true;
    }

    public void run() {
        List<Place> p = MyCity.places;

        while (true) {

            deltaX = (dest.getX() - start.getX()) / 100.0;
            deltaY = (dest.getY() - start.getY()) / 100.0;

            beggining();
            transition();
            destination();
            drawing();

            try {
                sleep(Helpers.randint(1000, 10000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            start = dest;
            dest = p.get(Helpers.randint(0, p.size() - 1, p.indexOf(start)));
        }
    }

    private void beggining() {

        startLock.lock();
        try {
            while (!start.isBikeAvailable()) {
                person.setWaitState("start", start);
                startCond.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        start.takeBike(person, dest);

        startCond.signalAll();
        startLock.unlock();
    }

    private void transition() {
        double deltaXAdd = 0;
        double deltaYAdd = 0;

        int dt = Helpers.randint(50, 100);

        for (int i = 1; i <= 100; i++) {
            deltaXAdd += deltaX;
            deltaYAdd += deltaY;

            person.setPosition(
                    new Point2D.Double(start.getX() + deltaXAdd, start.getY() + deltaYAdd)
            );
            myCity.repaint();
            try {
                sleep(dt);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void destination() {

        startLock.lock();
        try {
            while (!dest.isSlotAvailable()) {
                person.setWaitState("dest", dest);
                destCond.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        dest.leaveBike(person);

        destCond.signalAll();
        startLock.unlock();
    }


    private void drawing() {
        person.setPosition(new Point2D.Double(dest.getX(), dest.getY()));
        myCity.repaint();
        stillRunning = false;
    }

    public void printData() {
        StringBuilder sb = new StringBuilder("\nT_people: ").append(person.getName());
        sb.append("\nStart: \n");
        sb.append("\tbikes: \t").append(start.getBikeAvailable());
        sb.append("\n\tslots: \t").append(start.getFreeSlots());
        sb.append("\nDest: \n");
        sb.append("\tbikes: \t").append(dest.getBikeAvailable());
        sb.append("\n\tslots: \t").append(dest.getFreeSlots());
        System.out.println(sb.append("\n"));
    }


    public boolean stillRunning() {
        return stillRunning;
    }


}
