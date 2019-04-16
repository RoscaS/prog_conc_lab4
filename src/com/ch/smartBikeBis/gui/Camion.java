package com.ch.smartBikeBis.gui;

import java.awt.geom.Point2D;

public class Camion extends Entity{

    private int availableBikes;
	/*------------------------------------------------------------------*\
	|*							Constructors							*|
	\*------------------------------------------------------------------*/
	public Camion(Point2D position){
	    super.setPosition(position);
	    availableBikes = 0;
    }

    /*------------------------------------------------------------------*\
   	|*							Public Methods 							*|
   	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*				Getters			*|
	\*------------------------------*/
	public int getAvailableBikes(){
        return availableBikes;
    }

    /*------------------------------*\
   	|*				Setters			*|
   	\*------------------------------*/
    public void setAvailableBikes(int bikes){
        availableBikes = bikes;
    }
}
