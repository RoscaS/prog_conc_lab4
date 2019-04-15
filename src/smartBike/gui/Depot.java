package smartBike.gui;

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
