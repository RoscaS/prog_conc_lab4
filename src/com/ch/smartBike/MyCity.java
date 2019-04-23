package com.ch.smartBike;

import com.ch.Helpers;
import com.ch.smartBike.actors.*;
import com.ch.smartBike.threads.PersonJob;
import com.ch.smartBike.threads.TruckJob;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Handles initial GUI drawing and starts jobs.
 */
public class MyCity extends JPanel {

    private BufferedImage bikeImg;
    private BufferedImage placeImg;
    private BufferedImage camionImg;

    public Depot depot;
    public Truck truck;

	/*------------------------------------------------------------------*\
	|*							Constructors							*|
	\*------------------------------------------------------------------*/

    public MyCity() throws IOException {
        placeImg = ImageIO.read(new File("resources/place.png"));
        bikeImg = ImageIO.read(new File("resources/bike.png"));
        camionImg = ImageIO.read(new File("resources/camion.png"));

        depot = new Depot(new Point2D.Double(650, 20));
        truck = new Truck(depot.getPosition());

        startJobs();
    }

	/*------------------------------------------------------------------*\
	|*							Private Methods 						*|
	\*------------------------------------------------------------------*/

    private void startJobs() {
        List <Site> p = Settings.SITE_LIST;
        for (Person person : Settings.PERSON_LIST) {
            int idxA = Helpers.randint(0, p.size() - 1);
            int idxB = Helpers.randint(0, p.size() - 1, idxA);
            PersonJob pj = new PersonJob(person, (Place)p.get(idxA), (Place)p.get(idxB), this);
            pj.start();
        }
        TruckJob tj = new TruckJob(truck, this);
        tj.start();
    }

    /*------------------------------------------------------------------*\
   	|*							Public Methods 							*|
   	\*------------------------------------------------------------------*/


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Site place : Settings.SITE_LIST) {
            g.setColor(Color.BLACK);
            g.drawImage(placeImg, place.getX(), place.getY(), null);
            g.drawString(place.getName(), place.getX() + 60, place.getY() + 30);
            g.drawOval(place.getX() + 5, place.getY() + 70, 50, 50);

            g.drawImage(bikeImg, place.getX() - 40, place.getY() + 70, null);
            g.drawString(place.getAvailableBikes() + "/" + Settings.SLOTS,
                    place.getX() - 45, place.getY() + 70);
        }

        g.drawImage(placeImg, depot.getX(), depot.getY(), null);
        g.drawString(depot.getName(), depot.getX() + 60, depot.getY() + 30);
        g.drawOval(depot.getX() + 5, depot.getY() + 70, 50, 50);


        g.drawImage(camionImg, truck.getX() + 5, truck.getY() + 65, null);
        g.drawString(String.valueOf(truck.getBikesLoaded()), truck.getX() - 8, truck.getY() + 70);


        g.drawImage(bikeImg, depot.getX() - 40, depot.getY() + 70, null);
        g.drawString(String.valueOf(depot.getAvailableBikes()), depot.getX() - 45, depot.getY() + 70);

        for (Person person : Settings.PERSON_LIST) {
            g.drawImage(person.getStateImage(), person.getX() + 5, person.getY() + 65, null);
            g.drawString(person.getName(), person.getX() + 30, person.getY() + 80);
        }
    }
}
