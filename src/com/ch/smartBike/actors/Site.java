package com.ch.smartBike.actors;
import java.awt.geom.Point2D;
import java.util.concurrent.Semaphore;

public abstract class Site {

    private Point2D position;
    private String name;
    private int availableBikes;

    protected Semaphore lock;


	/*------------------------------------------------------------------*\
	|*							Constructors						  *|
	\*------------------------------------------------------------------*/

    public Site(Point2D position, String name, int bikes) {
        this.availableBikes = bikes;
        this.position = position;
        this.name = name;
        lock = new Semaphore(1);
    }

    /*------------------------------------------------------------------*\
   	|*							Public Methods 						  *|
   	\*------------------------------------------------------------------*/

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(name);
        return sb.toString();
    }

    public abstract void pushBike(Entity entity) throws InterruptedException;

    public abstract void pullBike(Entity entity)  throws InterruptedException;

    public void incrementBikes(int n){
        try {
            lock.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setAvailableBikes(getAvailableBikes() + n);
        lock.release();
    }

    public void decrementBikes(int n){
        try {
            lock.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setAvailableBikes(getAvailableBikes() - n);
        lock.release();
    }

    /*------------------------------*\
	|*				Getters		   *|
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
   	|*			  Setters	       *|
   	\*------------------------------*/

    public void setAvailableBikes(int availableBikes) {
        this.availableBikes = availableBikes;
    }
}
