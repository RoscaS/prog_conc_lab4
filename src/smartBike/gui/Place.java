package smartBike.gui;

import java.awt.geom.Point2D;
import java.util.Random;

public class Place implements Site{
	private String siteName;
	private Point2D position;
	private int nbVelo;
	private int nbPlaceVide;

	public Place(String aSiteNAme, Point2D aPosition, int aNbVelo, int aNbBorne) {
		siteName = aSiteNAme;
		position = aPosition;
		nbVelo = aNbVelo;
		nbPlaceVide = aNbBorne - aNbVelo;
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

	public int getNbVelo() {
		return nbVelo;
	}

	public void setNbVelo(int nbVelo) {
		this.nbVelo = nbVelo;
	}

	public int getNbPlaceVide() {
		return nbPlaceVide;
	}

	public void setNbPlaceVide(int nbPlaceVide) {
		this.nbPlaceVide = nbPlaceVide;
	}



}
