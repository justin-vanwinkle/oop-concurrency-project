package main.thing.ship;

import main.portTime.PortTime;
import main.thing.Thing;

import java.util.ArrayList;

/**
 * Created by vanwinklej on 3/21/17.
 */
public class PassengerShip extends Ship {
    int numberOfOccupiedRooms;
    int numberOfPassengers;
    int numberOfRooms;

    public PassengerShip(String name, int index, int parent, PortTime arrivalTime, PortTime dockTime, double draft,
                         double length, double weight, double width, ArrayList jobs, int numberOfOccupiedRooms,
                         int numberOfPassengers, int numberOfRooms) {
        super(name, index, parent, arrivalTime, dockTime, draft, length, weight, width, jobs);
        this.numberOfOccupiedRooms = numberOfOccupiedRooms;
        this.numberOfPassengers = numberOfPassengers;
        this.numberOfRooms = numberOfRooms;
    }
}
