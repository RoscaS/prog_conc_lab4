package com.ch.smartBike.actors;

import com.ch.smartBike.Settings;

import java.awt.geom.Point2D;

public class Place extends Site {

    private int totalSlots;

    /*------------------------------------------------------------------*\
   	|*							Constructors							*|
   	\*------------------------------------------------------------------*/

    public Place(Point2D position, String name, int bikes) {
        super(position, name, bikes);
        this.totalSlots = Settings.SLOTS;
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

    public void pushBike(Entity entity) throws InterruptedException {

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

    public void pullBike(Entity entity) throws InterruptedException {
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
}
