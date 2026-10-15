package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.ActorStatistics;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.statistics.StatisticOperations;
import game.items.FirstAidKit;

/**
 * An Action to use a First Aid Kit.
 * This action increases the actor's maximum health and restores them to full HP.
 */
public class FirstAidKitAction extends Action {
    private final FirstAidKit kit;

    /**
     * Constructor.
     * @param kit the FirstAidKit item instance
     */
    public FirstAidKitAction(FirstAidKit kit) {
        this.kit = kit;
    }

    /**
     * Increases the actor's Max HP, heals them to full, and puts the kit on cooldown.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of the health increase and restoration.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // Use modifyStatisticMaximum as defined in your GameEntity.java
        actor.modifyStatisticMaximum(ActorStatistics.HEALTH, StatisticOperations.INCREASE, 1);

        // Restore health to full
        int maxHealth = actor.getMaximumStatistic(ActorStatistics.HEALTH);
        int currentHealth = actor.getStatistic(ActorStatistics.HEALTH);
        actor.heal(maxHealth - currentHealth);

        kit.startCooldown();
        return actor + " uses the First Aid Kit! Max HP increased and health restored.";
    }

    /**
     * Returns a descriptive string for the menu.
     * @param actor The actor performing the action.
     * @return description for the action menu.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " uses the First Aid Kit";
    }
}