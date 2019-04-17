package com.ch.smartBikeBis;

import com.ch.Helpers;
import com.ch.smartBikeBis.actors.Camion;
import com.ch.smartBikeBis.actors.Depot;
import com.ch.smartBikeBis.actors.Person;
import com.ch.smartBikeBis.actors.Place;
import com.ch.smartBikeBis.threads.PersonJob;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class MyCity extends JPanel {

    private final long serialVersionUID = 1L;

    private BufferedImage bikeImg;
    private BufferedImage placeImg;
    private BufferedImage camionImg;

    private Depot depot;
    private Camion camion;

	/*------------------------------------------------------------------*\
	|*							Constructors							*|
	\*------------------------------------------------------------------*/

    public MyCity() throws IOException {
        placeImg = ImageIO.read(new File("place.png"));
        bikeImg = ImageIO.read(new File("bike.png"));
        camionImg = ImageIO.read(new File("camion.png"));

        depot = new Depot(new Point2D.Double(650, 20));
        camion = new Camion(depot.getPosition());

        startJobs();
    }

	/*------------------------------------------------------------------*\
	|*							Private Methods 						*|
	\*------------------------------------------------------------------*/

    private void startJobs() {
        List <Place> p = Default.PLACES_LIST;
        for (Person person : Default.PERSON_LIST) {
            int idxA = Helpers.randint(0, p.size() - 1);
            int idxB = Helpers.randint(0, p.size() - 1, idxA);
            PersonJob pj = new PersonJob(person, p.get(idxA), p.get(idxB), this);
            pj.start();
        }
    }

    /*------------------------------------------------------------------*\
   	|*							Public Methods 							*|
   	\*------------------------------------------------------------------*/

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Place place : Default.PLACES_LIST) {
            g.setColor(Color.BLACK);
            g.drawImage(placeImg, place.getX(), place.getY(), null);
            g.drawString(place.getName(), place.getX() + 60, place.getY() + 30);
            g.drawOval(place.getX() + 5, place.getY() + 70, 50, 50);

            g.drawImage(bikeImg, place.getX() - 40, place.getY() + 70, null);
            g.drawString(place.getAvailableBikes() + "/" + place.getTotalSlots(),
                    place.getX() - 45, place.getY() + 70);
        }

        g.drawImage(placeImg, depot.getX(), depot.getY(), null);
        g.drawString(depot.getName(), depot.getX() + 60, depot.getY() + 30);
        g.drawOval(depot.getX() + 5, depot.getY() + 70, 50, 50);

        g.drawImage(camionImg, camion.getX() + 5, camion.getY() + 65, null);
        g.drawString(String.valueOf(camion.getAvailableBikes()), camion.getX() - 8, camion.getY() + 70);

        g.drawImage(bikeImg, depot.getX() - 40, depot.getY() + 70, null);
        g.drawString(String.valueOf(depot.getAvailableBikes()), depot.getX() - 45, depot.getY() + 70);

        for (Person person : Default.PERSON_LIST) {
            g.drawImage(person.getStateImage(), person.getX() + 5, person.getY() + 65, null);
            g.drawString(person.getName(), person.getX() + 30, person.getY() + 80);
        }
    }
}
