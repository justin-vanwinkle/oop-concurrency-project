package main;

import main.thing.Dock;
import main.thing.SeaPort;
import main.thing.World;
import main.ui.SeaPortUI;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SeaPortProgram {

    World world;

    public SeaPortProgram() {
        world = new World();
        SeaPortUI ui = new SeaPortUI(this);
    }

    /**
     * Parses out object definitions while ignoring comments and blank lines
     * @param filePath
     * @return
     */
    public static ArrayList<String> parseObjectDefinitions(String filePath) {
            ArrayList<String> lines = new ArrayList<>();

            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

                String line;

                while ((line = br.readLine()) != null) {
                    line = line.trim();
                    if (line.isEmpty() || line.startsWith("//")) {
                        continue;
                    }
                    lines.add(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        return lines;
    }

    private boolean appendObjectFromDefinition(String line) {
        StringTokenizer st = new StringTokenizer(line);

        switch (st.nextToken()) {
            case "port":
                this.world.addPort(
                        SeaPort( st.nextToken(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))
                );
                break;
            case "dock":
                this.world.getPorts() Dock(st.nextToken(), Integer.parseInt(st.nextToken(), Integer.parseInt(st.nextToken())));

        }

    }

    public static void main(String[] args) {

        SeaPortProgram spp = new SeaPortProgram();

        // get the object defs
        //ArrayList<String> objectDefs = parseObjectDefinitions("src/test/testParseObjectDefinitions.txt");

        // build the objects one at a time
//        for (String objectDef : objectDefs) {
//            createObjectFromDefinition(objectDef);
//        }
//        System.out.println(objectDefs);
    }

}
