package main.thing;

/**
 * Created by vanwinklej on 3/21/17.
 */
public class Thing implements Comparable<Thing> {

    private int index;
    private int parent;
    private String name;

    public Thing(String name, int index, int parent) {
        this.index = index;
        this.parent = parent;
        this.name = name;
    }

    @Override
    public int compareTo(Thing o) {
        return 0;
    }

    public int getIndex() {
        return index;
    }

    public int getParent() {
        return parent;
    }

    public String getName() {
        return name;
    }
}
