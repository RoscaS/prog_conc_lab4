package smartBike.gui;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class People {

	private StatePeople state;
	private String persName;
	private Point2D position;

	private BufferedImage waitImg;
	private BufferedImage bikeImg;
	private BufferedImage workImg;

	public People(String aPersName, Point2D aPosition) throws IOException {
		state = StatePeople.WAIT;
		persName = aPersName;
		position = aPosition;

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

	public String getPersName() {
		return persName;
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



}
