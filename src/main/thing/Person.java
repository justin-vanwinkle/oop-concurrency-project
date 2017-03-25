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
        StringBuilder sb = new StringBuilder("Person: ");
        sb.append(getName() + " ");
        sb.append(getIndex() + " ");
        sb.append(skill);
        return sb.toString();
    }
}
