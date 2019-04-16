package com.ch.smartBike.gui;

public interface Site {

	int getX();
	int getY();

	// perso
	boolean isSlotAvailable();
	boolean isBikeAvailable();

	void takeBike(Person p, Place dest);
	void leaveBike(Person p);

	void incrementBikes(int x);
	void decrementBikes(int x);
	// end perso

	int getBikeAvailable();
	void setBikeAvailable(int bikeAvailable);
	int getFreeSlots();
	void setSlotAvailable(int slotAvailable);

}
