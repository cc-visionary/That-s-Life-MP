package model.cards.ActionCard;

import model.GameOfLife;
import model.players.Player;

/**
 * Represents the Action Card which requires a Player to Pay money to All the other Players
 */

final public class PayAllCard extends ActionCard {
    public PayAllCard(String name, String description, double amount) {
        super(name, description, amount);
    }

    /**
     * Does the action of the ActionCard (Pay to All the Players)
     */
    @Override
    public void activate() {
        // deducts the number of players * amount from the player who drew the card's balance
        getOwner().payBalance(getOtherPlayers().length * getAmount());

        // then gives it to all the other players seperately
        for(Player otherPlayer : getOtherPlayers()) {
            otherPlayer.addBalance(getAmount());
        }

        GameOfLife.addRoundStat(String.format("%s paid $%.2f to all the other players ($%.2f)", getOwner().getName(), getAmount(), getOtherPlayers().length * getAmount()));
    }

    @Override
    public String toString() {
        return String.format("ActionCard{name=%s,amount=%.2f,transaction=PAY,recipient=ALL}", getName(), getAmount());
    }
}
