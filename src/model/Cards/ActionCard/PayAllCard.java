package model.Cards.ActionCard;

import model.GameOfLife;
import model.Players.Player;

/**
 * Represents the Action Card which requires a Player to Pay money to All the other Players
 */

final public class PayAllCard extends ActionCard {
    public PayAllCard(String name, String description, int amount) {
        super(name, description, amount);
    }

    /**
     * Does the action of the ActionCard (Pay to All the Players)
     */
    @Override
    public void activate() {
        // deducts the number of players * amount from the player who drew the card's balance
        getOwner().payBalance(getOtherPlayers().length * getAmount());

        GameOfLife.addRoundStat(String.format("%s paid $%d to all the other players ($%d)", getOwner().getName(), getAmount(), getOtherPlayers().length * getAmount()));

        // then gives it to all the other players seperately
        for(Player otherPlayer : getOtherPlayers()) {
            otherPlayer.addBalance(getAmount());
        }
    }

    @Override
    public String toString() {
        return String.format("ActionCard{name=%s,amount=%d,transaction=PAY,recipient=ALL}", getName(), getAmount());
    }
}
