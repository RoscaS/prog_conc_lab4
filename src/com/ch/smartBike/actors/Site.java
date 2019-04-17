package com.ch.smartBike.actors;

import java.awt.geom.Point2D;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public abstract class Site {

    private Point2D position;
    private String name;

    private int availableBikes;

    protected ReentrantLock lock;
    protected Condition emptyCond;
    protected Condition fullCond;

	/*------------------------------------------------------------------*\
	|*							Constructors							*|
	\*------------------------------------------------------------------*/

    public Site(Point2D position, String name, int bikes) {
        this.availableBikes = bikes;
        this.position = position;
        this.name = name;

        lock = new ReentrantLock();
        emptyCond = lock.newCondition();
        fullCond = lock.newCondition();
    }

    /*------------------------------------------------------------------*\
   	|*							Public Methods 							*|
   	\*------------------------------------------------------------------*/

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(name);
        return sb.toString();
    }

    public abstract void pushBike(Entity entity) throws InterruptedException;

    public abstract void pullBike(Entity entity)  throws InterruptedException;

    public void incrementBikes(int n) {
        lock.lock();
        setAvailableBikes(getAvailableBikes() + n);
        lock.unlock();
    }

    public void decrementBikes(int n) {
        lock.lock();
        setAvailableBikes(getAvailableBikes() - n);
        lock.unlock();
    }

    /*------------------------------*\
	|*				Getters			*|
	\*------------------------------*/

    public Point2D getPosition() {
  		return position;
  	}

    public int getAvailableBikes() {
        return availableBikes;
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return (int) position.getX();
    }

    public int getY() {
        return (int) position.getY();
    }

    /*------------------------------*\
   	|*				Getters			*|
   	\*------------------------------*/

    public void setAvailableBikes(int availableBikes) {
        this.availableBikes = availableBikes;
    }


}
