package main.thing.ship;

import main.thing.Job;

/**
 * Created by vanwinklej on 3/21/17.
 */
public class CargoShip extends Ship {
    double cargoValue;
    double cargoVolume;
    double cargoWeight;


    public CargoShip(String name, int index, int parent, double weight, double length, double width, double draft,
                         double cargoValue, double cargoVolume, double cargoWeight) {
        super(name, index, parent, weight, length, width, draft);
        this.cargoValue = cargoValue;
        this.cargoVolume = cargoVolume;
        this.cargoWeight = cargoWeight;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Cargo ship: " + super.toString());

        for (Job job: getJobs()) {
            sb.append("\n" + job);
        }
        return sb.toString();
    }

}
