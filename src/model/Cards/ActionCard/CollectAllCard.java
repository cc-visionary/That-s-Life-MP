package model.Cards.ActionCard;

import model.GameOfLife;
import model.Players.Player;

/**
 * Represents the Action Card which requires a Player to Collect money from All the other Players
 */

final public class CollectAllCard extends ActionCard {
    public CollectAllCard(String name, String description, int amount) {
        super(name, description, amount);
    }

    /**
     * Does the action of the ActionCard (Collect from All the Players)
     */
    @Override
    public void activate() {
        // deducts the amount of money to all the other players
        for(Player otherPlayer : getOtherPlayers()) {
            otherPlayer.payBalance(getAmount());
        }

        GameOfLife.addRoundStat(String.format("%s received $%d from all the other players ($%d)", getOwner().getName(), getAmount(), getOtherPlayers().length * getAmount()));

        // then gives all those money to the player who drew the card
        getOwner().addBalance(getOtherPlayers().length * getAmount());

        }

    @Override
    public String toString() {
        return String.format("ActionCard{name=%s,amount=%d,transaction=COLLECT,recipient=ALL}", getName(), getAmount());
    }
}
