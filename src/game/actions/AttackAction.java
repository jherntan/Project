package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;

/**
 * An Action to attack another Actor.
 * This class uses the Actor's intrinsic weapon to deal damage to a target.
 */
public class AttackAction extends Action {
    private final Actor target;
    private final String direction;

    /**
     * Constructor.
     * @param target the Actor to attack
     * @param direction the direction where the target is located (e.g., "North")
     */
    public AttackAction(Actor target, String direction) {
        this.target = target;
        this.direction = direction;
    }

    /**
     * Executes the attack. Retrieves the actor's intrinsic weapon and performs the attack.
     * If the target becomes unconscious, it triggers the unconscious method.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of whether the attack hit and the damage dealt.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        IntrinsicWeapon weapon = actor.getIntrinsicWeapon();
        // The engine handles the hit rate calculation and hurt() call internally
        String result = weapon.attack(actor, target, map);

        if (!target.isConscious()) {
            result += "\n" + target.unconscious(actor, map);
        }
        return result;
    }

    /**
     * Returns a descriptive string for the menu.
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacks " + target + " at " + direction;
    }
}