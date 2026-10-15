package game.items;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Inventory;
import edu.monash.fit2099.engine.items.Item;
import game.enums.ItemStatistics;

/**
 * A specialized inventory implementation that enforces a maximum weight capacity.
 * Items can only be added if their weight does not exceed the remaining capacity.
 */
public class WeightLimitedInventory extends Inventory {
    /** Maximum weight capacity allowed */
    private int weightLimit;
    /** Current total weight of all items in inventory */
    private int currentWeight;

    /**
     * Constructor.
     * @param weightLimit The maximum total weight this inventory can hold.
     */
    public WeightLimitedInventory(int weightLimit) {
        this.weightLimit = weightLimit;
        this.currentWeight = 0;
    }

    /**
     * Attempts to add an item to the inventory.
     * Checks the item's WEIGHT statistic against the current available capacity.
     * @param item The item to be added.
     * @return true if the item was added successfully, false if it was too heavy.
     */
    @Override
    public boolean add(Item item) {
        int itemWeight = 0;
        if (item.hasStatistic(ItemStatistics.WEIGHT)) {
            itemWeight = item.getStatistic(ItemStatistics.WEIGHT);
        }

        if (this.currentWeight + itemWeight <= this.weightLimit) {
            this.items.add(item);
            this.currentWeight += itemWeight;
            return true;
        } else {
            new Display().println("Too heavy to pick up " + item + "!");
            return false;
        }
    }

    /**
     * Removes an item from the inventory and updates the current total weight.
     * @param item The item to be removed.
     * @return true if the item was found and removed, false otherwise.
     */
    @Override
    public boolean remove(Item item) {
        if (items.contains(item)) {
            if (item.hasStatistic(ItemStatistics.WEIGHT)) {
                this.currentWeight -= item.getStatistic(ItemStatistics.WEIGHT);
            }
            items.remove(item);
            return true;
        }
        return false;
    }
}