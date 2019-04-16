package com.ch.smartBike.gui;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Person {

	private StatePeople state;
	private String name;
	private Point2D position;

	private BufferedImage waitImg;
	private BufferedImage bikeImg;
	private BufferedImage workImg;

	public Person(String name, Point2D aPosition) throws IOException {
		this.state = StatePeople.WAIT;
		this.position = aPosition;
		this.name = name;

		waitImg = ImageIO.read(new File("people_wait.png"));
		bikeImg = ImageIO.read(new File("people_bike.png"));
		workImg = ImageIO.read(new File("people_action.png"));
	}

	public static enum StatePeople {
		WAIT, BIKE, WORK;
	}

	public BufferedImage getImage() {
		switch (state) {
		case WAIT:
			return waitImg;
		case BIKE:
			return bikeImg;
		case WORK:
			return workImg;
		default:
			return waitImg;
		}
	}

	public void setWaitState(String where, Place place) {
		setState(StatePeople.WAIT);
		StringBuilder sb = new StringBuilder(name);
		sb.append(" \tis waiting" + " \tstate: ");
		sb.append(StatePeople.WAIT).append(" ").append(place.getName());
		sb.append(" (").append(where);
		System.out.println(sb.append(")\n"));
	}

	public String getName() {
		return name;
	}

	public void setPosition(Point2D position) {
		this.position = position;
	}

	public int getX() {
		return (int) position.getX();
	}

	public int getY() {
		return (int) position.getY();
	}

	public void setState(StatePeople state) {
		this.state = state;
	}

	public StatePeople getState() {
		return state;
	}
}
