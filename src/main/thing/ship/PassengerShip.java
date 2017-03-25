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


    public PassengerShip(String name, int index, int parent, double weight, double length, double width, double draft,
                         int numberOfPassengers, int numberOfRooms, int numberOfOccupiedRooms) {
        super(name, index, parent, weight, length, width, draft);
        this.numberOfOccupiedRooms = numberOfOccupiedRooms;
        this.numberOfPassengers = numberOfPassengers;
        this.numberOfRooms = numberOfRooms;
    }

    @Override
    public String toString() {
        return "Passenger ship: " + getName() + " " + getIndex();
    }
}
