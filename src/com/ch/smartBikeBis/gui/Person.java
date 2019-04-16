package com.ch.smartBikeBis.gui;

import com.ch.smartBikeBis.PersonStates;

import java.awt.image.BufferedImage;


public class Person extends Entity {

    private PersonStates state;
    private String name;

    /*------------------------------------------------------------------*\
	|*							Constructors							*|
	\*------------------------------------------------------------------*/

    public Person(String name) {
        super();
        this.name = name;
        this.state = PersonStates.WAIT;
    }

    /*------------------------------------------------------------------*\
   	|*							Public Methods 							*|
   	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*				Getters			*|
	\*------------------------------*/

    public BufferedImage getStateImage() {
        return state.getImage();
    }

    public String getName() {
        return name;
    }

    public PersonStates getState() {
   		return state;
   	}

	/*------------------------------*\
	|*				Setters			*|
	\*------------------------------*/

    public void setState(PersonStates state) {
        this.state = state;
    }
}
