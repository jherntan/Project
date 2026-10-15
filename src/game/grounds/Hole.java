package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Slime;
import game.actors.Undead;
import java.util.Random;

/**
 * A class representing a hole in the facility.
 * Acts as a spawner that periodically generates hostile entities.
 */
public class Hole extends Ground {
    /** Tracks the number of turns passed since game start */
    private int age = 0;
    /** Random generator for entity selection */
    private final Random random = new Random();

    /**
     * Constructor.
     */
    public Hole() {
        super('o', "Hole");
    }

    /**
     * Checks every 20 turns to see if a new enemy should be spawned.
     * Spawns either an Undead or a Slime if the location is currently vacant.
     * @param location The location of the hole.
     */
    @Override
    public void tick(Location location) {
        age++;
        if (age % 20 == 0 && !location.containsAnActor()) {
            try {
                if (random.nextBoolean()) {
                    location.addActor(new Undead());
                } else {
                    location.addActor(new Slime());
                }
            } catch (Exception e) {
                // Failsafe for engine-level spawning exceptions
            }
        }
    }
}