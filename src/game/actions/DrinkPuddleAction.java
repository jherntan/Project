package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.ContractedWorker;
import game.items.SterilisationBox;

/**
 * An Action that allows an Actor to drink from a puddle.
 * Logic handles both purified water (if the actor has a Sterilisation Box)
 * and toxic water (poisoning the actor).
 */
public class DrinkPuddleAction extends Action {

    /**
     * Executes the drinking action. Checks for a SterilisationBox in the actor's inventory.
     * Purified water heals, while mysterious water deals immediate damage and sets a poison timer.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return description of the side effects of drinking.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        boolean hasBox = false;
        for (Item item : actor.getInventory().getItems()) {
            if (item instanceof SterilisationBox) {
                hasBox = true;
                break;
            }
        }

        if (hasBox) {
            actor.heal(1);
            return actor + " drinks purified water from the puddle. (+1 HP)";
        } else {
            // 1st total damage: Immediate (10 -> 9)
            actor.hurt(1);

            // Trigger the remaining 2 turns of damage in ContractedWorker
            if (actor instanceof ContractedWorker) {
                ((ContractedWorker) actor).setPoisonTurns(2);
            }

            return actor + " drinks the mysterious puddle water. It's toxic! (Poisoned for 3 total turns)";
        }
    }

    /**
     * Returns a descriptive string for the menu.
     * @param actor The actor performing the action.
     * @return description of the action.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " drinks from the puddle";
    }
}