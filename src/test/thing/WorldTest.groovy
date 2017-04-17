package test.thing

import main.thing.Dock
import main.thing.Job
import main.thing.Person
import main.thing.SeaPort
import main.thing.Thing
import main.thing.World
import main.thing.ship.CargoShip
import main.thing.ship.PassengerShip
import org.junit.Test

/**
 * Created by vanwinklej on 3/21/17.
 */
class WorldTest extends GroovyTestCase {
    World world
    void setUp() {
        world = new World()
        world.addThingToParent(new SeaPort("port", 1, 0))
        world.addThingToParent(new Dock("dock", 2, 1))
        world.addThingToParent(new PassengerShip("pship", 3, 2, 1.1, 1.1, 1.1, 1.1, 1, 1, 1))
        world.addThingToParent(new CargoShip("cship", 4, 1, 1.1, 1.1, 1.1, 1.1, 1, 1, 1))
        world.addThingToParent(new Person("person", 5, 1, "clerk"))

        ArrayList<String> r = new ArrayList<>();
        r.add("test")
        world.addThingToParent(new Job("job", 6, 5, 1.1, r))
    }

    @Test
    void testAddThingAndGetThing() {
        SeaPort p = new SeaPort("new port", 99, 0)
        Thing expected = p
        world.addThingToParent(p)
        Thing result = world.getThing(99)
        assertEquals(expected.toString(), result.toString())
    }
}
