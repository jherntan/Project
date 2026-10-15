package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.items.Inventory;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpAction;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.Effect;
import game.actions.UnlockAction;
import game.interfaces.Unlockable;

/**
 * The player character for Garbage Collection Inc.
 * Handles manual input via a menu and recurring status effects like poison and fire.
 */
public class ContractedWorker extends Actor {
    private int poisonTurns = 0;

    /**
     * Constructor.
     * @param name Name of the worker
     * @param displayChar Character to represent the player on the map
     * @param hitPoints Initial health
     * @param inventory Starting inventory implementation
     */
    public ContractedWorker(String name, char displayChar, int hitPoints, Inventory inventory) {
        super(name, displayChar, hitPoints, inventory);
        this.enableAbility(Effect.WORKER);
    }

    /**
     * Sets the remaining poison duration.
     * @param turns Number of remaining turns the actor will take damage.
     */
    public void setPoisonTurns(int turns) {
        this.poisonTurns = turns;
        this.enableAbility(Effect.POISONED);
    }

    /**
     * Selects and returns an action to perform on the current turn.
     * @return the Action to be executed
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (!this.isConscious()) {
            this.unconscious(map);
            return new DoNothingAction();
        }

        Location here = map.locationOf(this);

        // Scan for items to pick up
        for (Item item : here.getItems()) {
            actions.add(new PickUpAction(item));
        }

        // Scan held items for possible actions
        for (Item item : this.getInventory().getItems()) {
            actions.add(item.allowableActions(this, here));
        }

        // Scan surroundings for unlockable doors
        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            Ground ground = destination.getGround();
            if (ground instanceof Unlockable) {
                actions.add(new UnlockAction((Unlockable) ground, destination));
            }
        }

        if (lastAction.getNextAction() != null)
            return lastAction.getNextAction();

        Menu menu = new Menu(actions);
        Action selectedAction = menu.showMenu(this, display);

        // Apply recurring damage AFTER the menu choice
        handleStatusEffects(display);

        return selectedAction;
    }

    /**
     * Processes recurring damage from poison or fire.
     * @param display The display to print status messages to.
     */
    private void handleStatusEffects(Display display) {
        if (poisonTurns > 0) {
            this.hurt(1);
            poisonTurns--;
            display.println(this + " groans from toxins! (-1 HP). Poison turns remaining: " + poisonTurns);

            if (poisonTurns <= 0) {
                this.disableAbility(Effect.POISONED);
                display.println(this + " has recovered from the poison.");
            }
        }

        if (this.hasAbility(Effect.BURNING)) {
            this.hurt(1);
            display.println(this + " is burning! (-1 HP)");
            this.disableAbility(Effect.BURNING);
        }
    }
}