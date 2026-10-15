package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.behaviours.Behaviour;
import game.items.BasicInventory;
import game.behaviours.SlimeEatBehaviour;
import game.behaviours.WanderBehaviour;
import game.enums.Effect;
import java.util.ArrayList;
import java.util.List;

/**
 * A Slime enemy that wanders the map and consumes items like Apples and Cookies.
 */
public class Slime extends Actor {
    private int poisonTurns = 0;
    private final List<Behaviour<Actor, Action>> behaviours = new ArrayList<>();

    /**
     * Constructor.
     */
    public Slime() {
        super("Slime", 's', 25, new BasicInventory());
        behaviours.add(new SlimeEatBehaviour());
        behaviours.add(new WanderBehaviour());
    }

    /**
     * Sets the poison duration for the slime.
     * @param turns ticks of damage.
     */
    public void setPoisonTurns(int turns) {
        this.poisonTurns = turns;
    }

    /**
     * Decides an action for the slime based on prioritized behaviors.
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (poisonTurns > 0) {
            this.hurt(1);
            poisonTurns--;
            if (poisonTurns <= 0) this.disableAbility(Effect.POISONED);
        }

        for (Behaviour<Actor, Action> behaviour : behaviours) {
            Action action = behaviour.operate(this, map.locationOf(this));
            if (action != null) return action;
        }
        return new DoNothingAction();
    }
}