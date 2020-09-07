package main.cards.ActionCard;

import main.players.Player;
import main.utilities.InputUtil;

/**
 * Represents the Action Card which requires a Player to Pay money to another Player
 */

final public class PayPlayerCard extends ActionCard {
    public PayPlayerCard(String name, String description, double amount) {
        super(name, description, amount);
    }

    /**
     * Does the action of the ActionCard (Pay to another Player)
     */
    @Override
    public void activate() {
        Player recipient = chooseOtherPlayer();

        // deducts from the player who drew the card
        getOwner().payBalance(getAmount());

        // transfer the deducted balance to the player chosen
        recipient.addBalance(getAmount());

        System.out.println(getOwner().getName() + " paid $" + getAmount() + " to " + recipient.getName());
    }

    @Override
    public String toString() {
        return String.format("ActionCard{name=%s,amount=%.2f,transaction=PAY,recipient=PLAYER}", getName(), getAmount());
    }
}
