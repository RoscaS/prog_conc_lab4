package com.ch.smartBikeBis.gui;

import java.awt.geom.Point2D;

public abstract class Entity {

    private Point2D position;

    public Entity(){
        position = new Point2D.Double(0, 0);
    }

	/*------------------------------*\
	|*				Getters			*|
	\*------------------------------*/

    public int getX() {
        return (int) position.getX();
    }

    public int getY() {
        return (int) position.getY();
    }

    /*------------------------------*\
   	|*				Setters			*|
   	\*------------------------------*/

    public void setPosition(Point2D position) {
        this.position = position;
    }
}
