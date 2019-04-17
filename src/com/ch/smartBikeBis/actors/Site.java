package com.ch.smartBikeBis.actors;

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

    public abstract void pushBike() throws InterruptedException;

    public abstract void pullBike()  throws InterruptedException;

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
