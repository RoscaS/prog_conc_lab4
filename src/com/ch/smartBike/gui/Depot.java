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


	public boolean isFreeSlots() {
		return false;
	}


	public void takeBike(Person person) { }

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

	public int getBikeNumber() {
		return nbVelo;
	}

	public void setBikeNumber(int bikeNumber) {
		this.nbVelo = bikeNumber;
	}

	public int getFreeSlots() {
		return nbPlaceVide;
	}

	public void setFreeSlots(int freeSlots) {
		this.nbPlaceVide = freeSlots;
	}
}
