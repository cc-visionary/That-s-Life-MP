package model.Cards.ActionCard;

import model.GameOfLife;

/**
 * Represents the Action Card which requires a Player to Pay money to the Bank
 */

final public class PayBankCard extends ActionCard {
    public PayBankCard(String name, String description, int amount) {
        super(name, description, amount);
    }

    /**
     * Does the action of the ActionCard (Pay to the Bank)
     */
    @Override
    public void activate() {
        GameOfLife.addRoundStat(String.format("%s paid $%d to the bank", getOwner().getName(), getAmount()));

        // deducts the amount indicated on the card to the player's balance
        getOwner().payBalance(getAmount());
    }

    @Override
    public String toString() {
        return String.format("ActionCard{name=%s,amount=%d,transaction=PAY,recipient=BANK}", getName(), getAmount());
    }
}
