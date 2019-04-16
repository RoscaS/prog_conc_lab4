package com.ch.smartBike;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.ch.Helpers;
import com.ch.smartBike.gui.*;
import com.ch.smartBike.thread.PeopleThread;

public class MyCity extends JPanel {

    private static final long serialVersionUID = 1L;

    // public static Place[] places;
    public static List<Place> places = new ArrayList<>();
    public static List<Person> people = new ArrayList<>();

    private Depot depot;
    private Camion camion;

    private BufferedImage bikeImg;
    private BufferedImage placeImg;
    private BufferedImage camionImg;

    public MyCity(Config config) throws IOException {

        placeImg = ImageIO.read(new File("place.png"));
        bikeImg = ImageIO.read(new File("bike.png"));
        camionImg = ImageIO.read(new File("camion.png"));

        int inhabitants = config.getNbHab();
        int totalSites = config.getNbSite();
        int slotsPerSite = config.getSlotsPerSite();
        int totalBikes = config.getNbVelo();
        int radius = 200;

        depot = new Depot(
                new Point2D.Double(650, 20),
                totalBikes - (totalSites * (slotsPerSite - 2)),
                totalBikes
        );

        camion = new Camion(depot.getPosition(), 0);


        for (int i = 1; i <= totalSites; i++) {
            Point2D position = new Point2D.Double(
                    120 + radius + radius * Math.cos(2.0 * 3.14 / ((float) totalSites) * ((float) i + 1)),
                    10 + radius + radius * Math.sin(2.0 * 3.14 / ((float) totalSites) * ((float) i + 1))
            );
            places.add(new Place(ParamList.getSiteName(i - 1), position, slotsPerSite));
        }

        for (int i = 1; i <= inhabitants; i++) {
            Place placeInit = Place.getAlea(places);
            Person person = new Person(ParamList.getName(i - 1), placeInit.getPosition());
            people.add(person);
        }

        for (Person person : people) {
            int idxA = Helpers.randint(0, places.size() - 1);
            int idxB = Helpers.randint(0, places.size() - 1, idxA);
            PeopleThread pt = new PeopleThread(person, places.get(idxA), places.get(idxB), this);
            pt.start();
        }

        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Place place : places) {
            g.setColor(Color.BLACK);
            g.drawImage(placeImg, place.getX(), place.getY(), null);
            g.drawString(place.getName(), place.getX() + 60, place.getY() + 30);
            g.drawOval(place.getX() + 5, place.getY() + 70, 50, 50);

            g.drawImage(bikeImg, place.getX() - 40, place.getY() + 70, null);
            g.drawString(place.getBikeAvailable() + "/" + place.getSlots(),
                    place.getX() - 45, place.getY() + 70);
        }

        g.drawImage(placeImg, depot.getX(), depot.getY(), null);
        g.drawString(depot.getSiteName(), depot.getX() + 60, depot.getY() + 30);
        g.drawOval(depot.getX() + 5, depot.getY() + 70, 50, 50);

        g.drawImage(camionImg, camion.getX() + 5, camion.getY() + 65, null);
        g.drawString(String.valueOf(camion.getNbVelo()), camion.getX() - 8, camion.getY() + 70);

        g.drawImage(bikeImg, depot.getX() - 40, depot.getY() + 70, null);
        g.drawString(String.valueOf(depot.getBikeAvailable()), depot.getX() - 45, depot.getY() + 70);

        for (Person person : people) {
            g.drawImage(person.getImage(), person.getX() + 5, person.getY() + 65, null);
            g.drawString(person.getName(), person.getX() + 30, person.getY() + 80);
        }
    }


}
