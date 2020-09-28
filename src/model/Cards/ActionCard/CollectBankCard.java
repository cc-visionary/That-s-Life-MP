package model.Cards.ActionCard;

import model.GameOfLife;

/**
 * Represents the Action Card which requires a Player to Collect money from the Bank
 */

final public class CollectBankCard extends ActionCard {
    public CollectBankCard(String name, String description, int amount) {
        super(name, description, amount);
    }

    /**
     * Does the action of the ActionCard (Collect from Bank)
     */
    @Override
    public void activate() {
        GameOfLife.addRoundStat(String.format("%s received $%d from the bank", getOwner().getName(), getAmount()));

        // adds the amount to the balance of the person who drew the card
        getOwner().addBalance(getAmount());

    }

    @Override
    public String toString() {
        return String.format("ActionCard{name=%s,amount=%d,transaction=COLLECT,recipient=BANK}", getName(), getAmount());
    }
}
