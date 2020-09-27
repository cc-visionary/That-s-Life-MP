package model.Cards.ActionCard;

import model.GameOfLife;
import model.Players.Player;

/**
 * Represents the Action Card which requires a Player to Collect money from another Player
 */

final public class CollectPlayerCard extends ActionCard {
    public CollectPlayerCard(String name, String description, int amount) {
        super(name, description, amount);
    }

    /**
     * Does the action of the ActionCard (Collect from Player)
     */
    @Override
    public void activate() {
        Player recipient = chooseOtherPlayer();

        GameOfLife.addRoundStat(String.format("%s received $%d from %s", getOwner().getName(), getAmount(), recipient.getName()));

        // deducts the amount from balance of the chosen player
        recipient.payBalance(getAmount());

        // transfers the amount to the balance of one who drew the card
        getOwner().addBalance(getAmount());

        }

    @Override
    public String toString() {
        return String.format("ActionCard{name=%s,amount=%d,transaction=COLLECT,recipient=PLAYER}", getName(), getAmount());
    }
}
