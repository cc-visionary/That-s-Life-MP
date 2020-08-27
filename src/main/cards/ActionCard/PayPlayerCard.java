package main.cards.ActionCard;

import main.players.Player;

import java.util.Scanner;

final public class PayPlayerCard extends ActionCard {
    public PayPlayerCard(String name, String description, double amount) {
        super(name, description, amount);
    }

    @Override
    public void activate(Player player, Player[] otherPlayers) {
        Player recipient = choosePlayer(otherPlayers);

        // deducts from the player who drew the card
        player.payBalance(getAmount());

        // transfer the deducted balance to the player chosen
        recipient.addBalance(getAmount());
    }


    /**
     * Asks the user to choose a player from the other players
     * @param players - the other players besides the user who draw the card
     * @return chosenPlayer
     */
    public Player choosePlayer(Player[] players) {
        Scanner scanner = new Scanner(System.in);
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
            System.out.print("Choose the player to pay for: ");
            do {
                choice = scanner.nextInt();
            } while(choice < 1 || choice > players.length);
            chosenPlayer = players[choice - 1];
        } else { // if not, set the default chosen player as the other player indexed at 0
            chosenPlayer = players[0];
        }
        return chosenPlayer;
    }
}
