package main.thing;

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
    public String toString() {
        return "Person: " + super.toString() + " " + skill;
    }
}
