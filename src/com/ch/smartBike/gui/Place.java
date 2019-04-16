package com.ch.smartBike.gui;

import java.awt.geom.Point2D;
import java.util.List;
import java.util.Random;

public class Place implements Site{

	private String name;
	private Point2D position;
	private int bikeAvailable;
	private int slotAvailable;
	private int slots;

	public Place(String aSiteNAme, Point2D aPosition, int slots) {
		this.name = aSiteNAme;
		this.position = aPosition;
		this.bikeAvailable = slots - 2;
		this.slotAvailable = 2;
		this.slots = slots;
	}

	public static Place getAlea(List<Place> places) {
		Random rand = new Random();
		int value = rand.nextInt(places.size());
		return places.get(value);
	}

	public boolean equals(Object o) {
		if (!(o instanceof Place)) {
			return false;
		}
		Place other = (Place) o;
		return this.name == other.name;
	}

	public boolean isSlotAvailable() {
		return slotAvailable > 0;
	}

	public boolean isBikeAvailable() {
		return bikeAvailable > 0;
	}


	public void takeBike(Person person, Place dest) {
		bikeAvailable = bikeAvailable - 1;
		slotAvailable = slotAvailable + 1;
		Person.StatePeople s = Person.StatePeople.BIKE;

		person.setState(s);
		StringBuilder sb = new StringBuilder(person.getName());
		sb.append("\tgot a bike \tstate: ").append(s);
		sb.append("\tfrom:\t").append(this.name);
		sb.append("\tto:\t").append(dest.name);
		System.out.println(sb);
	}

	public void leaveBike(Person person) {
		bikeAvailable = bikeAvailable + 1;
		slotAvailable = slotAvailable - 1;
		Person.StatePeople s = Person.StatePeople.WORK;
		person.setState(s);
		System.out.println(person.getName() + "\tleft bike" + " \tstate: " + s);
	}




	public void incrementBikes(int x) {
		bikeAvailable = bikeAvailable + x;
		slotAvailable = slotAvailable - x;
	}

	public void decrementBikes(int x) {
		bikeAvailable = bikeAvailable - x;
		slotAvailable = slotAvailable + x;
	}


	public int getSlots() {
		return slots;
	}

	public String getName() {
		return name;
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

	public int getBikeAvailable() { return bikeAvailable; }

	public void setBikeAvailable(int bikeAvailable) {
		this.bikeAvailable = bikeAvailable;
	}

	public int getFreeSlots() {
		return slotAvailable;
	}

	public void setSlotAvailable(int slotAvailable) {
		this.slotAvailable = slotAvailable;
	}
}
