package test.thing

import main.thing.Thing
import main.thing.World
import org.junit.Test

/**
 * Created by vanwinklej on 3/21/17.
 */
class WorldTest extends GroovyTestCase {
    World world;
    void setUp() {
        world = new World()
    }

    void testCompareTo() {

    }

    @Test
    void testAddThing() {
//        Thing expected = new Thing("Test", 20000, 10000)
//        world.addThing(expected)
//        Thing result = world.getThing(20000)
//        assertEquals(expected, result)
    }
}
