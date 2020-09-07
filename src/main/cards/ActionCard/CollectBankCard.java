package main.cards.ActionCard;

import main.players.Player;

/**
 * Represents the Action Card which requires a Player to Collect money from the Bank
 */

final public class CollectBankCard extends ActionCard {
    public CollectBankCard(String name, String description, double amount) {
        super(name, description, amount);
    }

    /**
     * Does the action of the ActionCard (Collect from Bank)
     */
    @Override
    public void activate() {
        // adds the amount to the balance of the person who drew the card
        getOwner().addBalance(getAmount());

        System.out.println(getOwner().getName() + " received $" + getAmount() + " from the bank");
    }

    @Override
    public String toString() {
        return String.format("ActionCard{name=%s,amount=%.2f,transaction=COLLECT,recipient=BANK}", getName(), getAmount());
    }
}
