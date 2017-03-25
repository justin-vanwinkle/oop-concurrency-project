package main.thing;

import main.thing.ship.CargoShip;
import main.thing.ship.PassengerShip;
import main.thing.ship.Ship;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
        getDocks().put(dock.getParentId(), dock);
    }

    public void addShip(Ship ship) {
        getShips().put(ship.getParentId(), ship);
    }

    public void addPerson(Person person) {
        getPersons().put(person.getParentId(), person);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder("Sea Port: ");
        sb.append(getName() + " ");
        sb.append(getIndex());
        return sb.toString();
    }

    @Override
    public boolean addChild(Thing child) {
        if(child instanceof CargoShip || child instanceof PassengerShip) {
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
