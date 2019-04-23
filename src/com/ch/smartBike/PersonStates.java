package com.ch.smartBike;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public enum PersonStates {

    WAIT("people_wait.png"),
    BIKE("people_bike.png"),
    WORK("people_action.png");

    private final static String dir = "resources/";

	/*------------------------------------------------------------------*\
	|*							Constructors							*|
	\*------------------------------------------------------------------*/

    PersonStates(String file)  {
        try {
            image = ImageIO.read(new File(dir + file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*------------------------------------------------------------------*\
   	|*							Public Methods 							*|
   	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*				Getters			*|
	\*------------------------------*/

    public BufferedImage getImage() {
        return image;
    }

    /*------------------------------------------------------------------*\
	|*							Private Attributs 						*|
	\*------------------------------------------------------------------*/

    private BufferedImage image;

}
