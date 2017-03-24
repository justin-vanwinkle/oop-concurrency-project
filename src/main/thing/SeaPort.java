package main.thing;

import main.thing.ship.Ship;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vanwinklej on 3/21/17.
 */
public class SeaPort extends Thing {
    Map<Integer, Dock> docks = new HashMap<>();
    ArrayList<Ship> que = new ArrayList<>();
    Map<Integer, Ship> ships = new HashMap<>();
    Map<Integer, Person> persons = new HashMap<>();

    public SeaPort(String name, int index, int parent) {
        super(name, index, parent);
    }

    public void addDock(Dock dock) {
        docks.put(dock.getParent(), dock);
    }

    public void addShip(Ship ship) {
        ships.put(ship.getParent(), ship);
    }

    public void addPerson(Person person) {
        persons.put(person.getParent(), person);
    }

    @Override
    public String toString() {
        String name = "Name: " + getName() + "\n";
        String index = "Index: " + getIndex() + "\n";
        String parent = "Parent: " + getParent() + "\n";
        return "PORT:\n" + name + index + parent;
    }

}
