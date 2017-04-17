package test.thing

import main.SeaPortProgram
import main.thing.Dock
import main.thing.Person
import main.thing.SeaPort
import main.thing.Thing
import main.thing.ship.PassengerShip
import main.thing.ship.Ship
import org.junit.Test

/**
 * Created by vanwinklej on 3/21/17.
 */
class SeaPortTest extends GroovyTestCase {

    SeaPort sp
    Ship s1, s2, s3, s4

    void setUp() {
        sp = new SeaPort("port", 1, 0)

        s1 = new PassengerShip("ship1", 2, 1, 1.1, 1.1, 1.1, 1.1, 1, 1, 1)
        s2 = new PassengerShip("ship2", 2, 1, 1.2, 1.2, 1.2, 1.2, 1, 1, 1)
        s3 = new PassengerShip("ship3", 2, 1, 1.3, 1.3, 1.3, 1.3, 1, 1, 1)
        s4 = new PassengerShip("ship4", 2, 1, 1.4, 1.4, 1.4, 1.4, 1, 1, 1)

    }

    @Test
    void testAddChild() {

        Thing child = new Dock("name", 1, 1)
        boolean result = sp.addChild(child)
        assertEquals(true, result)
    }

    @Test
    void testAddShip() {
        boolean result = sp.addShip(s1, true)
        assertEquals(true, result)

        result = sp.addShip(s2, false)
        assertEquals(true, result)
    }

}
