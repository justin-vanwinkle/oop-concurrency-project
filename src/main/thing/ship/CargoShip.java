/**
 * Filename: CargoShip.java
 * Date: 26 March 2017
 * Author: Justin VanWinkle
 * Purpose: This Class represents a cargo ship that might exist in the port
 */

package main.thing.ship;

import main.thing.Job;
import main.thing.Thing;

public class CargoShip extends Ship{
    double cargoValue;
    double cargoVolume;
    double cargoWeight;


    /**
     * The constructor for this class
     * @param name the name of the ship
     * @param index the index of the ship
     * @param parent the parent to which this ship belongs
     * @param weight the weight of this ship
     * @param length the length of this ship
     * @param width the width of this ship
     * @param draft the draft of this ship
     * @param cargoValue the value of the cargo
     * @param cargoVolume the volume of cargo
     * @param cargoWeight the weigh of the cargo
     */
    public CargoShip(String name, int index, int parent, double weight, double length, double width, double draft,
                         double cargoValue, double cargoVolume, double cargoWeight) {
        super(name, index, parent, weight, length, width, draft);
        this.cargoValue = cargoValue;
        this.cargoVolume = cargoVolume;
        this.cargoWeight = cargoWeight;
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
        // get a string builder and add the super string to it
        StringBuilder sb = new StringBuilder("Cargo ship: " + super.toString());

        // iterate over the jobs of this ship and append them to the string builder
        for (Job job: getJobs()) {
            sb.append("\n" + job);
        }
        return sb.toString();
    }


}
