package com.ch.smartBike.threads;

import com.ch.Helpers;
import com.ch.smartBike.MyCity;
import com.ch.smartBike.actors.Entity;
import com.ch.smartBike.actors.Site;

import java.awt.geom.Point2D;

public abstract class BaseJob extends Thread {

    protected MyCity city;
    protected Entity entity;
    protected Site start;
    protected Site destination;

    protected double deltaX;
    protected double deltaY;

	/*------------------------------------------------------------------*\
	|*							Constructors							*|
	\*------------------------------------------------------------------*/

    public BaseJob(Entity entity, Site start, Site destination, MyCity city) {
        this.city = city;
        this.entity = entity;
        this.start = start;
        this.destination = destination;
    }

	/*------------------------------------------------------------------*\
	|*							Protected Methods 					  *|
	\*------------------------------------------------------------------*/

    /*------------------------------*\
   	|*				Graphics		*|
   	\*------------------------------*/

    protected void updateCoords() {
        deltaX = (destination.getX() - start.getX()) / 100.0;
        deltaY = (destination.getY() - start.getY()) / 100.0;
    }

    protected void updateDraw() {
        double deltaXAdd = 0;
        double deltaYAdd = 0;

        int dt = Helpers.randint(20, 60);

        for (int i = 1; i <= 100; i++) {
            deltaXAdd += deltaX;
            deltaYAdd += deltaY;
            double x = start.getX() + deltaXAdd;
            double y = start.getY() + deltaYAdd;
            entity.setPosition(new Point2D.Double(x, y));
            city.repaint();
            try {
                sleep(dt);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    protected void repaint() {
        entity.setPosition(new Point2D.Double(destination.getX(), destination.getY()));
        city.repaint();
    }



}
