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

    @Override
    public boolean addChild(Thing child) {
        if (child instanceof Ship) {
            ship = (Ship) child;

        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Dock: " + super.toString());
        if (ship != null) {
            sb.append("\n  Ship: " + ship.toString());
        }
        else sb.append("\n No Ship");
        return sb.toString() ;
    }
}
