package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location; // Ensure this is imported
import edu.monash.fit2099.engine.statistics.BaseStatistic;
import game.actions.FirstAidKitAction;
import game.enums.ItemStatistics;

/**
 * A high-value medical item that fully restores health and increases Max HP.
 * Has a long cooldown period between uses.
 */
public class FirstAidKit extends Item {
    private int cooldown = 0;

    public FirstAidKit() {
        super("First Aid Kit", '+');
        this.addNewStatistic(ItemStatistics.WEIGHT, new BaseStatistic(25));
    }

    /**
     * Reduces the cooldown timer every turn.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        if (cooldown > 0) cooldown--;
    }

    /**
     * Updated signature to match the Item superclass.
     * Added 'Location location' parameter.
     */
    @Override
    public ActionList allowableActions(Actor owner, Location location) {
        ActionList actions = new ActionList();
        if (cooldown <= 0) {
            actions.add(new FirstAidKitAction(this));
        }
        return actions;
    }

    public void startCooldown() {
        this.cooldown = 20;
    }
}