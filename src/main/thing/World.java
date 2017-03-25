package main.thing;

import main.portTime.PortTime;

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
    }

    public void addThing(Thing thing) {
        objects.put(thing.getIndex(), thing);
    }

    public Thing getThing(int id) {
        return objects.get(id);
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
}
