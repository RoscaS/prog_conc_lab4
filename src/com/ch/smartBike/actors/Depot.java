package com.ch.smartBike.actors;

import com.ch.smartBike.Settings;

import java.awt.geom.Point2D;

public class Depot extends Site {

	/*------------------------------------------------------------------*\
	|*							Constructors							*|
	\*------------------------------------------------------------------*/

    public Depot(Point2D position) {
        super(position, "DEPOT", Settings.DEPOT_AVAILABLE_BIKES);
    }

    /*------------------------------------------------------------------*\
   	|*							Public Methods 							*|
   	\*------------------------------------------------------------------*/

    @Override
    public void pushBike(Entity entity) throws InterruptedException {
    }

    @Override
    public void pullBike(Entity entity) throws InterruptedException {
    }
}
