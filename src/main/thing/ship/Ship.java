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
    ArrayList<Job> jobs;

    public Ship(String name, int index, int parent, PortTime arrivalTime, PortTime dockTime, double draft,
                double length, double weight, double width, ArrayList jobs) {
        super(name, index, parent);
        this .arrivalTime = arrivalTime;
        this.dockTime = dockTime;
        this.draft = draft;
        this.length = length;
        this.weight = weight;
        this.width = width;
        this.jobs = jobs;
    }
}
