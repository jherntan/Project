package game.items;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.statistics.BaseStatistic;
import game.actions.ConsumeAction;
import game.enums.ItemStatistics;
import game.enums.Effect;
import game.interfaces.Consumable;

/**
 * A consumable food item that can be found in the facility.
 * If eaten without a Sterilisation Box, it poisons the actor.
 */
public class Apple extends Item implements Consumable {
    /**
     * Constructor.
     */
    public Apple() {
        super("Apple", 'ó');
        this.addNewStatistic(ItemStatistics.WEIGHT, new BaseStatistic(1));
    }

    /**
     * Handles the consumption logic for an Apple.
     * Checks for a SterilisationBox to determine if the apple heals or poisons the actor.
     * @param actor The actor consuming the apple.
     * @return A description of the consumption outcome.
     */
    @Override
    public String consumedBy(Actor actor) {
        boolean hasBox = actor.getInventory().getItems().stream().anyMatch(i -> i instanceof SterilisationBox);
        actor.getInventory().remove(this);

        if (hasBox) {
            actor.heal(3);
            return actor + " eats a sterilized apple. (+3 HP)";
        } else {
            actor.hurt(1);
            actor.enableAbility(Effect.POISONED);
            return actor + " eats a spoiled apple and is now POISONED!";
        }
    }

    /**
     * Returns a list of actions the owner can perform with this item.
     * @param owner the actor possessing the item.
     * @param location the current location.
     * @return a list of allowable actions.
     */
    @Override
    public ActionList allowableActions(Actor owner, Location location) {
        ActionList actions = new ActionList();
        if (owner != null) actions.add(new ConsumeAction(this));
        return actions;
    }
}