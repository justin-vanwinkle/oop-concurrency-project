/**
 * Filename: SeaPortProgram.java
 * Date: 26 March 2017
 * Author: Justin VanWinkle
 * Purpose: This program models a set of sea ports and allows the user to interact with the model by searching
 */

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

    /**
     * The constructor for this class
     */
    public SeaPortProgram() {
        // get a UI and pass it context to this object
        new SeaPortUI(this);
    }

    /**
     * Parses out object definitions while ignoring comments and blank lines
     * @param filePath the path to a file containing definitions
     * @return an ArrayList of Strings representing each object definition in the file
     */
    public static ArrayList<String> parseObjectDefinitions(String filePath) {
        // get a list to hold the lines
        ArrayList<String> lines = new ArrayList<>();

        // try with resources for reading the file
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;

            // iterate over the lines of the file
            while ((line = br.readLine()) != null) {
                // trim of whitespace
                line = line.trim();
                // check if this is a comment or blank line
                if (line.isEmpty() || line.startsWith("//")) {
                    continue;
                }
                // add this line
                lines.add(line);
            }
        } catch (IOException e) {
            // let's just sweep this under the rug for now
            // an infinitely bad practice for production apps
        }
        return lines;
    }

    /**
     * Creates an Thing from a definition
     * @param definition the definition from which a Thing will be created
     * @return the Thing that is created
     */
    public Thing createThingFromDefinition(String definition) {
        // get a scanner on the definition
        Scanner sc = new Scanner(definition);

        // route based on the first word
        switch (sc.next()) {

            // create a port
            case "port":
                return new SeaPort(
                        sc.next()
                        , sc.nextInt()
                        , sc.nextInt());

            // create a dock
            case "dock":
                // create the dock
                return new Dock(
                        sc.next()
                        , sc.nextInt()
                        , sc.nextInt()
                );

            // create a passenger ship
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

            // create a cargo ship
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

            // create a person
            case "person":
                return new Person(
                        sc.next()
                        , sc.nextInt()
                        , sc.nextInt()
                        , sc.next()
                );

            // create a job
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

            // if we get an unknown definition, ignore it
            default:
                break;
        }
        return null;
    }


    /**
     * Creates a world to model the definitions contained in the referenced file
     * @param filepath the path to a definition file
     */
    public void createWorld(String filepath) {

        // get a new world
        world = new World();

        // parse out the definitions from the file
        ArrayList<String> objDefs = parseObjectDefinitions(filepath);

        // iterate over the definitions
        for(String def: objDefs) {
            // create this thing
            Thing thing = createThingFromDefinition(def);

            // add this thing to the master map
            world.addThingToParent(thing);
        }
    }

    /**
     * A small workaround for the initial ships not having jobs when assigned to a dock
     */
    public void startWorld() {

        world.getPorts().forEach(port -> {
            new Thread(port).start();
        });

        world.getPorts().forEach(port -> {
            port.getDocks().forEach(dock -> {
                dock.getShip().getJobs().forEach(job -> {
                    job.toggleGoFlag();
                });
            });
        });

    }


    /**
     * Gets the world of this class
     * @return
     */
    public World getWorld() {
        return world;
    }


    /**
     * The main entry point of this program
     * @param args unused
     */
    public static void main(String[] args) {

        // instantiate this program
        new SeaPortProgram();
    }

}
