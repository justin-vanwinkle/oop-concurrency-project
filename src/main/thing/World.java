/**
 * Filename: World.java
 * Date: 26 March 2017
 * Author: Justin VanWinkle
 * Purpose: This Class represents the world and contains all of the ports and objects that might be in it and relative
 */

package main.thing;

import main.portTime.PortTime;
import main.thing.ship.Ship;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class World extends Thing {
    private ArrayList<SeaPort> ports;
    private PortTime time;
    private Map<Integer, Thing> objects = new HashMap<>();


    /**
     * The constructor for this class
     */
    public World() {
        super("World", 0, -1);
        ports = new ArrayList<>();
        time = new PortTime(0);
        objects.put(0, this);
    }

    /**
     * Performs a search through all physical objects in the world
     * @param pattern the pattern on which to match
     * @return an ArrayList of Things that have a match
     */
    public ArrayList<Thing> search(String pattern) {
        // get a list
        ArrayList<Thing> matches = new ArrayList<>();

        // iterate over ports
        ports.forEach( port -> {

            // check port for match
            if (port.checkForMatch(pattern))
                matches.add(port);

            // iterate over docks
            port.getDocks().forEach( dock -> {
                if (dock.checkForMatch(pattern))
                    matches.add(dock);
            });

            // iterate over ships
            port.getShips().forEach( ship -> {
              if (ship.checkForMatch(pattern))
                  matches.add(ship);
            });

            // iterate over persons
            port.getPersons().forEach( person -> {
                if (person.checkForMatch(pattern))
                    matches.add(person);
            });
        });

        return matches;
    }

    /**
     * Adds a thing to the master map of this world for indexing
     * @param thing the thing to be added
     */
    public void addThingToParent(Thing thing) {

        // put this thing in the map
        objects.put(thing.getIndex(), thing);

        // add this thing to its parent
        Thing parent = getThing( thing.getParentId() );

        // if this is a ship
        if ( thing instanceof Ship ) {
            SeaPort port;

            // get the port
            if ( parent instanceof Dock ) {
                port = (SeaPort)getThing( parent.getParentId() );
                // pass the ship to the port for placement
                port.addShip( (Ship)thing, true );
            }
            else {
                port = (SeaPort)getThing( parent.getIndex() );
                // pass the ship to the port for placement
                port.addShip( (Ship)thing, false );
            }
        }

        // add directly to parent
        parent.addChild(thing);
    }

    /**
     * Gets a thing out of the indexing map
     * @param id the index of the item to retrieve
     * @return the Thing requested if it exists.  Otherwise null.
     */
    public Thing getThing(int id) {
        // if this key exists
        if (objects.containsKey(id)) {
            return objects.get(id);
        }
        else return null;
    }

    /**
     * Gets the ports that belong to this world
     * @return a Map containing all of the ports in this world
     */
    public ArrayList<SeaPort> getPorts() {
        return ports;
    }

    /**
     * Adds a port to this world
     * @param port the port to be added
     * @return
     */
    public boolean addPort(SeaPort port) {
        return ports.add(port);
    }

    /**
     * Adds a child to this world
     * @param child the child to be added
     * @return true if added. otherwise false
     */
    @Override
    public boolean addChild(Thing child) {
        // if this is a port, add it
        if(child instanceof SeaPort) {
            addPort( (SeaPort)child );
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
        // get a string builder
        StringBuilder sb = new StringBuilder(">>>>> The world:");

        // iterate over the ports and add each one to the string
        ports.forEach( port ->sb.append(port.toString()) );

        return sb.toString();
    }


}
