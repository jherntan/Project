package game.items;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.statistics.BaseStatistic;
import game.enums.ItemStatistics;

/**
 * A heavy piece of scrap metal found in the facility.
 * Primarily used for inventory weight management testing.
 */
public class CRTMonitor extends Item {
    /**
     * Constructor.
     */
    public CRTMonitor() {
        super("CRT Monitor", '◙');
        this.addNewStatistic(ItemStatistics.WEIGHT, new BaseStatistic(30));
    }
}