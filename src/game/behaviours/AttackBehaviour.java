package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.behaviours.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackAction;
import game.enums.Effect;

/**
 * A behaviour that allows an Actor to identify and attack a nearby target.
 * It specifically looks for Actors with the Effect.WORKER capability.
 */
public class AttackBehaviour implements Behaviour<Actor, Action> {

    /**
     * Scans the surrounding exits for an actor that possesses the WORKER ability.
     * If a valid target is found, it returns an AttackAction.
     * * @param entity the Actor performing the behaviour
     * @param location the current location of the Actor
     * @return an AttackAction if a target is in range, null otherwise
     */
    @Override
    public Action operate(Actor entity, Location location) {
        for (Exit exit : location.getExits()) {
            Location destination = exit.getDestination();

            if (destination.containsAnActor()) {
                Actor target = destination.getActor();

                // Use hasAbility to check if the target is the player/worker
                if (target.hasAbility(Effect.WORKER)) {
                    return new AttackAction(target, exit.getName());
                }
            }
        }
        return null;
    }
}