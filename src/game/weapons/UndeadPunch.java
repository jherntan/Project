package game.weapons;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;

/**
 * A concrete implementation of the Undead's intrinsic weapon.
 * Defines the specific combat stats for the Undead enemy type.
 */
public class UndeadPunch extends IntrinsicWeapon {
    /**
     * Constructor.
     * Sets the damage, action verb, hit rate, and weapon name according to REQ3.
     */
    public UndeadPunch() {
        // REQ3: 1 damage, "punches", 10% hit rate, name "bare fist"
        super(1, "punches", 10, "bare fist");
    }
}