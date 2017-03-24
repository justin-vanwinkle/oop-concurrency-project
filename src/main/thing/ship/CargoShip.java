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


    public CargoShip(String name, int index, int parent, double weight, double length, double width, double draft,
                         int cargoValue, int cargoVolume, int cargoWeight) {
        super(name, index, parent, weight, length, width, draft);
        this.cargoValue = cargoValue;
        this.cargoVolume = cargoVolume;
        this.cargoWeight = cargoWeight;
    }
}
