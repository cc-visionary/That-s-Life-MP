package main.cards.ActionCard;

import main.players.Player;
import main.utilities.InputUtil;

/**
 * Represents the Action Card which requires a Player to Collect money from another Player
 */

final public class CollectPlayerCard extends ActionCard {
    public CollectPlayerCard(String name, String description, double amount) {
        super(name, description, amount);
    }

    /**
     * Does the action of the ActionCard (Collect from Player)
     * @param player
     * @param otherPlayers
     */
    @Override
    public void activate(Player player, Player[] otherPlayers) {
        Player recipient = choosePlayer(otherPlayers);

        // deducts the amount from balance of the chosen player
        recipient.payBalance(getAmount());

        // transfers the amount to the balance of one who drew the card
        player.addBalance(getAmount());

        System.out.println(player.getName() + " received $" + getAmount() + " from " + recipient.getName());
    }

    /**
     * Asks the user to choose a player from the other players
     * @param players - the other players besides the user who draw the card
     * @return chosenPlayer
     */
    public Player choosePlayer(Player[] players) {
        Player chosenPlayer;
        int choice;

        // if more than 1 other player exists, let the player who drew the card choose between them
        if(players.length > 1) {
            // loops and outputs the list of other players to choose from
            System.out.println("Other Players:");
            for(int i = 1; i <= players.length; i++) {
                System.out.println("[" + i + "] : " + players[i - 1].getName());
            }

            // accepts only inputs of 1-# of players
            choice = InputUtil.scanInt("Choose the player to collect for: ", 1, players.length);

            chosenPlayer = players[choice - 1];
        } else { // if not, set the default chosen player as the other player indexed at 0
            chosenPlayer = players[0];
        }
        return chosenPlayer;
    }

    @Override
    public String toString() {
        return String.format("ActionCard{name=%s,amount=%.2f,transaction=COLLECT,recipient=PLAYER}", getName(), getAmount());
    }
}
