package main;

import main.thing.*;
import main.thing.ship.CargoShip;
import main.thing.ship.PassengerShip;
import main.thing.ship.Ship;
import main.ui.SeaPortUI;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SeaPortProgram {

    private World world;

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

    public Thing createThingFromDefinition(String definition) {
        StringTokenizer st = new StringTokenizer(definition);

        switch (st.nextToken()) {
            case "port":
                return new SeaPort(
                        st.nextToken()
                        , Integer.parseInt(st.nextToken())
                        , Integer.parseInt(st.nextToken()));

            case "dock":
                // create the dock
                return new Dock(
                        st.nextToken()
                        , Integer.parseInt(st.nextToken())
                        , Integer.parseInt(st.nextToken())
                );

            case "pship":
                return new PassengerShip(
                        st.nextToken()
                        , Integer.parseInt(st.nextToken())
                        , Integer.parseInt(st.nextToken())
                        , Double.parseDouble(st.nextToken())
                        , Double.parseDouble(st.nextToken())
                        , Double.parseDouble(st.nextToken())
                        , Double.parseDouble(st.nextToken())
                        , Integer.parseInt(st.nextToken())
                        , Integer.parseInt(st.nextToken())
                        , Integer.parseInt(st.nextToken())
                );

            case "cship":
                return new CargoShip(
                        st.nextToken()
                        , Integer.parseInt(st.nextToken())
                        , Integer.parseInt(st.nextToken())
                        , Double.parseDouble(st.nextToken())
                        , Double.parseDouble(st.nextToken())
                        , Double.parseDouble(st.nextToken())
                        , Double.parseDouble(st.nextToken())
                        , Double.parseDouble(st.nextToken())
                        , Double.parseDouble(st.nextToken())
                        , Double.parseDouble(st.nextToken())
                );

            case "person":
                return new Person(
                        st.nextToken()
                        , Integer.parseInt(st.nextToken())
                        , Integer.parseInt(st.nextToken())
                        , st.nextToken()
                );

            case "job":
                // collect params
                String name = st.nextToken();
                int index = Integer.parseInt(st.nextToken());
                int parent = Integer.parseInt(st.nextToken());
                double duration = Double.parseDouble(st.nextToken());

                // build the list of required skills
                ArrayList<String> skill = new ArrayList<>();
                while (st.hasMoreTokens()) {
                    skill.add(st.nextToken());
                }

                // create the job
                return new Job( name, index, parent, duration, skill );
        }
        return null;
    }


    public void createWorld(String filepath) {

        ArrayList<String> objDefs = parseObjectDefinitions(filepath);

        for(String def: objDefs) {
            // create this thing
            Thing thing = createThingFromDefinition(def);

            // add this thing to the master map
            world.addThing(thing);

        }

        System.out.println(this.getWorld().toString());
    }


    public static void main(String[] args) {

        SeaPortProgram spp = new SeaPortProgram();
    }

    public World getWorld() {
        return world;
    }
}
