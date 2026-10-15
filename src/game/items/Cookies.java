package game.items;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.statistics.BaseStatistic;
import edu.monash.fit2099.engine.statistics.StatisticOperations;
import game.enums.ActorStatistics;
import game.enums.ItemStatistics;
import game.actions.ConsumeAction;
import game.interfaces.Consumable;

/**
 * A multi-use consumable item.
 * Provides small heals if sterilized, but permanently reduces Max HP if toxic.
 */
public class Cookies extends Item implements Consumable {
    private int usages = 5;

    /**
     * Constructor.
     */
    public Cookies() {
        super("Cookies", '◍');
        this.addNewStatistic(ItemStatistics.WEIGHT, new BaseStatistic(1));
    }

    /**
     * Handles consumption of a single cookie.
     * @param actor The actor consuming the cookie.
     * @return String describing the effect on the actor.
     */
    @Override
    public String consumedBy(Actor actor) {
        usages--;
        boolean hasBox = actor.getInventory().getItems().stream().anyMatch(i -> i instanceof SterilisationBox);

        String msg;
        if (hasBox) {
            actor.heal(1);
            msg = actor + " eats a sterilized cookie. (+1 HP)";
        } else {
            actor.modifyStatisticMaximum(ActorStatistics.HEALTH, StatisticOperations.DECREASE, 1);
            msg = actor + " eats a toxic cookie! Max HP decreased.";
        }

        if (usages <= 0) actor.getInventory().remove(this);
        return msg;
    }

    @Override
    public ActionList allowableActions(Actor owner, Location location) {
        ActionList actions = new ActionList();
        if (owner != null && usages > 0) actions.add(new ConsumeAction(this));
        return actions;
    }
}