package com.ch.smartBikeBis.actors;

import com.ch.smartBikeBis.Default;

import java.awt.geom.Point2D;

public class Depot extends Site {

	/*------------------------------------------------------------------*\
	|*							Constructors							*|
	\*------------------------------------------------------------------*/

    public Depot(Point2D position) {
        super(position, "DEPOT", Default.DEPOT_AVAILABLE_BIKES);
    }

    /*------------------------------------------------------------------*\
   	|*							Public Methods 							*|
   	\*------------------------------------------------------------------*/

    @Override
    public void pushBike() throws InterruptedException {
    }

    @Override
    public void pullBike() throws InterruptedException {
    }

    /*------------------------------*\
	|*				Getters			*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Private Methods 						*|
	\*------------------------------------------------------------------*/
}
