package main.thing;

import main.portTime.PortTime;
import main.thing.ship.Ship;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vanwinklej on 3/21/17.
 */
public class World extends Thing {
    private Map<Integer, SeaPort> ports;
    private PortTime time;
    private Map<Integer, Thing> objects = new HashMap<>();


    public World() {
        super("World", 0, -1);
        ports = new HashMap<>();
        time = new PortTime(0);
        objects.put(0, this);
    }

    public void addThing(Thing thing) {
        objects.put(thing.getIndex(), thing);

        // add this thing to its parent
        Thing parent = getThing( thing.getParentId() );
        parent.addChild(thing);

        // if this ship went into a dock
        if ( parent instanceof Dock) {
            SeaPort port = (SeaPort)getThing( parent.getParentId() );
            port.addShip( (Ship)thing );
        }

    }

    public Thing getThing(int id) {
        if (objects.containsKey(id)) {
            return objects.get(id);
        }
        else return null;
    }

    public Map<Integer, SeaPort> getPorts() {
        return ports;
    }

    public PortTime getTime() {
        return time;
    }

    public SeaPort addPort(SeaPort port) {
        return ports.put(port.getIndex(), port);
    }

    @Override
    public boolean addChild(Thing child) {
        if(child instanceof SeaPort) {
            addPort( (SeaPort)child );
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(">>>>> The world:");

        ports.forEach((k,v)->sb.append(v.toString()));

        return sb.toString();
    }
}
