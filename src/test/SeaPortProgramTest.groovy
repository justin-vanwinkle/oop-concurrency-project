package test

import main.thing.Dock
import main.thing.Job
import main.thing.Person
import main.thing.SeaPort
import main.thing.Thing
import main.thing.ship.CargoShip
import main.thing.ship.PassengerShip
import org.junit.Test
import main.SeaPortProgram

/**
 * Created by vanwinklej on 3/21/17.
 */
class SeaPortProgramTest extends GroovyTestCase {

    SeaPortProgram spp;
    Thing thing;

    void setUp() {
        super.setUp()
        spp = new SeaPortProgram()
    }

    void tearDown() {

    }

    @Test
    void testParseObjectDefinitions() {
        ArrayList<String> expected = new ArrayList<>()
        expected.add("port Kandahar 10002 0")
        expected.add("dock Pier_5 20005 10001 30005")
        expected.add("cship Suites 40003 10000 165.91 447.15 85.83 27.07 125.09 176.80 857.43")
        expected.add("pship \"ZZZ_Hysterics\" 30002 20002 103.71 327.92 56.43 30.23 3212 917 917")
        expected.add("person Alberto 50013 10001 cleaner")
        expected.add("job Job_10_94_27 60020 30007 77.78 carpenter cleaner clerk")

        ArrayList<String> result = spp.parseObjectDefinitions("src/test/testParseObjectDefinitions.txt")
        assertEquals(expected, result)
    }

    @Test
    void testCreateThingFromDefinition() {

        // port
        Thing expected = new SeaPort("Kandahar", 10002, 0)
        Thing result = spp.createThingFromDefinition("port Kandahar 10002 0")
        assertEquals(expected.toString(), result.toString())

        // dock
        expected = new Dock("Pier_5", 20005, 10001)
        result = spp.createThingFromDefinition("dock Pier_5 20005 10001")
        assertEquals(expected.toString(), result.toString())

        // pship
        expected = new PassengerShip("ZZZ_Hysterics", 30002, 20002, 103.71, 327.92, 56.43, 30.23, 3212, 917, 917)
        result = spp.createThingFromDefinition("pship ZZZ_Hysterics 30002 20002 103.71 327.92 56.43 30.23 3212 917 917")
        assertEquals(expected.toString(), result.toString())

        // cship
        expected = new CargoShip("Erosional", 40001, 10000, 200.80, 242.33, 38.31, 23.49, 172.73, 188.54, 235.57)
        result = spp.createThingFromDefinition("cship Erosional 40001 10000 200.80 242.33 38.31 23.49 172.73 188.54 235.57")
        assertEquals(expected.toString(), result.toString())

        // person
        expected = new Person("Alberto", 50013, 10001, "cleaner")
        result = spp.createThingFromDefinition("person Alberto 50013 10001 cleaner")
        assertEquals(expected.toString(), result.toString())

        // job
        ArrayList<String> reqs = new ArrayList<>()
        reqs.add("carpenter")
        reqs.add("cleaner")
        reqs.add("clerk")
        expected = new Job("Job_10_94_27", 60020, 30007, 77.78, reqs)
        result = spp.createThingFromDefinition("job Job_10_94_27 60020 30007 77.78 carpenter cleaner clerk")
        assertEquals(expected.toString(), result.toString())

        // empty
        expected = null
        result = spp.createThingFromDefinition("etslrn")
        assertEquals(expected, result)
    }
}
