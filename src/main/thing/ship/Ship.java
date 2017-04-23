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

public abstract class Ship extends Thing implements Runnable {
    PortTime arrivalTime
            , dockTime;
    private double draft;
    private double length;
    private double weight;
    private double width;
    private ArrayList<Job> jobs = new ArrayList<>();
    private Thread thread;
    private Status status = Status.JOBS_PENDING;
    private boolean isDocked;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public enum Status{JOBS_IN_PROGRESS, JOBS_COMPLETE, JOBS_PENDING}

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
        thread = new Thread(this);
        thread.start();
    }

    /**
     * The workflow of the ship thread
     */
    @Override
    public void run() {
        isDocked = false;

        // delay until docked
        while (!isDocked) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // watch jobs for completion
        for (int i=0; i<jobs.size(); i++) {

            // hold on a job until it completes, the check the rest
            while (jobs.get(i).getStatus() != Job.Status.DONE) {
                try {
                    Thread.sleep(100);
                }
                catch (InterruptedException e) {}

                // ensure that all jobs started
                jobs.forEach(job -> {
                    if (job.getStatus() == Job.Status.WAITING) {
                        job.begin();
                    }
                });

            }

            // raise completion flag
            if (i == jobs.size() - 1) {
                status = Status.JOBS_COMPLETE;
            }
        }

        return;
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
     * Gets the jobs of a ship
     * @return the jobs of a ship
     */
    public ArrayList<Job> getJobs() {
        return jobs;
    }

    /**
     * Adds a child to a ship
     * @param child the child to be added
     * @return true if the child was added, otherwise false
     */
    @Override
    public boolean addChild(Thing child) {
        if (child instanceof Job) {
            return jobs.add((Job)child);
        }
        return false;
    }

    /**
     * raises the docked flag
     */
    public void dock() { isDocked = true; }

    /**
     * Checks if the docked flag is raised
     * @return the isDocked value
     */
    public boolean isDocked() {return isDocked;}

    /**
     * Gets the thread of the ship
     * @return the thread of the ship
     */
    public Thread getThread() {
        return thread;
    }
}

