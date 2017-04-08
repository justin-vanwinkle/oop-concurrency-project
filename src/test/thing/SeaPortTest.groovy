package test.thing

import main.SeaPortProgram
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

        Thing child = new PassengerShip("ship", 2, 1, 1.1, 1.1, 1.1, 1.1, 1, 1, 1)
        boolean result = sp.addChild(child)
        assertEquals(true, result)
    }

    @Test
    void testWeightComparator() {
        ArrayList<Ship> result = new ArrayList<>()
        result.add(s1)
        result.add(s3)
        result.add(s2)
        result.add(s4)

        Collections.sort(result, Ship.weightComparator)

        ArrayList<Ship> expected = new ArrayList<>()
        expected.add(s1)
        expected.add(s2)
        expected.add(s3)
        expected.add(s4)

        assertEquals(expected, result)
    }

    @Test
    void testDraftComparator() {
        ArrayList<Ship> result = new ArrayList<>()
        result.add(s1)
        result.add(s3)
        result.add(s2)
        result.add(s4)

        Collections.sort(result, Ship.draftComparator)

        ArrayList<Ship> expected = new ArrayList<>()
        expected.add(s1)
        expected.add(s2)
        expected.add(s3)
        expected.add(s4)

        assertEquals(expected, result)
    }

    @Test
    void testLengthComparator() {
        ArrayList<Ship> result = new ArrayList<>()
        result.add(s1)
        result.add(s3)
        result.add(s2)
        result.add(s4)

        Collections.sort(result, Ship.lengthComparator)

        ArrayList<Ship> expected = new ArrayList<>()
        expected.add(s1)
        expected.add(s2)
        expected.add(s3)
        expected.add(s4)

        assertEquals(expected, result)
    }

    @Test
    void testWidthComparator() {
        ArrayList<Ship> result = new ArrayList<>()
        result.add(s1)
        result.add(s3)
        result.add(s2)
        result.add(s4)

        Collections.sort(result, Ship.widthComparator)

        ArrayList<Ship> expected = new ArrayList<>()
        expected.add(s1)
        expected.add(s2)
        expected.add(s3)
        expected.add(s4)

        assertEquals(expected, result)
    }
}
