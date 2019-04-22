package com.ch.smartBike.threads;

import com.ch.smartBike.MyCity;
import com.ch.smartBike.Settings;
import com.ch.smartBike.actors.Depot;
import com.ch.smartBike.actors.Place;
import com.ch.smartBike.actors.Site;
import com.ch.smartBike.actors.Truck;

import java.util.ArrayList;
import java.util.List;

public class TruckJob extends BaseJob {

    private List<Site> tour;
    private Truck truck;

	/*------------------------------------------------------------------*\
	|*							Constructors						  *|
	\*------------------------------------------------------------------*/

    public TruckJob(Truck truck, MyCity city) {
        super(truck, city.depot, Settings.SITE_LIST.get(0), city);
        this.truck = (Truck) entity;

        this.tour = new ArrayList<>();
        this.tour.addAll(Settings.SITE_LIST);
        this.tour.add(city.depot);
    }

    /*------------------------------------------------------------------*\
   	|*							Public Methods 							*|
   	\*------------------------------------------------------------------*/

    @Override
    public void run() {

        int index = 1;

        while (true) {
            if (index == tour.size()) {
                index = 0;
            }

            updateCoords();

            if (start instanceof Place) {

                if (start.getAvailableBikes() > Settings.PLACE_AVAILABLE_BIKES) {
                    pickBikesOnPlace();
                }
                if (start.getAvailableBikes() < Settings.PLACE_AVAILABLE_BIKES) {
                    dropBikesOnPlace();
                }
            } else if (start instanceof Depot) {

                Depot depot = (Depot) start;

                depot.incrementBikes(truck.getBikesLoaded());
                truck.decrementBikes(truck.getBikesLoaded());

                int value = Math.min(2, depot.getAvailableBikes());
                depot.decrementBikes(value);
                truck.incrementBikes(value);

                simulateBreakTime();
            }

            logging();
            updateDraw();
            repaint();

            start = destination;
            destination = tour.get(index);
            index++;
        }
    }

    private void logging() {
        if (Settings.LOGGING) {
            StringBuilder sb = new StringBuilder("\n <TRUCK>");
            sb.append("\t").append("leave ").append(start.getName());
            sb.append("\t(").append(tour.indexOf(start) + 1);
            sb.append("/").append(tour.size()).append(")");
            sb.append("\t has: ").append(truck.getBikesLoaded());
            sb.append("/").append(Settings.MAX_LOAD_TRUCK).append(" bikes loaded\n");
            System.out.println(sb.toString());
        }
    }

    /*------------------------------*\
	|*				Getters			*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Private Methods 					  *|
	\*------------------------------------------------------------------*/

    private void simulateBreakTime() {
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * NEED TO BE PROTECTED ON CALL
     *
     * @return
     */
    private void pickBikesOnPlace() {
        int a = start.getAvailableBikes() - Settings.PLACE_AVAILABLE_BIKES;
        int b = Settings.MAX_LOAD_TRUCK - truck.getBikesLoaded();
        int value = Math.min(a, b);
        truck.incrementBikes(value);
        start.decrementBikes(value);
    }

    /**
     * NEED TO BE PROTECTED ON CALL
     *
     * @return
     */
    private void dropBikesOnPlace() {
        int a = Settings.PLACE_AVAILABLE_BIKES - start.getAvailableBikes();
        int b = truck.getBikesLoaded();
        int value = Math.min(a, b);
        truck.decrementBikes(value);
        start.incrementBikes(value);
    }

	/*------------------------------------------------------------------*\
	|*							Private Attributs 					  *|
	\*------------------------------------------------------------------*/
}
