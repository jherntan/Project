package game.main;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Inventory;
import edu.monash.fit2099.engine.positions.DefaultGroundCreator;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.actors.ContractedWorker;
import game.grounds.*;
import game.grounds.Puddle;
import game.items.*;

import java.util.Arrays;
import java.util.List;

/**
 * This class handles the miracle of creation for Moon 99-Deprecated.
 */
public class EclipseNebula extends World {
    public EclipseNebula(Display display) {
        super(display);
    }

    /**
     * Initialise maps, actors, items, and grounds of the game world for REQ2 and REQ3.
     */
    public void initialise() throws Exception {
        DefaultGroundCreator groundCreator = new DefaultGroundCreator();
        groundCreator.registerGround('.', Dirt::new);
        groundCreator.registerGround('#', Wall::new);
        groundCreator.registerGround('~', Puddle::new);
        groundCreator.registerGround('_', Floor::new);
        groundCreator.registerGround('=', Door::new);

        // REQ3: Register 'o' as a Hole to spawn creatures every 20 turns
        groundCreator.registerGround('o', Hole::new);

        List<String> moon99Deprecated = Arrays.asList(
                "....................########################################",
                "...#######..........#__________________#________________o__#",
                "...#_____#..........=__________________=___________________#",
                "...#_____=...~......#__________________#___________________#",
                "...#_____#..~~~.....########=#####=#####___#############___#",
                "...#######.~~~~.....#______#_#_________#___#___________#___#",
                ".........~~~~.......#______#_#_________#####___________#####",
                "....................#______=_#_________#___________________#",
                "......~.............#______#_#_________#___________________#",
                ".....~~~............#______#_###########___#############___#",
                ".....~..............#______#___________#___#___________#___#",
                "....................=______#___________=___=_____o_____=___#",
                "....................#______#############___#############___#",
                ".........~~~~.......#______#___________#####################",
                "........~~~~~~......#______#___________=___________________#",
                ".........~~~~.......#______#___________#___________________#",
                "....................#______#############___#############___#",
                "....................#______#_____o_____#___#___________#_o_#",
                "..~.................#______=___________=___=___________=___#",
                "....................########################################"
        );

        GameMap moon99DeprecatedMap = new GameMap("99-Deprecated", groundCreator, moon99Deprecated);
        this.addGameMap(moon99DeprecatedMap);

        // REQ1: Corporate Assets
        moon99DeprecatedMap.at(7, 2).addItem(new AccessCard());
        moon99DeprecatedMap.at(7, 3).addItem(new FirstAidKit());
        moon99DeprecatedMap.at(6, 3).addItem(new SterilisationBox());

        // REQ2: Items and Scraps
        moon99DeprecatedMap.at(5, 2).addItem(new Apple());
        moon99DeprecatedMap.at(49, 11).addItem(new Cookies());
        moon99DeprecatedMap.at(33, 17).addItem(new Lantern());
        moon99DeprecatedMap.at(57, 17).addItem(new CRTMonitor());
        moon99DeprecatedMap.at(49, 1).addItem(new FloppyDisk());

        // REQ1: Create unique workers
        this.addPlayer(createNewWorker("#1 Bob", 'ඞ'), moon99DeprecatedMap.at(6, 2));

        //Remaining team members commented out per instructions:
        this.addPlayer(createNewWorker("#2 Tom", 'ඞ'), moon99DeprecatedMap.at(7, 2));
        this.addPlayer(createNewWorker("#3 Sarah", 'ඞ'), moon99DeprecatedMap.at(8, 2));
        this.addPlayer(createNewWorker("#4 Julie", 'ඞ'), moon99DeprecatedMap.at(6, 4));
        this.addPlayer(createNewWorker("#5 Rick", 'ඞ'), moon99DeprecatedMap.at(8, 4));
    }

    /**
     * Helper to ensure each worker is independent and starts with a Flask.
     */
    private ContractedWorker createNewWorker(String name, char skin) {
        // Now correctly uses the WeightLimitedInventory with a 50-unit capacity
        Inventory inv = new WeightLimitedInventory(50);
        inv.add(new Flask());
        return new ContractedWorker(name, skin, 10, inv);
    }
}