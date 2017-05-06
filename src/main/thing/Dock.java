/**
 * Filename: Dock.java
 * Date: 26 March 2017
 * Author: Justin VanWinkle
 * Purpose: This Class represents a dock that might exist in a port
 */

package main.thing;

import main.thing.ship.Ship;

public class Dock extends Thing implements Runnable{
    private Ship ship;
    private Thread thread;
    private boolean runThread = true;

    /**
     * The constructor of Dock
     * @param name the name of dock
     * @param index the index of dock
     * @param parent the index of the parent of dock
     */
    public Dock(String name, int index, int parent) {
        super(name, index, parent);
        thread = new Thread(this);
        getThread().start();
    }

    /**
     * The workflow of Dock threads
     */
    @Override
    public void run() {

        while (runThread) {

            try {
                Thread.sleep(100);
            }
            catch (InterruptedException e) {}

            if (ship != null) {

                // dock the ship if it isn't docked
                if (!ship.isDocked()) {
                    ship.dock();
                }

                // if the ship had no jobs, release it immediately
                if (ship.getJobs().size() == 0) {
                    ship.setStatus(Ship.Status.JOBS_COMPLETE);
                }

                // if jobs complete, release the ship
                if (ship.getStatus() == Ship.Status.JOBS_COMPLETE) {
                    ship.undock();
                    ship = null;
                }

            }
        }

        return;
    }

    /**
     * Places a ship in this dock
     * @param child The ship to take this dock
     * @return true if a ship takes this dock.  Otherwise false.
     */
    @Override
    public boolean addChild(Thing child) {

        // if this is a ship, place it in this dock
        if (child instanceof Ship) {
            ship = (Ship) child;
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

    /**
     * Gets the thread of the dock
     * @return the thread of the dock
     */
    public Thread getThread() {
        return thread;
    }

    /**
     * Lowers the flag for the dock thread to continue running.
     */
    public void stopThread() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (ship == null) {
            runThread = false;
        }
    }
}
