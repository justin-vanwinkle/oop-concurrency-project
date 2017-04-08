/**
 * Filename: SeaPort.java
 * Date: 26 March 2017
 * Author: Justin VanWinkle
 * Purpose: This Class represents a port which can contain docks, ships, and persons
 */

package main.thing;


import main.thing.ship.Ship;

import java.util.*;

public class SeaPort extends Thing {
    private ArrayList<Dock> docks = new ArrayList<>();
    private ArrayList<Ship> que = new ArrayList<>();
    private ArrayList<Ship> ships = new ArrayList<>();
    private ArrayList<Person> persons = new ArrayList<>();

    public SeaPort(String name, int index, int parent) {
        super(name, index, parent);
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
    public void addShip(Ship ship, boolean isDocked) {
        // add this ship to the list of ships
        getShips().add(ship);

        if (!isDocked) {
            getQue().add(ship);
        }
    }

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
        // get a string builder and append the super class string
        StringBuilder sb = new StringBuilder("\n\n\nSeaPort: " + super.toString() + "\n");

        // iterate over the docks and append each
        docks.forEach( dock-> sb.append("\n " + dock.toString()) );

        // iterate over the que and add to string
        sb.append("\n\n --- List of all ships: in que:");
        getQue().forEach(ship -> sb.append("\n  > " + ship.toString()) );

        // iterate over all ships and add to string
        sb.append("\n\n --- List of all ships:");
        ships.forEach( ship -> sb.append("\n  > " + ship.toString()) );

        // iterate over all persons and add to string
        sb.append("\n\n --- List of all persons:");
        persons.forEach( port -> sb.append("\n  > " + port.toString()) );

        return sb.toString();
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
     * @return a map containing docks
     */
    public ArrayList<Dock> getDocks() {
        return docks;
    }

    /**
     * Gets the ships of this port
     * @return a map containing ships
     */
    public ArrayList<Ship> getShips() {
        return ships;
    }

    /**
     * Gets the persons of this port
     * @return a map containing the persons
     */
    public ArrayList<Person> getPersons() {
        return persons;
    }

    public ArrayList<Ship> getQue() {
        return que;
    }
}
