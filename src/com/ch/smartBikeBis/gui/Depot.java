package com.ch.smartBikeBis.gui;

import java.awt.geom.Point2D;

public class Depot extends Site {

	/*------------------------------------------------------------------*\
	|*							Constructors							*|
	\*------------------------------------------------------------------*/

    public Depot(Point2D position, int availableBikes) {
        super(position, availableBikes, "DEPOT");
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
