package main.thing;

import main.thing.ship.CargoShip;
import main.thing.ship.PassengerShip;
import main.thing.ship.Ship;

import java.util.*;

/**
 * Created by vanwinklej on 3/21/17.
 */
public class SeaPort extends Thing {
    private Map<Integer, Dock> docks = new HashMap<>();
    private ArrayList<Ship> que = new ArrayList<>();
    private Map<Integer, Ship> ships = new HashMap<>();
    private Map<Integer, Person> persons = new HashMap<>();

    public SeaPort(String name, int index, int parent) {
        super(name, index, parent);
    }

    public void addDock(Dock dock) {
        getDocks().put(dock.getIndex(), dock);
    }

    public void addShip(Ship ship) {
        getShips().put(ship.getIndex(), ship);
        if (!docks.containsKey(ship.getParentId())) {
            que.add(ship);
        }
    }

    public void addPerson(Person person) {
        getPersons().put(person.getIndex(), person);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("\n\nSeaPort: " + super.toString() + "\n");

        docks.forEach((k,v)->sb.append("\n " + v.toString()));

        sb.append("\n\n --- List of all ships: in que:");
        que.forEach(ship->sb.append("\n  > " + ship.toString()));

        sb.append("\n\n --- List of all ships:");
        Map<Integer, Thing> sortedShips = new TreeMap<>(ships);
        sortedShips.forEach((k,v)->sb.append("\n  > " + v.toString()));

        sb.append("\n\n --- List of all persons:");
        persons.forEach((k,v)->sb.append("\n  > " + v.toString()));

        return sb.toString();
    }

    @Override
    public boolean addChild(Thing child) {
        if(child instanceof Ship) {
            addShip( (Ship)child );
        }
        else if(child instanceof Dock) {
            addDock( (Dock)child );
        }
        else if(child instanceof Person) {
            addPerson( (Person)child );
        }

        return true;
    }

    public Map<Integer, Dock> getDocks() {
        return docks;
    }

    public ArrayList<Ship> getQue() {
        return que;
    }

    public Map<Integer, Ship> getShips() {
        return ships;
    }

    public Map<Integer, Person> getPersons() {
        return persons;
    }
}
