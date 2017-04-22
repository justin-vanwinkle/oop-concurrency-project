/**
 * Filename: Dock.java
 * Date: 26 March 2017
 * Author: Justin VanWinkle
 * Purpose: This Class represents a dock that might exist in a port
 */

package main.thing;

import main.thing.ship.Ship;

public class Dock extends Thing {
    private Ship ship;

    public Dock(String name, int index, int parent) {
        super(name, index, parent);
    }


    /**
     * Places a ship in this dock
     * @param child The ship to take this dock
     * @return true if a ship takes this dock.  Otherwise false.
     */
    @Override
    public boolean addChild(Thing child) {
        System.out.println("Ship " + child.getName() + " going into dock " + getName());

        // if this is a ship, place it in this dock
        if (child instanceof Ship) {
            ship = (Ship) child;
            ship.setStatus(Ship.Status.JOBS_IN_PROGRESS);
            //ship.getThread().start();
            ship.getJobs().forEach(job -> job.toggleGoFlag());
            return true;
        }

        return false;
    }

    /**
     * A getter for the ship of this dock
     * @return ship
     */
    public Ship getShip() {
        return ship;
    }

    /**
     * Creates a string representation of this class
     * @return a string representation of this class
     */
    @Override
    public String toString() {
        return "Dock: " + super.toString() ;
    }

}
