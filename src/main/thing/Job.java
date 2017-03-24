package main.thing;

import java.util.ArrayList;

/**
 * Created by vanwinklej on 3/21/17.
 */
public class Job extends Thing {
    double duration;
    ArrayList<String> requirements = new ArrayList<>();

    public Job(String name, int index, int parent, double duration, ArrayList requirements) {
        super(name, index, parent);
        this.duration = duration;
        this.requirements = requirements;
    }
}
