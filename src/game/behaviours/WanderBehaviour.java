package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.behaviours.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A behaviour that allows an Actor to move randomly to any available adjacent tile.
 * This acts as the default "idle" movement for most NPCs.
 */
public class WanderBehaviour implements Behaviour<Actor, Action> {
    /** Random number generator to pick a direction */
    private final Random random = new Random();

    /**
     * Scans all available exits and picks a random valid destination to move to.
     * * @param entity the Actor performing the behaviour
     * @param location the current location of the Actor
     * @return a MoveActorAction to a random valid exit, or null if no exits are available
     */
    @Override
    public Action operate(Actor entity, Location location) {
        List<Exit> validExits = new ArrayList<>();

        for (Exit exit : location.getExits()) {
            if (exit.getDestination().canActorEnter(entity)) {
                validExits.add(exit);
            }
        }

        if (!validExits.isEmpty()) {
            Exit randomExit = validExits.get(random.nextInt(validExits.size()));
            return new MoveActorAction(randomExit.getDestination(), randomExit.getName());
        }

        return null;
    }
}