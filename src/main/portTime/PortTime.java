package main.portTime;

/**
 * Created by vanwinklej on 3/21/17.
 */
public class PortTime {
    private int time;

    public PortTime(int time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Port Time: " + time;
    }
}
