package com.ch.smartBike.gui;

import java.awt.geom.Point2D;

public class Depot implements Site{

	private Point2D position;
	private int nbVelo;
	private int nbPlaceVide;

	public Depot(Point2D aPosition, int aNbVelo, int aNbBorne) {
		position = aPosition;
		nbVelo = aNbVelo;
		nbPlaceVide = aNbBorne - aNbVelo;
	}


	public boolean isSlotAvailable() { return false; }
	public boolean isBikeAvailable() { return false; }


	public void takeBike(Person person, Place dest) { }

	public void leaveBike(Person person) { }



	public void incrementBikes(int x) { }

	public void decrementBikes(int x) { }


	public String getSiteName() {
		return "DEPOT";
	}

	public Point2D getPosition() {
		return position;
	}

	public int getX() {
		return (int) position.getX();
	}

	public int getY() {
		return (int) position.getY();
	}

	public int getBikeAvailable() {
		return nbVelo;
	}

	public void setBikeAvailable(int bikeAvailable) {
		this.nbVelo = bikeAvailable;
	}

	public int getFreeSlots() {
		return nbPlaceVide;
	}

	public void setSlotAvailable(int slotAvailable) {
		this.nbPlaceVide = slotAvailable;
	}
}
