package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.behaviours.Behaviour;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.SlimeEatAction;
import game.items.Apple;
import game.items.Cookies;

/**
 * A behaviour specialized for the Slime actor to identify food on the ground.
 * It prioritizes eating specifically defined items like Apples and Cookies.
 */
public class SlimeEatBehaviour implements Behaviour<Actor, Action> {

    /**
     * Checks the current location for consumable items.
     * If an Apple or Cookie is present on the ground, it returns a SlimeEatAction.
     * * @param entity the Actor performing the behaviour
     * @param location the current location of the Actor
     * @return a SlimeEatAction if food is found, null otherwise
     */
    @Override
    public Action operate(Actor entity, Location location) {
        // Look through items at the Slime's current location
        for (Item item : location.getItems()) {
            if (item instanceof Apple || item instanceof Cookies) {
                // Found food! Return the Action that knows how to eat it.
                return new SlimeEatAction(item);
            }
        }

        // No food found, return null so the Slime can try its next Behaviour
        return null;
    }
}