package com.ch.smartBikeBis.gui;

import com.ch.smartBikeBis.Constants;

import java.awt.geom.Point2D;

public class Place extends Site {

    private int totalSlots;

    /*------------------------------------------------------------------*\
   	|*							Constructors							*|
   	\*------------------------------------------------------------------*/

    public Place(Point2D position, String name, int totalSlots) {
        super(position, totalSlots - Constants.INITIAL_FREE_SLOTS, name);
        this.totalSlots = totalSlots;
    }

    /*------------------------------------------------------------------*\
   	|*							Public Methods 							*|
   	\*------------------------------------------------------------------*/

    public boolean isAvailableSlots() {
        return getAvailableBikes() < totalSlots;
    }

    public boolean isAvailableBikes() {
        return getAvailableBikes() > 0;
    }

    public void incrementBikes() { // synchronized?
        setAvailableBikes(getAvailableBikes() + 1);
    }

    public void decrementBikes() { // synchronized?
        setAvailableBikes(getAvailableBikes() - 1);
    }

    public void pushBike() throws InterruptedException {
        try {
            lock.lock();
            while (!isAvailableSlots()) {
                fullCond.await();
            }
            incrementBikes();
            emptyCond.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void pullBike() throws InterruptedException {
        try {
            lock.lock();
            while (!isAvailableBikes()) {
                emptyCond.await();
            }
            decrementBikes();
            fullCond.signalAll();
        } finally {
            lock.unlock();
        }
    }

    /*------------------------------*\
	|*				Getters			*|
	\*------------------------------*/

    public int getTotalSlots() {
        return totalSlots;
    }
}
