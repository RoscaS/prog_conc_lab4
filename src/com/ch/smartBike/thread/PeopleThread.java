package com.ch.smartBike.thread;

import java.awt.geom.Point2D;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.ch.smartBike.MyCity;
import com.ch.smartBike.gui.Person;
import com.ch.smartBike.gui.Site;

public class PeopleThread extends Thread {
    private MyCity myCity;

    private boolean stillRunning;

    private Person person;
    private Site start;
    private Site dest;

    private double deltaX;
    private double deltaY;

    Lock destLock;
    Condition destCond;

    public PeopleThread(Person aPerson, Site aStart, Site aDest, MyCity aMyCity) {
        myCity = aMyCity;
        person = aPerson;
        start = aStart;
        dest = aDest;

        destLock = new ReentrantLock();
        destCond = destLock.newCondition();

        stillRunning = true;

        deltaX = (dest.getX() - start.getX()) / 100.0;
        deltaY = (dest.getY() - start.getY()) / 100.0;
    }


    public void run() {
        double deltaXAdd = 0;
        double deltaYAdd = 0;

        printData();

        person.setWaitState();

        if (start.getBikeNumber() > 0) {
            start.takeBike(person);
        }

        for (int i = 1; i <= 100; i++) {
            deltaXAdd += deltaX;
            deltaYAdd += deltaY;

            person.setPosition(
                    new Point2D.Double(start.getX() + deltaXAdd, start.getY() + deltaYAdd)
            );
            myCity.repaint();
            try {
                sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        person.setWaitState();


        destLock.lock();
        try {
            while (!dest.isFreeSlots()) {
                destCond.await();
                System.out.println(person.getName() + " locked");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            destLock.unlock();
        }


        dest.leaveBike(person);


        // if (dest.isFreeSlots()) {
        // 	dest.leaveBike(person);
        // }


        person.setPosition(new Point2D.Double(dest.getX(), dest.getY()));
        myCity.repaint();
        stillRunning = false;
    }


    public void printData() {
        StringBuilder sb = new StringBuilder("\nT_people: ").append(person.getName());
        sb.append("\nStart: \n");
        sb.append("\tbikes: \t").append(start.getBikeNumber());
        sb.append("\n\tslots: \t").append(start.getFreeSlots());
        sb.append("\nDest: \n");
        sb.append("\tbikes: \t").append(dest.getBikeNumber());
        sb.append("\n\tslots: \t").append(dest.getFreeSlots());
        System.out.println(sb.append("\n"));
    }


    public boolean stillRunning() {

        return stillRunning;
    }


}
