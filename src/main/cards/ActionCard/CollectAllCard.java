package main.cards.ActionCard;

import main.players.Player;

/**
 * Represents the Action Card which requires a Player to Collect money from All the other Players
 */

final public class CollectAllCard extends ActionCard {
    public CollectAllCard(String name, String description, double amount) {
        super(name, description, amount);
    }

    /**
     * Does the action of the ActionCard (Collect from All the Players)
     * @param player
     * @param otherPlayers
     */
    @Override
    public void activate(Player player, Player[] otherPlayers) {
        // deducts the amount of money to all the other players
        for(Player otherPlayer : otherPlayers) {
            otherPlayer.payBalance(getAmount());
        }

        // then gives all those money to the player who drew the card
        player.addBalance(otherPlayers.length * getAmount());

        System.out.println(player.getName() + " received $" + getAmount() + " from all the other players. ($" + otherPlayers.length * getAmount() + ")");
    }

    @Override
    public String toString() {
        return String.format("ActionCard{name=%s,amount=%.2f,transaction=COLLECT,recipient=ALL}", getName(), getAmount());
    }
}
