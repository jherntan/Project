package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.interfaces.Unlockable;

/**
 * An Action that allows an Actor to interact with objects implementing the Unlockable interface.
 * Typically used for opening locked doors using specific items.
 */
public class UnlockAction extends Action {
    private final Unlockable target;
    private final Location location;

    /**
     * Constructor.
     * @param target the Unlockable entity (e.g., a Door)
     * @param location the location of the target
     */
    public UnlockAction(Unlockable target, Location location) {
        this.target = target;
        this.location = location;
    }

    /**
     * Executes the unlock logic by delegating to the target's unlockedBy method.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a message indicating if the unlock was successful.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // This calls the unlockedBy method you wrote in Door.java
        return target.unlockedBy(actor, location);
    }

    /**
     * Returns a descriptive string for the menu.
     * @param actor The actor performing the action.
     * @return text to display in the menu.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " unlocks " + target;
    }
}