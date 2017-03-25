package test

import main.thing.SeaPort
import main.thing.Thing
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
        expected.add("ship Reason 40003 10000 165.91 447.15 85.83 27.07")
        expected.add("cship Suites 40003 10000 165.91 447.15 85.83 27.07 125.09 176.80 857.43")
        expected.add("pship \"ZZZ_Hysterics\" 30002 20002 103.71 327.92 56.43 30.23 3212 917 917")
        expected.add("person Alberto 50013 10001 cleaner")
        expected.add("job Job_10_94_27 60020 30007 77.78 carpenter cleaner clerk")

        ArrayList<String> result = spp.parseObjectDefinitions("src/test/testParseObjectDefinitions.txt")
        assertEquals(expected, result)
    }

    @Test
    void testCreateObjectFromDefinition() {

        // port
        SeaPort expected = new SeaPort("Kandahar", 10002, 0);
        //spp.createThingFromDefinition("port Kandahar 10002 0")
        SeaPort result = spp.getWorld().getPorts().get(10002)
        assertEquals(expected.toString(), result.toString())

        // dock

        // pship

        // cship



    }
}
