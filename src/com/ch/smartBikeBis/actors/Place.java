package com.ch.smartBikeBis.actors;

import com.ch.smartBikeBis.Default;

import java.awt.geom.Point2D;

public class Place extends Site {

    private int totalSlots;

    /*------------------------------------------------------------------*\
   	|*							Constructors							*|
   	\*------------------------------------------------------------------*/

    public Place(Point2D position, String name, int bikes) {
        super(position, name, bikes);
        this.totalSlots = Default.SLOTS;
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

    public void incrementBikes() {
        setAvailableBikes(getAvailableBikes() + 1);
    }

    public void decrementBikes() {
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
