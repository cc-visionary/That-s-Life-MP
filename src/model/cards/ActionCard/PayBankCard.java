package model.cards.ActionCard;

import model.GameOfLife;

/**
 * Represents the Action Card which requires a Player to Pay money to the Bank
 */

final public class PayBankCard extends ActionCard {
    public PayBankCard(String name, String description, double amount) {
        super(name, description, amount);
    }

    /**
     * Does the action of the ActionCard (Pay to the Bank)
     */
    @Override
    public void activate() {
        // deducts the amount indicated on the card to the player's balance
        getOwner().payBalance(getAmount());

        GameOfLife.addRoundStat(String.format("%s paid $%.2f to the bank", getOwner().getName(), getAmount()));
    }

    @Override
    public String toString() {
        return String.format("ActionCard{name=%s,amount=%.2f,transaction=PAY,recipient=BANK}", getName(), getAmount());
    }
}
