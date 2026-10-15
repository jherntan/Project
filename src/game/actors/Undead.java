package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.behaviours.Behaviour;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.items.BasicInventory;
import game.behaviours.AttackBehaviour;
import game.behaviours.WanderBehaviour;
import game.weapons.UndeadPunch;
import java.util.ArrayList;
import java.util.List;

/**
 * A hostile Undead actor that attacks players and wanders aimlessly.
 */
public class Undead extends Actor {
    private final List<Behaviour<Actor, Action>> behaviours = new ArrayList<>();

    /**
     * Constructor.
     */
    public Undead() {
        super("Undead", 'u', 15, new BasicInventory());
        // Priority 1: Try to attack the player
        behaviours.add(new AttackBehaviour());
        // Priority 2: Walk around if no target is found
        behaviours.add(new WanderBehaviour());
    }

    /**
     * Returns the concrete weapon class for the undead.
     * @return an UndeadPunch instance.
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new UndeadPunch();
    }

    /**
     * Executes behaviors in order of priority.
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        for (Behaviour<Actor, Action> behaviour : behaviours) {
            Action action = behaviour.operate(this, map.locationOf(this));
            if (action != null) return action;
        }
        return new DoNothingAction();
    }
}