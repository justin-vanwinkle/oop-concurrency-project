package main;

import main.thing.*;
import main.thing.ship.CargoShip;
import main.thing.ship.PassengerShip;
import main.ui.SeaPortUI;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class SeaPortProgram {

    private World world;

    public SeaPortProgram() {
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
        Scanner sc = new Scanner(definition);
        //StringTokenizer st = new StringTokenizer(definition);

        switch (sc.next()) {
            case "port":
                return new SeaPort(
                        sc.next()
                        , sc.nextInt()
                        , sc.nextInt());
//                        sc.next()
//                        , sc.nextInt()
//                        , sc.nextInt());

            case "dock":
                // create the dock
                return new Dock(
                        sc.next()
                        , sc.nextInt()
                        , sc.nextInt()
                );

            case "pship":
                return new PassengerShip(
                        sc.next()
                        , sc.nextInt()
                        , sc.nextInt()
                        , sc.nextDouble()
                        , sc.nextDouble()
                        , sc.nextDouble()
                        , sc.nextDouble()
                        , sc.nextInt()
                        , sc.nextInt()
                        , sc.nextInt()
                );

            case "cship":
                return new CargoShip(
                        sc.next()
                        , sc.nextInt()
                        , sc.nextInt()
                        , sc.nextDouble()
                        , sc.nextDouble()
                        , sc.nextDouble()
                        , sc.nextDouble()
                        , sc.nextDouble()
                        , sc.nextDouble()
                        , sc.nextDouble()
                );

            case "person":
                return new Person(
                        sc.next()
                        , sc.nextInt()
                        , sc.nextInt()
                        , sc.next()
                );

            case "job":
                // collect params
                String name = sc.next();
                int index = sc.nextInt();
                int parent = sc.nextInt();
                double duration = sc.nextDouble();

                // build the list of required skills
                ArrayList<String> skill = new ArrayList<>();
                while (sc.hasNext()) {
                    skill.add(sc.next());
                }

                // create the job
                return new Job( name, index, parent, duration, skill );
        }
        return null;
    }


    public void createWorld(String filepath) {
        world = new World();

        ArrayList<String> objDefs = parseObjectDefinitions(filepath);

        for(String def: objDefs) {
            // create this thing
            Thing thing = createThingFromDefinition(def);

            // add this thing to the master map
            world.addThing(thing);

        }
    }


    public static void main(String[] args) {

        SeaPortProgram spp = new SeaPortProgram();
    }

    public World getWorld() {
        return world;
    }
}
