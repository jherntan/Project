package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.statistics.BaseStatistic;
import game.enums.ItemStatistics;
import game.actions.ConsumeAction;
import game.interfaces.Consumable;

/**
 * A portable container for water that provides multiple healing uses.
 */
public class Flask extends Item implements Consumable {
    private int totalUsable = 5;

    /**
     * Constructor.
     */
    public Flask() {
        super("Flask", 'u');
        this.addNewStatistic(ItemStatistics.WEIGHT, new BaseStatistic(5));
    }

    @Override
    public String consumedBy(Actor actor) {
        if (totalUsable > 0) {
            totalUsable--;
            actor.heal(1);
            return actor + " drinks from the flask. (+1 HP, " + totalUsable + " left)";
        }
        return "The flask is empty!";
    }

    @Override
    public ActionList allowableActions(Actor owner, Location location) {
        ActionList actions = new ActionList();
        if (owner != null && totalUsable > 0) {
            actions.add(new ConsumeAction(this));
        }
        return actions;
    }
}