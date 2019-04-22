package com.ch.smartBike.actors;

import com.ch.smartBike.Settings;
import java.awt.geom.Point2D;
import java.util.concurrent.Semaphore;

public class Place extends Site {

    private int totalSlots;
    protected Semaphore emptyCond;
    protected Semaphore fullCond;

    /*------------------------------------------------------------------*\
   	|*							Constructors						  *|
   	\*------------------------------------------------------------------*/

    public Place(Point2D position, String name, int bikes) {
        super(position, name, bikes);
        this.totalSlots = Settings.SLOTS;
        emptyCond = new Semaphore(Settings.SLOTS);
        fullCond = new Semaphore(Settings.SLOTS);
        try {
            emptyCond.acquire(Settings.INITIAL_FREE_SLOTS);
            fullCond.acquire(bikes);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*------------------------------------------------------------------*\
   	|*							Public Methods 						  *|
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
        fullCond.acquire();
        incrementBikes();
        emptyCond.release();
    }

    public void pullBike(Entity entity) throws InterruptedException {
        emptyCond.acquire();
        decrementBikes();
        fullCond.release();
    }
}
