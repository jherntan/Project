package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.items.Item;
import game.interfaces.Unlockable;
import game.items.AccessCard;

/**
 * A class representing a security door in the game world.
 * The door is impassable until an Actor unlocks it using an Access Card.
 */
public class Door extends Ground implements Unlockable {
    /** State of the door's lock */
    private boolean isUnlocked = false;

    /**
     * Constructor.
     */
    public Door() {
        super('=', "Door");
    }

    /**
     * Checks if an actor can enter this location.
     * @param actor the Actor to check
     * @return true if the door is unlocked, false otherwise.
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return isUnlocked;
    }

    /**
     * Logic for unlocking the door. Checks the actor's inventory for an Access Card.
     * @param actor The actor attempting the unlock.
     * @param location The location of the door.
     * @return A description of the interaction result.
     */
    @Override
    public String unlockedBy(Actor actor, Location location) {
        boolean hasCard = false;
        for (Item item : actor.getInventory().getItems()) {
            if (item instanceof AccessCard) {
                hasCard = true;
                break;
            }
        }

        if (hasCard) {
            this.isUnlocked = true;
            return actor + " swipes the Access Card and the door hisses open!";
        }

        return actor + " tries to open the door, but it requires an Access Card!";
    }

    /**
     * Returns a string representation of the door.
     * @return name of the ground type.
     */
    @Override
    public String toString() {
        return "Security Door";
    }
}