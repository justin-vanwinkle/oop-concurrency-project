/**
 * Filename: PortTime.java
 * Date: 26 March 2017
 * Author: Justin VanWinkle
 * Purpose: This Class represents the time of a port
 */

package main.portTime;

public class PortTime {
    private int time;

    /**
     * Constructor for this class
     * @param time the time of a port
     */
    public PortTime(int time) {
        this.time = time;
    }

    /**
     * Creates a string representation of this class
     * @return a string representation of this class
     */
    @Override
    public String toString() {
        return "Port Time: " + time;
    }
}
