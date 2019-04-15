package com.ch.smartBike.thread;

import java.awt.geom.Point2D;

import com.ch.smartBike.MyCity;
import com.ch.smartBike.gui.Camion;
import com.ch.smartBike.gui.Site;

public class MoverCamion extends Thread {

	private MyCity myCity;

	private boolean stillRunning;

	private Camion camion;
	private Site start;
	private Site dest;

	private double deltaX;
	private double deltaY;

	public MoverCamion(Camion aCamion, Site aStart, Site aDest, MyCity aMyCity) {
		myCity = aMyCity;
		camion = aCamion;
		start = aStart;
		dest = aDest;

		stillRunning = true;

		deltaX = (dest.getX() - start.getX()) / 100.0;
		deltaY = (dest.getY() - start.getY()) / 100.0;
	}

	public void run() {
		double deltaXAdd = 0;
		double deltaYAdd = 0;
		for (int i = 1; i <= 100; i++) {
			deltaXAdd += deltaX;
			deltaYAdd += deltaY;
			camion.setPosition(new Point2D.Double(start.getX() + deltaXAdd, start.getY() + deltaYAdd));
			myCity.repaint();
			try {
				sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		camion.setPosition(new Point2D.Double(dest.getX(), dest.getY()));
		myCity.repaint();
		stillRunning = false;
	}

	public boolean stillRunning() {

		return stillRunning;
	}


}
