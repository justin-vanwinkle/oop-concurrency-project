/**
 * Filename: SeaPort.java
 * Date: 26 March 2017
 * Author: Justin VanWinkle
 * Purpose: This Class represents a port which can contain docks, ships, and persons
 */

package main.thing;


import main.thing.ship.Ship;
import java.util.*;

public class SeaPort extends Thing implements Runnable {
    private ArrayList<Dock> docks = new ArrayList<>();
    private ArrayList<Ship> que = new ArrayList<>();
    private ArrayList<Ship> ships = new ArrayList<>();
    private ArrayList<Person> persons = new ArrayList<>();
    private Thread thread;

    /**
     * Constructor for SeaPort
     * @param name the name of the port
     * @param index the index of the port
     * @param parent the parent index of the port
     */
    public SeaPort(String name, int index, int parent) {
        super(name, index, parent);
        thread = new Thread(this);
        thread.start();
    }

    /**
     * The workflow of a SeaPort thread
     */
    @Override
    public synchronized void run() {

        while (true) {

            boolean dockIsRunning = false;

            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException e) {}

            for (Dock dock : docks) {
                Ship ship = dock.getShip();

                // note that a dock is running
                if (dock.getThread().isAlive()) {
                    dockIsRunning = true;
                }

                // place the next ship
                if (!que.isEmpty() && (ship == null || ship.getStatus() == Ship.Status.JOBS_COMPLETE)) {
                    dock.addChild(que.remove(0));
                }

                // set the thread to terminate
                else if (que.isEmpty() && ship == null && dock.getThread().isAlive()) {
                        dock.stopThread();
                }
            }

            // shut down if no dock is running
            if (!dockIsRunning) {
                break;
            }
        }

        return;
    }

    /**
     * Add a dock to this port
     * @param dock the dock to be added
     */
    public void addDock(Dock dock) {
        getDocks().add(dock);
    }

    /**
     * Adds a ship to this port
     * @param ship the ship to be added
     */
    public boolean addShip(Ship ship, boolean isDocked) {
        // add this ship to the list of ships
        boolean flag = getShips().add(ship);

        if (!isDocked) {
            flag = getQue().add(ship);
        }
        return flag;
    }

    /**
     * Adds a person to this port
     * @param person the person to be added
     */
    public void addPerson(Person person) {
        getPersons().add(person);
    }

    /**
     * Performs a regex comparison to check for a match
     * @param pattern the pattern to match on
     * @return true if a match is found.  Otherwise false.
     */
    @Override
    public boolean checkForMatch(String pattern) {
        // check the super class for a match
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
        return "SeaPort: " + super.toString();
    }

    /**
     * Adds a child to this port
     * @param child the child to be added
     * @return true if added.  otherwise false
     */
    @Override
    public boolean addChild(Thing child) {

        // if this is a dock, add it to docks
        if(child instanceof Dock) {
            addDock( (Dock)child );
            return true;
        }

        // if this is a person, add it to persons
        else if(child instanceof Person) {
            addPerson( (Person)child );
            return true;
        }

        return false;
    }

    /**
     * Gets the docks of this port
     * @return docks
     */
    public ArrayList<Dock> getDocks() {
        return docks;
    }

    /**
     * Gets the ships of this port
     * @return ships
     */
    public ArrayList<Ship> getShips() {
        return ships;
    }

    /**
     * Gets the persons of this port
     * @return the persons
     */
    public ArrayList<Person> getPersons() {
        return persons;
    }

    /**
     * Gets the que of this port
     * @return the que
     */

    public ArrayList<Ship> getQue() {
        return que;
    }

    /**
     * gets the thread this port is operating on
     * @return the thread
     */
    public Thread getThread() {
        return thread;
    }
}
