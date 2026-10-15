package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.Effect;
import game.interfaces.Ignitable;

/**
 * A class representing fire on the ground.
 * Deals burning damage to actors and eventually burns out, turning back into a floor.
 */
public class Fire extends Ground implements Ignitable {
    /** Remaining duration of the fire in turns */
    private int life = 5;

    /**
     * Constructor.
     */
    public Fire() {
        super('^', "Fire");
    }

    /**
     * Updates the fire state each turn. Applies the burning effect to actors
     * standing on the tile and reduces the fire's remaining life.
     * @param loc The location where the fire is currently burning.
     */
    @Override
    public void tick(Location loc) {
        if (loc.getActor() != null) {
            loc.getActor().enableAbility(Effect.BURNING);
        }

        life--;

        if (life <= 0) {
            loc.setGround(new Floor());
        }
    }

    /**
     * Resets the fire's duration to maximum.
     */
    @Override
    public void ignite() {
        life = 5;
    }

    /**
     * Immediately extinguishes the fire.
     */
    @Override
    public void extinguish() {
        life = 0;
    }

    /**
     * Checks if the fire is currently active.
     * @return true if life is greater than 0.
     */
    @Override
    public boolean isLit() {
        return life > 0;
    }
}