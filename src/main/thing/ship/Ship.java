/**
 * Filename: Ship.java
 * Date: 26 March 2017
 * Author: Justin VanWinkle
 * Purpose: This abstract class serves as the super class for types of ships
 */

package main.thing.ship;

import main.portTime.PortTime;
import main.thing.Job;
import main.thing.Thing;
import java.util.ArrayList;
import java.util.Comparator;

public abstract class Ship extends Thing {
    PortTime arrivalTime
            , dockTime;
    private double draft;
    private double length;
    private double weight;
    private double width;
    private ArrayList<Job> jobs = new ArrayList<>();

    public double getDraft() {
        return draft;
    }

    public double getLength() {
        return length;
    }

    public double getWeight() {
        return weight;
    }

    public double getWidth() {
        return width;
    }

    /**
     * The constructor for this class
     * @param name the name of the ship
     * @param index the index of the ship
     * @param parent the parent to which this ship belongs
     * @param weight the weight of this ship
     * @param length the length of this ship
     * @param width the width of this ship
     * @param draft the draft of this ship
     */
    public Ship(String name, int index, int parent, double weight, double length, double width, double draft) {
        super(name, index, parent);
        this.weight = weight;
        this.length = length;
        this.width = width;
        this.draft = draft;
    }


    /**
     * Performs a regex comparison to check for a match
     * @param pattern the pattern to match on
     * @return true if a match is found.  Otherwise false.
     */
    @Override
    public boolean checkForMatch(String pattern) {
        //TODO search on times once they are implemented

        if (super.checkForMatch(pattern)) {
            return true;
        }

        return false;
    }

    /**
     * Creates a string representation of this class
     * @return a string representation of this class
     */
    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * Gets the jobs from this ship
     * @return an ArrayList of jobs for this ship
     */
    public ArrayList<Job> getJobs() {
        return jobs;
    }


    /**
     * A comparator for ship weights
     */
    public static Comparator<Ship> weightComparator = new Comparator<Ship>() {
                public int compare(Ship s1, Ship s2) {
                    return Double.compare(s1.getWeight(),
                            s2.getWeight());
                }
            };

    /**
     * A comparator for ship drafts
     */
    public static Comparator<Ship> draftComparator = new Comparator<Ship>() {
        public int compare(Ship s1, Ship s2) {
            return Double.compare(s1.getDraft(),
                    s2.getDraft());
        }
    };

    /**
     * A comparator for ship width
     */
    public static Comparator<Ship> widthComparator = new Comparator<Ship>() {
        public int compare(Ship s1, Ship s2) {
            return Double.compare(s1.getWidth(),
                    s2.getWidth());
        }
    };

    /**
     * A comparator for ship lengths
     */
    public static Comparator<Ship> lengthComparator = new Comparator<Ship>() {
        public int compare(Ship s1, Ship s2) {
            return Double.compare(s1.getLength(),
                    s2.getLength());
        }
    };
}

