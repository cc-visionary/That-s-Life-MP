package main.cards.ActionCard;

import main.GameOfLife;
import main.players.Player;
import main.utilities.InputUtil;

/**
 * Represents the Action Card which requires a Player to Collect money from another Player
 */

final public class CollectPlayerCard extends ActionCard {
    public CollectPlayerCard(String name, String description, double amount) {
        super(name, description, amount);
    }

    /**
     * Does the action of the ActionCard (Collect from Player)
     */
    @Override
    public void activate() {
        Player recipient = chooseOtherPlayer();

        // deducts the amount from balance of the chosen player
        recipient.payBalance(getAmount());

        // transfers the amount to the balance of one who drew the card
        getOwner().addBalance(getAmount());

        GameOfLife.addRoundStat(String.format("%s received $%.2f from %s", getOwner().getName(), getAmount(), recipient.getName()));
    }

    @Override
    public String toString() {
        return String.format("ActionCard{name=%s,amount=%.2f,transaction=COLLECT,recipient=PLAYER}", getName(), getAmount());
    }
}
