package main.thing;

import main.portTime.PortTime;

import java.util.ArrayList;

/**
 * Created by vanwinklej on 3/21/17.
 */
public class World extends Thing {
    private ArrayList<SeaPort> ports;
    private PortTime time;

    public World() {
        super("World", 0, -1);
        ports = new ArrayList<>();
        time = new PortTime(0);
    }

    public ArrayList<SeaPort> getPorts() {
        return ports;
    }

    public PortTime getTime() {
        return time;
    }

    public boolean addPort(SeaPort port) {
        return ports.add(port);
    }
}
