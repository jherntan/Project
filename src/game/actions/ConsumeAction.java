package game.actions;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.items.Item;
import game.interfaces.Consumable;

/**
 * A general Action for consuming items that implement the Consumable interface.
 * This allows for polymorphic consumption of various items like food or medicine.
 */
public class ConsumeAction extends Action {
    private final Item item;

    /**
     * Constructor.
     * @param item the item to be consumed
     */
    public ConsumeAction(Item item) { this.item = item; }

    /**
     * Executes the consumption logic by delegating to the item's consumedBy method.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of the consumption effect.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return ((Consumable) item).consumedBy(actor);
    }

    /**
     * Returns a descriptive string for the menu.
     * @param actor The actor performing the action.
     * @return description of the consumption.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes " + item;
    }
}