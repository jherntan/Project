package game.interfaces;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;

public interface Unlockable {
    String unlockedBy(Actor actor, Location location);
}