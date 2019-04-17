package com.ch.smartBikeBis;

import com.ch.smartBikeBis.actors.Person;
import com.ch.smartBikeBis.actors.Place;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Default {

    /*------------------------------------------------------------------*\
   	|*							Constant values 						*|
   	\*------------------------------------------------------------------*/

    public final static int PEOPLES = 10;
    public final static int SITES = 5;
    public final static int SLOTS = 5;
    public final static int BIKES = 30;

    public final static int RADIUS = 200;
    public final static int INITIAL_FREE_SLOTS = 2;

    public final static int MIN_WORK_TIME = 1000;
    public final static int MAX_WORK_TIME = 10000;

    public final static boolean DEBUG = true;

    public final static int DEPOT_AVAILABLE_BIKES = depotAvailableBikes();
    public final static List<Place> PLACES_LIST = buildPlacesList();
    public final static List<Person> PERSON_LIST = buildPersonList();

    /*------------------------------------------------------------------*\
   	|*							Private Methods 						*|
   	\*------------------------------------------------------------------*/

    private static int depotAvailableBikes() {
        return BIKES - (SLOTS - INITIAL_FREE_SLOTS) * SITES;
    }

    private static List<Place> buildPlacesList() {
        List<Place> places = new ArrayList<>();
        int bikes = SLOTS - INITIAL_FREE_SLOTS;
        String[] placeNames = {
                    "MAISON", "ECOLE", "TRAVAIL", "PISCINE", "MAGASIN", "BOUCHERIE",
                    "BANQUE", " BAR", "RESTAURANT", "DISCOTHEQUE", "BOULANGERIE",
                    "FITNESS", "PARC", "JARDIN", "CINEMA"
            };

        for (int i = 0; i <= SITES; i++) {
            Point2D position = new Point2D.Double(coord(120, i), coord(10, i));
            System.out.println(placeNames[i]);
            places.add(new Place(position, placeNames[i], bikes));
        }
        return places;
    }

    private static List<Person> buildPersonList() {
        List<Person> people = new ArrayList<>();
        String[] peopleNames = {
                    "Oliver", "Guille", "Marcel", "Louise", "Margee", "Noelle",
                    "Robert", "Benben", "Adrien", "Hedvis", "Ericso", "Emilie",
                    "Garlan", "Elisah", "Patrus"
            };

        for (int i = 0; i <= PEOPLES; i++) {
            Person person = new Person(peopleNames[i]);
            people.add(person);
        }
        return people;
    }


    private static double coord(int a, int i) {
        double angle = 2.0 * Math.PI / ((float) SITES) * ((float) i + 2);
        return a + RADIUS + RADIUS * (a == 120 ? Math.cos(angle) : Math.sin(angle));
    }


    // private final static String[] placeNames = {
    //         "MAISON", "ECOLE", "TRAVAIL", "PISCINE", "MAGASIN", "BOUCHERIE",
    //         "BANQUE", " BAR", "RESTAURANT", "DISCOTHEQUE", "BOULANGERIE",
    //         "FITNESS", "PARC", "JARDIN", "CINEMA"
    // };
    //
    // private final static String[] peopleNames = {
    //         "Oliver", "Guille", "Marcel", "Louise", "Margee", "Noelle",
    //         "Robert", "Benben", "Adrien", "Hedvis", "Ericso", "Emilie",
    //         "Garlan", "Elisah", "Patrus"
    // };
}
