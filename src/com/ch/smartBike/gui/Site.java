package com.ch.smartBike.gui;

public interface Site {

	public int getX();

	public int getY();

	// perso
	public boolean isFreeSlots();

	public void takeBike(Person p);
	public void leaveBike(Person p);

	public void incrementBikes(int x);
	public void decrementBikes(int x);
	// end perso

	public int getBikeNumber();

	public void setBikeNumber(int bikeNumber);

	public int getFreeSlots();

	public void setFreeSlots(int freeSlots);

}
