package main.cards.ActionCard;

import main.players.Player;

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

        System.out.println(getOwner().getName() + " paid $" + getAmount() + " to all the other players($" + getOtherPlayers().length * getAmount() + ")");
    }

    @Override
    public String toString() {
        return String.format("ActionCard{name=%s,amount=%.2f,transaction=PAY,recipient=ALL}", getName(), getAmount());
    }
}
