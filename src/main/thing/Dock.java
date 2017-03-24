package main.thing;

import main.thing.ship.Ship;

/**
 * Created by vanwinklej on 3/21/17.
 */
public class Dock extends Thing {
    private Ship ship;

    public Dock(String name, int index, int parent) {
        super(name, index, parent);
    }


    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }
}
