/**
 * Filename: Person.java
 * Date: 26 March 2017
 * Author: Justin VanWinkle
 * Purpose: This Class represents a person who has a trade skill
 */

package main.thing;

import java.util.regex.Pattern;

public class Person extends Thing {
    private String skill;
    private boolean isAvailable = true;

    /**
     * The constructor for this class
     * @param name the name of this person
     * @param index the index of this person
     * @param parent the parent of this person
     * @param skill the skill of this person
     */
    public Person(String name, int index, int parent, String skill) {
        super(name, index, parent);
        this.skill = skill;
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

        // check the skill for a match
        if (r.matcher(getSkill()).find()) {
            return true;
        }
        // check the super class for a match
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
        if (isAvailable) {
            return "(Available) Person: " + super.toString() + " " + getSkill();
        }
        else {
            return "(Unavailable) Person: " + super.toString() + " " + getSkill();
        }

    }

    public String getSkill() {
        return skill;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        synchronized (this) {
            isAvailable = available;
        }
    }
}
