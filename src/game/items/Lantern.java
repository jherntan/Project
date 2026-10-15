package game.items;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.statistics.BaseStatistic;
import game.grounds.Fire;
import game.enums.ItemStatistics;
import game.interfaces.Ignitable;

/**
 * A portable light source that can ignite the ground.
 * It has a small chance to start a fire on the current tile while lit and carried.
 */
public class Lantern extends Item implements Ignitable {
    /** Whether the lantern is currently turned on */
    private boolean lit = true;
    /** Remaining fuel capacity */
    private int fuel = 10;

    /**
     * Constructor.
     */
    public Lantern() {
        super("Lantern", '&');
        this.addNewStatistic(ItemStatistics.WEIGHT, new BaseStatistic(7));
    }

    /**
     * Updates the lantern state each turn.
     * If lit, there is a 5% chance to consume fuel and spawn a Fire ground object.
     * @param loc The location of the actor carrying the lantern.
     * @param actor The actor carrying the lantern.
     */
    @Override
    public void tick(Location loc, Actor actor) {
        if (isLit() && Math.random() < 0.05) {
            fuel--;
            loc.setGround(new Fire());
        }
    }

    /** Sets the lantern to lit state. */
    @Override
    public void ignite() { lit = true; }

    /** Sets the lantern to extinguished state. */
    @Override
    public void extinguish() { lit = false; }

    /** * Checks if the lantern is currently providing light.
     * @return true if lit and has remaining fuel.
     */
    @Override
    public boolean isLit() { return lit && fuel > 0; }
}