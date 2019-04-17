package com.ch.smartBike.actors;

import java.awt.geom.Point2D;

public class Truck extends Entity{

    private int bikesLoaded;


	/*------------------------------------------------------------------*\
	|*							Constructors							*|
	\*------------------------------------------------------------------*/

	public Truck(Point2D position){
	    super.setPosition(position);
        bikesLoaded = 0;
    }

    /*------------------------------------------------------------------*\
   	|*							Public Methods 							*|
   	\*------------------------------------------------------------------*/


	/*------------------------------*\
	|*				Getters			*|
	\*------------------------------*/
	public int getBikesLoaded(){
        return bikesLoaded;
    }

    /*------------------------------*\
   	|*				Setters			*|
   	\*------------------------------*/
    public void setAvailableBikes(int bikes){
        bikesLoaded = bikes;
    }

    public void incrementBikes(int n) {
        setAvailableBikes(bikesLoaded + n);
    }

    public void decrementBikes(int n) {
        setAvailableBikes(bikesLoaded - n);
    }
}
