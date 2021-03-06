package model.Cards.BlueCard;

import model.Constants;

/**
 * Represents the Blue Card - F1 Race Card
 */

final public class F1RaceCard extends BlueCard {
    public F1RaceCard() {
        super("F1 Race", "The Player pays 10% of his current salary", Constants.RACECAR_DRIVER);
    }

    /**
     * Returns 10% of the player who drew the card's current salary
     * @return 10% of the salary of the player
     */
    @Override
    public int getAmount() {
        return getOwner().getSalaryCard().getSalary() / 10;
    }
}