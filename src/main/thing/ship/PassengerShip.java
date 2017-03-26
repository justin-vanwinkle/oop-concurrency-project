package main.thing.ship;

import main.thing.Job;

/**
 * Created by vanwinklej on 3/21/17.
 */
public class PassengerShip extends Ship {
    int numberOfOccupiedRooms;
    int numberOfPassengers;
    int numberOfRooms;


    public PassengerShip(String name, int index, int parent, double weight, double length, double width, double draft,
                         int numberOfPassengers, int numberOfRooms, int numberOfOccupiedRooms) {
        super(name, index, parent, weight, length, width, draft);
        this.numberOfOccupiedRooms = numberOfOccupiedRooms;
        this.numberOfPassengers = numberOfPassengers;
        this.numberOfRooms = numberOfRooms;
    }

    @Override
    public boolean checkForMatch(String pattern) {
        //TODO search on times once they are implemented

        if (super.checkForMatch(pattern)) {
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Passenger ship: " + super.toString());

            for (Job job : getJobs()) {
                sb.append("\n" + job);
            }
        return sb.toString();
    }
}
