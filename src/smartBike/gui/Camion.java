package smartBike.gui;

import java.awt.geom.Point2D;

public class Camion {

    private Point2D position;
    private int nbVelo;

    public Camion(Point2D aPosition, int aNbVelo) {
        position = aPosition;
        nbVelo = aNbVelo;
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

    public int getNbVelo() {
        return nbVelo;
    }

    public void setNbVelo(int nbVelo) {
        this.nbVelo = nbVelo;
    }


}
