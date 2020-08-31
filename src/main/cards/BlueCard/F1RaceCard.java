package main.cards.BlueCard;

import main.players.Player;
import main.utilities.StringUtil;

/**
 * Represents the Blue Card - F1 Race Card
 */

final public class F1RaceCard extends BlueCard {
    public F1RaceCard(String career) {
        super("F1 Race", "The Player pays 10% of his current salary", career);
    }

    /**
     * Returns 10% of the player who drew the card's current salary
     * @return 10% of the salary of the player
     */
    @Override
    public double getAmount() {
        return getOwner().getSalaryCard().getSalary() * 0.1;
    }
}