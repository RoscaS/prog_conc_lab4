package com.ch.smartBike;

import com.ch.smartBike.actors.Person;
import com.ch.smartBike.actors.Place;
import com.ch.smartBike.actors.Site;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Application setup class.
 * You might change values of:
 *  <ul>
 *      <li>Default development values</li>
 *      <li>Default settings</li>
 *  </ul>
 */
public class Settings {

    static {
        /*------------------------------*\
       	|*	Default development values  *|
       	\*------------------------------*/
        // Total number of citizen
        PEOPLES = 10;
        // Number of sites
        SITES = 5;
        // Bikes slots per site
        SLOTS = 5;
        // Total number of bikes
        BIKES = 30;

        /*------------------------------*\
       	|*	    Default settings        *|
       	\*------------------------------*/
        // If set, default development values are replaced by user input
        PRODUCTION = false;
        // Print activity logging on stdout
        LOGGING = true;
        // Used to paint the simulation
        RADIUS = 200;
        // Minimum numbers of slots per site allowed
        MIN_SLOTS = 4;
        // Maximum number of bikes that the truck can carry
        MAX_LOAD_TRUCK = 4;
        // Free slots per site on first iteration
        INITIAL_FREE_SLOTS = 2;
        // Left boundary for random working time
        MIN_WORK_TIME = 1000;
        // Right boundary for random working time
        MAX_WORK_TIME = 10000;

        /*------------------------------*\
       	|*	    Computed properties     *|
       	\*------------------------------*/
        // Initial number of bikes available in Depot
        DEPOT_AVAILABLE_BIKES = depotAvailableBikes();
        // Initial number of bikes available on each Place
        PLACE_AVAILABLE_BIKES = placeAvailableBikes();
        // List of dummy Places
        SITE_LIST = buildPlacesList();
        // List of dummy Person
        PERSON_LIST = buildPersonList();
        // Usage of the program
        USAGE = usage();
    }

    /*------------------------------------------------------------------*\
   	|*							User input       						*|
   	\*------------------------------------------------------------------*/

    public static int PEOPLES;
    public static int SITES;
    public static int SLOTS;
    public static int BIKES;

    /*------------------------------------------------------------------*\
   	|*							Constant values 						*|
   	\*------------------------------------------------------------------*/

    public final static boolean PRODUCTION;
    public final static boolean LOGGING;

    public final static int RADIUS;
    public final static int MIN_SLOTS;

    public final static int INITIAL_FREE_SLOTS;

    public final static int PLACE_AVAILABLE_BIKES;

    public final static int MAX_LOAD_TRUCK;

    public final static int MIN_WORK_TIME;
    public final static int MAX_WORK_TIME;


    public final static int DEPOT_AVAILABLE_BIKES;
    public final static List<Site> SITE_LIST;
    public final static List<Person> PERSON_LIST;

    public final static String USAGE;

    /*------------------------------------------------------------------*\
   	|*							Public Methods   						*|
   	\*------------------------------------------------------------------*/

    public static void inputErrorExit() {
        System.out.println(USAGE);
        System.exit(-1);
    }

    public static void handleInitialValues(String[] args) {
        if (PRODUCTION) {
            captureInput(args);
        }
    }

    /*------------------------------*\
   	|*				Getters			*|
   	\*------------------------------*/


    /*------------------------------*\
   	|*				Setters			*|
   	\*------------------------------*/

    public static void setPEOPLES(String input) {
        int value = parseInput(input);
        if (value > PERSON_LIST.size()) {
            inputErrorExit();
        }
        PEOPLES = value;
    }

    public static void setSITES(String input) {
        int value = parseInput(input);
        if (value > SITE_LIST.size()) {
            inputErrorExit();
        }
        SITES = value;
    }

    public static void setSLOTS(String input) {
        int value = parseInput(input);
        if (value < MIN_SLOTS) {
            inputErrorExit();
        }
        SLOTS = value;
    }

    public static void setBIKES(String input) {
        int value = parseInput(input);
        if (value < SITES * (SLOTS - 2) + 3) {
            inputErrorExit();
        }
        BIKES = value;
    }

    /*------------------------------------------------------------------*\
   	|*							Private Methods 						*|
   	\*------------------------------------------------------------------*/

    private static void captureInput(String[] args) {
        if (args.length < 5) {
            Settings.inputErrorExit();
        }
        Settings.setPEOPLES(args[1]);
        Settings.setSITES(args[2]);
        Settings.setSLOTS(args[3]);
        Settings.setBIKES(args[4]);
    }

    private static int parseInput(String input) {
        int value = 0;
        try {
            value = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            inputErrorExit();
        }
        return value;
    }

    private static double coord(int a, int i) {
        double angle = 2.0 * Math.PI / ((float) SITES) * ((float) i + 2);
        return a + RADIUS + RADIUS * (a == 120 ? Math.cos(angle) : Math.sin(angle));
    }

    private static String usage() {
        StringBuilder sb = new StringBuilder();
        sb.append("usage: SmartCity <sites> <citizen> <slots> <bikes>");
        sb.append("\n\tsites:   \tNumber of sites       \tinteger [2; ");
        sb.append(SITE_LIST.size()).append("]");
        sb.append("\n\tcitizen: \tNumber of citizen   \tinteger [1; ");
        sb.append(PERSON_LIST.size()).append("]");
        sb.append("\n\tslots:   \tSlots per site        \tinteger > 4");
        sb.append("\n\tbikes:   \ttotal number of bikes \tbikes >= sites * (slots - 2) + 3");
        return sb.toString();
    }

    /*------------------------------------------------------------------*\
   	|*							Computed properties						*|
   	\*------------------------------------------------------------------*/

    private static int depotAvailableBikes() {
        return BIKES - (SLOTS - INITIAL_FREE_SLOTS) * SITES;
    }

    private static int placeAvailableBikes() {
        return SLOTS - INITIAL_FREE_SLOTS;
    }

    private static List<Site> buildPlacesList() {
        List<Site> places = new ArrayList<>();
        int bikes = SLOTS - INITIAL_FREE_SLOTS;
        String[] placeNames = {
                "MAISON", "ECOLE", "TRAVAIL", "PISCINE", "MAGASIN", "BOUCHERIE",
                "BANQUE", " BAR", "RESTAURANT", "DISCOTHEQUE", "BOULANGERIE",
                "FITNESS", "PARC", "JARDIN", "CINEMA"
        };

        for (int i = 1; i <= SITES; i++) {
            Point2D position = new Point2D.Double(coord(120, i - 1), coord(10, i - 1));
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

        for (int i = 1; i <= PEOPLES; i++) {
            Person person = new Person(peopleNames[i - 1]);
            people.add(person);
        }
        return people;
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
