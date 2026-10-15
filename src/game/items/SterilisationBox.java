package game.items;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.statistics.BaseStatistic;
import game.enums.ItemStatistics;

/**
 * A utility item that allows the player to safely consume contaminated items.
 * Acts as a passive requirement check for various ConsumeAction logic.
 */
public class SterilisationBox extends Item {
    /**
     * Constructor.
     */
    public SterilisationBox() {
        super("Sterilisation Box", '▣');
        this.addNewStatistic(ItemStatistics.WEIGHT, new BaseStatistic(7));
    }
}