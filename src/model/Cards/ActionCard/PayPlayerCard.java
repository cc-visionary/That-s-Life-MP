package model.Cards.ActionCard;

import model.GameOfLife;
import model.Players.Player;

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

        GameOfLife.addRoundStat(String.format("%s paid $%.2f to %s", getOwner().getName(), getAmount(), recipient.getName()));
    }

    @Override
    public String toString() {
        return String.format("ActionCard{name=%s,amount=%.2f,transaction=PAY,recipient=PLAYER}", getName(), getAmount());
    }
}
