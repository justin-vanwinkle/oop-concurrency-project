package main.thing.ship;

import main.portTime.PortTime;

import java.util.ArrayList;

/**
 * Created by vanwinklej on 3/21/17.
 */
public class CargoShip extends Ship {
    double cargoValue;
    double cargoVolume;
    double cargoWeight;

    public CargoShip(String name, int index, int parent, PortTime arrivalTime, PortTime dockTime, double draft,
                     double length, double weight, double width, ArrayList jobs, double cargoValue, double cargoVolume,
                     double cargoWeight) {
        super(name, index, parent, arrivalTime, dockTime, draft, length, weight, width, jobs);
        this.cargoValue = cargoValue;
        this.cargoVolume = cargoVolume;
        this.cargoWeight = cargoWeight;
    }
}
