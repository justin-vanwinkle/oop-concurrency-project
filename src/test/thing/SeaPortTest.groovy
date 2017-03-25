package test.thing

import main.thing.SeaPort
import main.thing.Thing
import main.thing.ship.Ship
import org.junit.Test

/**
 * Created by vanwinklej on 3/21/17.
 */
class SeaPortTest extends GroovyTestCase {
    void testCompareTo() {

    }

    @Test
    void testAddChild() {
        Thing sp = new SeaPort("port", 1, 0)
        Thing child = new Ship("ship", 2, 1, 0, 0, 0, 0)

        sp.addChild(child)

        println(sp.getShips().get(2))
    }
}
