package com.ch.smartBike.gui;

import java.awt.geom.Point2D;
import java.util.Random;

public class Place implements Site{
	private String siteName;
	private Point2D position;
	private int bikeNumber;
	private int freeSlots;
	private int slots;

	public Place(String aSiteNAme, Point2D aPosition, int slots) {
		this.siteName = aSiteNAme;
		this.position = aPosition;
		this.bikeNumber = slots - 2;
		this.freeSlots = 2;
		this.slots = slots;
	}

	public static Place getAlea(Place[] placeList) {
		Random rand = new Random();
		int value = rand.nextInt(placeList.length);
		return placeList[value];
	}

	public boolean equals(Object o) {
		if (!(o instanceof Place)) {
			return false;
		}
		Place other = (Place) o;
		return this.siteName == other.siteName;
	}



	public boolean isFreeSlots() {
		return freeSlots > 0;
	}


	public void takeBike(Person person) {
		bikeNumber = bikeNumber + 1;
		freeSlots = freeSlots - 1;
		Person.StatePeople s = Person.StatePeople.BIKE;
		person.setState(s);
		System.out.println(person.getName() + "\tgot a bike" + " \t\t\tstate: " + s);
	}

	public void leaveBike(Person person) {
		bikeNumber = bikeNumber - 1;
		freeSlots = freeSlots + 1;
		Person.StatePeople s = Person.StatePeople.WORK;
		person.setState(s);
		System.out.println(person.getName() + "\tdropped his bike" + "\t\tstate: " + s);
	}




	public void incrementBikes(int x) {
		bikeNumber = bikeNumber + x;
		freeSlots = freeSlots - x;
	}

	public void decrementBikes(int x) {
		bikeNumber = bikeNumber - x;
		freeSlots = freeSlots + x;
	}


	public int getSlots() {
		return slots;
	}

	public String getSiteName() {
		return siteName;
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

	public int getBikeNumber() { return bikeNumber; }

	public void setBikeNumber(int bikeNumber) {
		this.bikeNumber = bikeNumber;
	}

	public int getFreeSlots() {
		return freeSlots;
	}

	public void setFreeSlots(int freeSlots) {
		this.freeSlots = freeSlots;
	}
}
