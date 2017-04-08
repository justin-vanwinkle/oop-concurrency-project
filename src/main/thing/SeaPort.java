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
    private Map<Integer, Dock> docks = new HashMap<>();
    private ArrayList<Ship> que = new ArrayList<>();
    private Map<Integer, Ship> ships = new HashMap<>();
    private Map<Integer, Person> persons = new HashMap<>();

    public SeaPort(String name, int index, int parent) {
        super(name, index, parent);
    }

    /**
     * Add a dock to this port
     * @param dock the dock to be added
     */
    public void addDock(Dock dock) {
        getDocks().put(dock.getIndex(), dock);
    }

    /**
     * Adds a ship to this port
     * @param ship the ship to be added
     */
    public void addShip(Ship ship) {
        // add this ship to the list of ships
        getShips().put(ship.getIndex(), ship);

        // if this ship is not in a dock, add it to the que
        if (!docks.containsKey(ship.getParentId())) {
            que.add(ship);
        }
    }

    public void addPerson(Person person) {
        getPersons().put(person.getIndex(), person);
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
        docks.forEach((k,v)->sb.append("\n " + v.toString()));

        // iterate over the que and add to string
        sb.append("\n\n --- List of all ships: in que:");
        que.forEach(ship->sb.append("\n  > " + ship.toString()));

        // iterate over all ships and add to string
        sb.append("\n\n --- List of all ships:");
        Map<Integer, Thing> sortedShips = new TreeMap<>(ships);
        sortedShips.forEach((k,v)->sb.append("\n  > " + v.toString()));

        // iterate over all persons and add to string
        sb.append("\n\n --- List of all persons:");
        persons.forEach((k,v)->sb.append("\n  > " + v.toString()));

        return sb.toString();
    }

    /**
     * Adds a child to this port
     * @param child the child to be added
     * @return true if added.  otherwise false
     */
    @Override
    public boolean addChild(Thing child) {
        // if this is a ship, add it to ships
        if(child instanceof Ship) {
            addShip( (Ship)child );
            return true;
        }
        // if this is a dock, add it to docks
        else if(child instanceof Dock) {
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
    public Map<Integer, Dock> getDocks() {
        return docks;
    }

    /**
     * Gets the ships of this port
     * @return a map containing ships
     */
    public Map<Integer, Ship> getShips() {
        return ships;
    }

    /**
     * Gets the persons of this port
     * @return a map containing the persons
     */
    public Map<Integer, Person> getPersons() {
        return persons;
    }

}
