package com.ch.smartBike.actors;

import com.ch.Helpers;
import com.ch.smartBike.Settings;
import com.ch.smartBike.MyCity;
import com.ch.smartBike.PersonStates;

import java.awt.geom.Point2D;
import java.util.List;

public class PersonJob extends BaseJob {

    /*------------------------------------------------------------------*\
	|*							Constructors							*|
	\*------------------------------------------------------------------*/

    public PersonJob(Person person, Place start, Place destination, MyCity city) {
        super(person, start, destination, city);
    }

    @Override
    public void run() {
        entity.setPosition(new Point2D.Double(start.getX(), start.getY()));

        while (true) {
            updateCoords();
            takeBike();
            updateDraw();
            leaveBike();
            repaint();
            simulateWorkTime();
            updateStartAndDestination();
        }
    }

	/*------------------------------------------------------------------*\
	|*							Private Methods 						*|
	\*------------------------------------------------------------------*/

    private void updateStartAndDestination() {
        List<Site> p = Settings.SITE_LIST;
        start = destination;
        destination = p.get(Helpers.randint(0, p.size() - 1, p.indexOf(start)));
    }

    private void takeBike() {
        if (!((Place)start).isAvailableBikes()) {
            changeState(PersonStates.WAIT);
        }
        try {
            start.pullBike(entity);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        changeState(PersonStates.BIKE);
    }

    private void leaveBike() {
        if (!((Place)destination).isAvailableSlots()) {
            changeState(PersonStates.WAIT);
        }
        try {
            destination.pushBike(entity);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        changeState(PersonStates.WORK);
    }

    private void simulateWorkTime() {
        int min = Settings.MIN_WORK_TIME;
        int max = Settings.MAX_WORK_TIME;
        try {
            sleep(Helpers.randint(min, max));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*------------------------------*\
   	|*				Debug   		*|
   	\*------------------------------*/

    private void changeState(PersonStates state) {
        ((Person)entity).setState(state);

        if (Settings.LOGGING) {
            StringBuilder sb = new StringBuilder();
            // sb.append(state == PersonStates.WAIT ? "\t" : "");
            sb.append(((Person)entity).getName());
            sb.append("\tcurrent state: ").append(state);
            sb.append("\tjourney: [").append(start.getName());
            sb.append(" -> ").append(destination.getName());
            sb.append("]");
            sb.append(state == PersonStates.WAIT ? "\t\t /!\\ waiting" : "");
            System.out.println(sb);
        }
    }
}
