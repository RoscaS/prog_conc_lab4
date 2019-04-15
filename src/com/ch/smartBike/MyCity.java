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

import com.ch.smartBike.gui.*;
import com.ch.smartBike.thread.MoverHab;

public class MyCity extends JPanel {

    private static final long serialVersionUID = 1L;

    private Place[] placeList;
    private List<People> peopleList;
    private Depot depot;
    private Camion camion;

    private BufferedImage bikeImg;
    private BufferedImage placeImg;
    private BufferedImage camionImg;

    public MyCity(Config config) throws IOException {
        int nbSite = config.getNbSite();
        int nbBorne = config.getNbBorne();
        int nbVelo = config.getNbVelo();
        int radius = 200;

        placeList = new Place[nbSite];

        for (int i = 1; i <= nbSite; i++) {
            Point2D position = new Point2D.Double(
                    120 + radius + radius * Math.cos(2.0 * 3.14 / ((float) nbSite) * ((float) i + 1)),
                    10 + radius + radius * Math.sin(2.0 * 3.14 / ((float) nbSite) * ((float) i + 1))
            );
            Place place = new Place(ParamList.getSiteName(i - 1), position, nbBorne - 2, nbBorne);
            placeList[i - 1] = place;
        }
        depot = new Depot(new Point2D.Double(650, 20), nbVelo - (nbSite * (nbBorne - 2)), nbVelo);

        int nbHab = config.getNbHab();
        peopleList = new ArrayList<People>();

        for (int i = 1; i <= nbHab; i++) {
            Place placeInit = Place.getAlea(placeList);
            People people = new People(ParamList.getName(i - 1), placeInit.getPosition());
            peopleList.add(people);
        }

        camion = new Camion(depot.getPosition(), 0);
        placeImg = ImageIO.read(new File("place.png"));
        bikeImg = ImageIO.read(new File("bike.png"));
        camionImg = ImageIO.read(new File("camion.png"));


        People poep = peopleList.get(0);
        Place pl1 = placeList[0];
        Place pl2 = placeList[1];

        MoverHab m = new MoverHab(poep, pl1, pl2, this);

        m.start();


        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Place place : placeList) {
            g.setColor(Color.BLACK);
            g.drawImage(placeImg, place.getX(), place.getY(), null);
            g.drawString(place.getSiteName(), place.getX() + 60, place.getY() + 30);
            g.drawOval(place.getX() + 5, place.getY() + 70, 50, 50);

            g.drawImage(bikeImg, place.getX() - 40, place.getY() + 70, null);
            g.drawString(String.valueOf(place.getNbVelo()) + "/" + String.valueOf(place.getNbPlaceVide()),
                    place.getX() - 45, place.getY() + 70);
        }

        g.drawImage(placeImg, depot.getX(), depot.getY(), null);
        g.drawString(depot.getSiteName(), depot.getX() + 60, depot.getY() + 30);
        g.drawOval(depot.getX() + 5, depot.getY() + 70, 50, 50);

        g.drawImage(camionImg, camion.getX() + 5, camion.getY() + 65, null);
        g.drawString(String.valueOf(camion.getNbVelo()), camion.getX() - 8, camion.getY() + 70);

        g.drawImage(bikeImg, depot.getX() - 40, depot.getY() + 70, null);
        g.drawString(String.valueOf(depot.getNbVelo()), depot.getX() - 45, depot.getY() + 70);

        for (People people : peopleList) {
            g.drawImage(people.getImage(), people.getX() + 5, people.getY() + 65, null);
            g.drawString(people.getPersName(), people.getX() + 30, people.getY() + 80);
        }

    }


}
