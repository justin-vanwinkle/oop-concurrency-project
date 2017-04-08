/**
 * Filename: PassengerShip.java
 * Date: 26 March 2017
 * Author: Justin VanWinkle
 * Purpose: This Class represents a passenger ship that might exist in the port
 */

package main.thing.ship;

import main.thing.Job;

public class PassengerShip extends Ship {
    int numberOfOccupiedRooms;
    int numberOfPassengers;
    int numberOfRooms;


    /**
     * The constructor for this class
     * @param name the name of this ship
     * @param index the index of this ship
     * @param parent the parent to which this ship belongs
     * @param weight the weight of this ship
     * @param length the length of this ship
     * @param width the width of this ship
     * @param draft the draft of this ship
     * @param numberOfPassengers the number of passengers on this ship
     * @param numberOfRooms the number of rooms on this ship
     * @param numberOfOccupiedRooms the number of occupied rooms on this ship
     */
    public PassengerShip(String name, int index, int parent, double weight, double length, double width, double draft,
                         int numberOfPassengers, int numberOfRooms, int numberOfOccupiedRooms) {
        super(name, index, parent, weight, length, width, draft);
        this.numberOfOccupiedRooms = numberOfOccupiedRooms;
        this.numberOfPassengers = numberOfPassengers;
        this.numberOfRooms = numberOfRooms;
    }

    /**
     * Performs a regex comparison to check for a match
     * @param pattern the pattern to match on
     * @return true if a match is found.  Otherwise false.
     */
    @Override
    public boolean checkForMatch(String pattern) {
        //TODO search on times once they are implemented

        if (super.checkForMatch(pattern)) {
            return true;
        }

        return false;
    }

    /**
     * Creates a string representation of this class
     * @return a string representation of this class
     */
    @Override
    public String toString() {
        // get a string builder and append the parent string
        StringBuilder sb = new StringBuilder("Passenger ship: " + super.toString());

        // iterate over the jobs and append them to the string builder
        for (Job job : getJobs()) {
            sb.append("\n" + job);
        }
        return sb.toString();
    }

}
