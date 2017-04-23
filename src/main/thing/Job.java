/**
 * Filename: Job.java
 * Date: 26 March 2017
 * Author: Justin VanWinkle
 * Purpose: This Class represents a job that a person might do
 */

package main.thing;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class Job extends Thing implements Runnable{
    double duration;
    ArrayList<String> requirements = new ArrayList<>();
    private Thread thread;
    private Status status = Status.WAITING;
    private boolean goFlag = false;
    private int progress;

    public Status getStatus() {
        return status;
    }

    public int getProgress() {
        return progress;
    }

    public enum Status {RUNNING, SUSPENDED, WAITING, DONE}

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
        thread = new Thread(this);
        thread.start();
    }

    /**
     * The workflow for the Job method
     */
    @Override
    public void run() {
        long runTime = 0;

        // run until the duration has passed
        while (runTime < duration) {

            try {
                Thread.sleep (1000);
            } catch (InterruptedException e) {}

            // progress if the goFlag is raised
            if (goFlag) {
                status = Status.RUNNING;
                runTime += 10;
                Double d = (runTime / duration) * 100;
                progress = d.intValue();
            }

            else if (status == Status.WAITING) {
                continue;
            }

            else if (status == Status.DONE) {
                break;
            }

            else {
                status = Status.SUSPENDED;
            }
        }

        // duration has passed, clean up
        progress = 100;
        status = Status.DONE;

        return;
    }

    /**
     * Sets the progress of a job to suspend
     */
    public void suspend() {
        goFlag = false;
    }

    /**
     * Sets the progress of a job to halt and sets the job as complete
     */
    public void cancel() {
        goFlag = false;
        status = Status.DONE;
    }

    /**
     * Sets the job to begin progressing
     */
    public void begin() { goFlag = true; }

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

    /**
     * Toggles the progress of a job
     * @return
     */
    public boolean toggleGoFlag() {
        goFlag = !goFlag;
        return goFlag;
    }

    /**
     * Gets the thread of a job
     * @return the thread of a job
     */
    public Thread getThread() {
        return thread;
    }
}
