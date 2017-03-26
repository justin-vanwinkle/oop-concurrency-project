package main.thing.ship;

import main.portTime.PortTime;
import main.thing.Job;
import main.thing.Thing;

import java.util.ArrayList;

/**
 * Created by vanwinklej on 3/21/17.
 */
public class Ship extends Thing {
    PortTime arrivalTime
            , dockTime;
    double draft
            , length
            , weight
            , width;
    private ArrayList<Job> jobs = new ArrayList<>();

    public Ship(String name, int index, int parent, double weight, double length, double width, double draft) {
        super(name, index, parent);
        this.weight = weight;
        this.length = length;
        this.width = width;
        this.draft = draft;
    }

    public boolean addJob(Job job) {
        return getJobs().add(job);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public ArrayList<Job> getJobs() {
        return jobs;
    }
}

