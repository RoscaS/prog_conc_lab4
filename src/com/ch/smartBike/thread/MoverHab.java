package com.ch.smartBike.thread;

import java.awt.geom.Point2D;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.ch.smartBike.MyCity;
import com.ch.smartBike.gui.People;
import com.ch.smartBike.gui.Site;

public class MoverHab extends Thread {
	private MyCity myCity;

	private boolean stillRunning;

	private People people;
	private Site start;
	private Site dest;

	private double deltaX;
	private double deltaY;

	Lock destLock;
	Condition destCond;

	public MoverHab(People aPeople, Site aStart, Site aDest, MyCity aMyCity) {
		myCity = aMyCity;
		people = aPeople;
		start = aStart;
		dest = aDest;

		destLock = new ReentrantLock();
		destCond = destLock.newCondition();

		stillRunning = true;

		deltaX = (dest.getX() - start.getX()) / 100.0;
		deltaY = (dest.getY() - start.getY()) / 100.0;
	}


	public void run() {
		double deltaXAdd = 0;
		double deltaYAdd = 0;

		printData();

		people.setState(People.StatePeople.WAIT);
		status("before leave");

		if (start.getNbVelo() > 0) {
			start.setNbVelo(start.getNbVelo() - 1);
			people.setState(People.StatePeople.BIKE);
			status("got a bike");
		}

		status("leaving");
		for (int i = 1; i <= 100; i++) {
			deltaXAdd += deltaX;
			deltaYAdd += deltaY;

			people.setPosition(
					new Point2D.Double(start.getX() + deltaXAdd, start.getY() + deltaYAdd)
			);
			myCity.repaint();
			try {
				sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		people.setState(People.StatePeople.WAIT);
		status("arrived");

		// destLock.lock();
		System.out.println(dest.getNbPlaceVide());
		// while (dest.getNbVelo() >= dest.getNbPlaceVide() )

		if (dest.getNbPlaceVide() > dest.getNbVelo()) {
			dest.setNbVelo(dest.getNbVelo() + 1);
			people.setState(People.StatePeople.WAIT);
			status("bike dropped");
		}

		people.setState(People.StatePeople.WORK);
		status("on his way to work");

		printData();



		people.setPosition(new Point2D.Double(dest.getX(), dest.getY()));
		myCity.repaint();
		stillRunning = false;
	}



	public void printData() {
		StringBuilder sb = new StringBuilder("\nT_people: ").append(people.getPersName());
		sb.append("\nStart: \n");
		sb.append("\tNb bikes: \t").append(start.getNbVelo());
		sb.append("\n\tNb slots: \t").append(start.getNbPlaceVide());
		sb.append("\nDest: \n");
		sb.append("\tNb bikes: \t").append(dest.getNbVelo());
		sb.append("\n\tNb slots: \t").append(dest.getNbPlaceVide());
		System.out.println(sb.append("\n"));
	}

	public void status(String info) {
		StringBuilder sb = new StringBuilder(people.getPersName());
		sb.append(":\t").append(people.getState()).append(" ").append(info);
		System.out.println(sb);
	}

	public boolean stillRunning() {

		return stillRunning;
	}


}
