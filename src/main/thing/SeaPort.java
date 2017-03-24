package main.thing;

import main.thing.ship.Ship;

import java.util.ArrayList;

/**
 * Created by vanwinklej on 3/21/17.
 */
public class SeaPort extends Thing {
    ArrayList<Dock> docks = new ArrayList<>();
    ArrayList<Ship> que = new ArrayList<>();
    ArrayList<Ship> ships = new ArrayList<>();
    ArrayList<Person> persons = new ArrayList<>();

    public SeaPort(String name, int index, int parent) {
        super(name, index, parent);
    }

    public void addDock(Dock dock) {
        docks.add(dock.getParent(), dock);
    }

    public void addShip(Ship ship) {
        ships.add(ship.getParent(), ship);
    }

    public void addPerson(Person person) {
        persons.add(person.getParent(), person);
    }

}
