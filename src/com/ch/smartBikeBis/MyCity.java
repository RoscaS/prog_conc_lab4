package com.ch.smartBikeBis;

import com.ch.Helpers;
import com.ch.smartBike.ParamList;
import com.ch.smartBikeBis.gui.Camion;
import com.ch.smartBikeBis.gui.Depot;
import com.ch.smartBikeBis.gui.Person;
import com.ch.smartBikeBis.gui.Place;
import com.ch.smartBikeBis.threads.PersonJob;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyCity extends JPanel {

    private final long serialVersionUID = 1L;

    private BufferedImage bikeImg;
    private BufferedImage placeImg;
    private BufferedImage camionImg;

    private int persones;
    private int totalSites;
    private int slotsPerSite;
    private int totalBikes;
    private int radius;

    private List<Place> places = new ArrayList<>();
    private List<Person> people = new ArrayList<>();

    private Depot depot;
    private Camion camion;

	/*------------------------------------------------------------------*\
	|*							Constructors							*|
	\*------------------------------------------------------------------*/

    public MyCity(Config config) throws IOException {
        persones = config.getNbHab();
        totalSites = config.getNbSite();
        slotsPerSite = config.getSlotsPerSite();
        totalBikes = config.getNbVelo();
        radius = 200;

        initImages();
        initPlaces();
        initPersons();
        initDepot();

        startJobs();
    }

    private void initDepot() {
        int bikes = totalBikes - (slotsPerSite - Constants.INITIAL_FREE_SLOTS) * totalSites;
        depot = new Depot(new Point2D.Double(650, 20), bikes);
        camion = new Camion(depot.getPosition());
    }

	/*------------------------------------------------------------------*\
	|*							Private Methods 						*|
	\*------------------------------------------------------------------*/

    private void startJobs() {
        for (Person person : people) {
            int idxA = Helpers.randint(0, places.size() - 1);
            int idxB = Helpers.randint(0, places.size() - 1, idxA);
            PersonJob pt = new PersonJob(person, places.get(idxA), places.get(idxB), this);
            pt.start();
        }
    }

    private void initImages() throws IOException {
        placeImg = ImageIO.read(new File("place.png"));
        bikeImg = ImageIO.read(new File("bike.png"));
        camionImg = ImageIO.read(new File("camion.png"));
    }

    private void initPlaces() {
        for (int i = 1; i <= totalSites; i++) {
            Point2D position = new Point2D.Double(
                    120 + radius + radius * Math.cos(2.0 * Math.PI / ((float) totalSites) * ((float) i + 1)),
                    10 + radius + radius * Math.sin(2.0 * Math.PI / ((float) totalSites) * ((float) i + 1))
            );
            places.add(new Place(position, Constants.SITES[i - 1], slotsPerSite));
        }
    }

    private void initPersons() {
        for (int i = 1; i <= persones; i++) {
            Person person = new Person(ParamList.getName(i - 1));
            people.add(person);
        }
    }

    /*------------------------------------------------------------------*\
   	|*							Public Methods 							*|
   	\*------------------------------------------------------------------*/

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Place place : places) {
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

        for (Person person : people) {
            g.drawImage(person.getStateImage(), person.getX() + 5, person.getY() + 65, null);
            g.drawString(person.getName(), person.getX() + 30, person.getY() + 80);
        }
    }

    /*------------------------------*\
    |*				Getters			*|
    \*------------------------------*/

    public List<Place> getPlaces() {
        return places;
    }
}
