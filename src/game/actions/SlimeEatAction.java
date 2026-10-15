package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.ActorStatistics;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.statistics.StatisticOperations;
import game.actors.Slime;
import game.items.Apple;
import game.items.Cookies;
import game.enums.Effect;

/**
 * A specialized Action for the Slime actor to consume items from the ground.
 * Different items (Apples vs. Cookies) have different biological effects on the Slime.
 */
public class SlimeEatAction extends Action {
    private final Item item;

    /**
     * Constructor.
     * @param item the item to be removed from the map and eaten.
     */
    public SlimeEatAction(Item item) { this.item = item; }

    /**
     * Executes the eating logic. Apples poison the Slime, while Cookies reduce Max HP.
     * The item is removed from the map upon execution.
     * @param actor The actor (Slime) performing the action.
     * @param map The map the actor is on.
     * @return a description of the Slime's meal.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (item instanceof Apple) {
            actor.hurt(1);
            if (actor instanceof Slime) {
                ((Slime) actor).setPoisonTurns(4);
                actor.enableAbility(Effect.POISONED);
            }
        } else if (item instanceof Cookies) {
            actor.modifyStatisticMaximum(ActorStatistics.HEALTH, StatisticOperations.DECREASE, 1);
        }
        map.locationOf(actor).removeItem(item);
        return actor + " eats " + item + " from the ground!";
    }

    /**
     * The Slime is an NPC and does not use the player menu.
     * @param actor The actor performing the action.
     * @return null, as Slimes do not interact via menus.
     */
    @Override
    public String menuDescription(Actor actor) { return null; }
}