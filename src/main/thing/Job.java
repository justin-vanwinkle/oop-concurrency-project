/**
 * Filename: Job.java
 * Date: 26 March 2017
 * Author: Justin VanWinkle
 * Purpose: This Class represents a job that a person might do
 */

package main.thing;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class Job extends Thing {
    double duration;
    ArrayList<String> requirements = new ArrayList<>();

    /**
     * The constructor for this class
     * @param name the name of this job
     * @param index the index of this job
     * @param parent the parent to which this job belongs
     * @param duration the duration of this job
     * @param requirements the requirements of this job
     */
    public Job(String name, int index, int parent, double duration, ArrayList<String> requirements) {
        super(name, index, parent);
        this.duration = duration;
        this.requirements = requirements;
    }

    /**
     * Performs a regex comparison to check for a match
     * @param pattern the pattern to match on
     * @return true if a match is found.  Otherwise false.
     */
    @Override
    public boolean checkForMatch(String pattern) {
        // compile the pattern
        Pattern r = Pattern.compile(pattern);

        // if a match is found
        if (r.matcher( Double.toString(duration) ).find()) {
            return true;
        }

        // iterate over all of the requirements and check for matches.
        for (String req : requirements) {
            if (r.matcher(req).find()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Creates a string representation of this class
     * @return a string representation of this class
     */
    @Override
    public String toString() {
        // get a string builder and append super string and duration
        StringBuilder sb = new StringBuilder("Job: " + super.toString() + " " + duration);

        // iterate over the requirements and append them to the string
        for (String requirement: requirements) {
            sb.append(" " + requirement);
        }
        return sb.toString();
    }

}
