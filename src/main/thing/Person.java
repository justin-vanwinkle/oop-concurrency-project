package main.thing;

import java.util.regex.Pattern;

/**
 * Created by vanwinklej on 3/21/17.
 */
public class Person extends Thing {
    String skill;

    public Person(String name, int index, int parent, String skill) {
        super(name, index, parent);
        this.skill = skill;
    }

    @Override
    public boolean checkForMatch(String pattern) {
        Pattern r = Pattern.compile(pattern);

        if (r.matcher(skill).find()) {
            return true;
        }
        if (super.checkForMatch(pattern)) {
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return "Person: " + super.toString() + " " + skill;
    }
}
