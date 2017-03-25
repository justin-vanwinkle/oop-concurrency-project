package main.thing;

import main.thing.ship.CargoShip;
import main.thing.ship.PassengerShip;
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

    @Override
    public boolean addChild(Thing child) {
        if (child instanceof Ship) {
            ship = (Ship) child;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Dock: ");
        sb.append(getName() + " ");
        sb.append(getIndex() + "\n");
        sb.append("\t" + ship);
        return sb.toString() ;
    }
}
