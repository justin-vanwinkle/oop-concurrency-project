/**
 * Filename: Thing.java
 * Date: 26 March 2017
 * Author: Justin VanWinkle
 * Purpose: This Class serves as a superclass for all things in the world
 */

package main.thing;

import java.lang.*;
import java.util.regex.Pattern;

public abstract class Thing {

    private int index;
    private int parent;
    private String name;

    /**
     * A constructor for this class
     */
    public Thing() {
        this.name = "";
        this.index = -1;
        this.parent = -1;
    }

    /**
     * A constructor for this class
     *
     * @param name   the name of this thing
     * @param index  the index of this thing
     * @param parent the parent to which this thing belongs
     */
    public Thing(String name, int index, int parent) {
        this.index = index;
        this.parent = parent;
        this.name = name;
    }

    /**
     * Performs a regex comparison to check for a match
     *
     * @param pattern the pattern to match on
     * @return true if a match is found.  Otherwise false.
     */
    public boolean checkForMatch(String pattern) {
        // compile the pattern
        Pattern r = Pattern.compile(pattern);

        // if the name matches
        if (r.matcher(name).find()) {
            return true;
        }

        // if the index matches
        if (r.matcher(Integer.toString(index)).find()) {
            return true;
        }

        return false;
    }

    /**
     * Adds a child to this thing.
     * This should not be used.
     *
     * @param child the child to be added
     * @return
     */
    public boolean addChild(Thing child) {
        //TODO fix until you make this class abstract
        return false;
    }

    /**
     * gets the index of this thing
     *
     * @return the index of this thing
     */
    public int getIndex() {
        return index;
    }

    /**
     * gets the index of the parent to which this thing belongs
     *
     * @return the index of this thing's parent
     */
    public int getParentId() {
        return parent;
    }

    /**
     * gets the name of this thing
     *
     * @return the name of this thing
     */
    public String getName() {
        return name;
    }

    /**
     * Creates a string representation of this class
     *
     * @return a string representation of this class
     */
    @Override
    public String toString() {
        return name + " " + index;
    }

}
