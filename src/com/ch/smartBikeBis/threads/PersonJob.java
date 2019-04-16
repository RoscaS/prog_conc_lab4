package com.ch.smartBikeBis.threads;

import com.ch.Helpers;
import com.ch.smartBikeBis.MyCity;
import com.ch.smartBikeBis.PersonStates;
import com.ch.smartBikeBis.gui.Person;
import com.ch.smartBikeBis.gui.Place;

import java.awt.geom.Point2D;
import java.util.List;

public class PersonJob extends Thread {

    private final int MIN_WORK_TIME = 1000;
    private final int MAX_WORK_TIME = 10000; // remonter dans settings
    private boolean debug = true;

    private MyCity city;
    private Person person;
    private Place start;
    private Place destination;

    private double deltaX;
    private double deltaY;

    /*------------------------------------------------------------------*\
	|*							Constructors							*|
	\*------------------------------------------------------------------*/

    public PersonJob(Person person, Place start, Place destination, MyCity city) {
        this.city = city;
        this.person = person;
        this.start = start;
        this.destination = destination;
    }

    @Override
    public void run() {

        while (true) {
            updateCoords();
            takeBike();
            updateDraw();
            leaveBike();
            repaint();
            simulateWorkTime();
            updatePlaces();
        }
    }

	/*------------------------------------------------------------------*\
	|*							Private Methods 						*|
	\*------------------------------------------------------------------*/

    private void updatePlaces() {
        List<Place> p = city.getPlaces();
        start = destination;
        destination = p.get(Helpers.randint(0, p.size() - 1, p.indexOf(start)));
    }

    private void takeBike() {
        if (!start.isAvailableBikes()) {
            changeState(PersonStates.WAIT);
        }
        try {
            start.pullBike();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        changeState(PersonStates.BIKE);
    }

    private void leaveBike() {
        if (!destination.isAvailableSlots()) {
            changeState(PersonStates.WAIT);
        }
        try {
            destination.pushBike();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        changeState(PersonStates.WORK);
    }

    private void simulateWorkTime() {
        try {
            sleep(Helpers.randint(MIN_WORK_TIME, MAX_WORK_TIME));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*------------------------------*\
   	|*				Debug   		*|
   	\*------------------------------*/

    private void changeState(PersonStates state) {
        person.setState(state);

        if (debug) {
            StringBuilder sb = new StringBuilder();
            sb.append(state == PersonStates.WAIT ? "\t" : "");
            sb.append(person.getName());
            sb.append("\tcurrent state: ").append(state);
            sb.append("\tjourney: [").append(start.getName());
            sb.append(" -> ").append(destination.getName());
            System.out.println(sb.append("]"));
        }
    }

    /*------------------------------*\
   	|*				Graphics		*|
   	\*------------------------------*/

    private void updateCoords() {
        deltaX = (destination.getX() - start.getX()) / 100.0;
        deltaY = (destination.getY() - start.getY()) / 100.0;
    }

    private void updateDraw() {
        double deltaXAdd = 0;
        double deltaYAdd = 0;

        int dt = Helpers.randint(20, 60);

        for (int i = 1; i <= 100; i++) {
            deltaXAdd += deltaX;
            deltaYAdd += deltaY;
            double x = start.getX() + deltaXAdd;
            double y = start.getY() + deltaYAdd;
            person.setPosition(new Point2D.Double(x, y));
            city.repaint();
            try {
                sleep(dt);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void repaint() {
        person.setPosition(new Point2D.Double(destination.getX(), destination.getY()));
        city.repaint();
    }

    /*------------------------------*\
   	|*				Getters			*|
   	\*------------------------------*/

   	/*------------------------------------------------------------------*\
   	|*							Public Methods 							*|
   	\*------------------------------------------------------------------*/

}
