package main.thing;

/**
 * Created by vanwinklej on 3/21/17.
 */
public class Thing implements Comparable<Thing> {

    private int index;
    private int parent;
    private String name;

    public Thing() {
        this.name = "";
        this.index = -1;
        this.parent = -1;
    }

    public Thing(String name, int index, int parent) {
        this.index = index;
        this.parent = parent;
        this.name = name;
    }

    public boolean checkForMatch(String pattern) {
        if (name.contains(pattern)) {
            return true;
        }
        if (Integer.toString(index).contains(pattern)) {
            return true;
        }
        return false;
    }

    public boolean addChild(Thing child) {
        return false;
    }

    @Override
    public int compareTo(Thing o) {
        return 0;
    }

    public int getIndex() {
        return index;
    }

    public int getParentId() {
        return parent;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " " + index;
    }
}
