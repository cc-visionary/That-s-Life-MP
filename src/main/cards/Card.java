package main.cards;

import main.players.Player;
import main.utilities.InputUtil;
import main.utilities.StringUtil;

import java.util.ArrayList;

/**
 * Represents the Card
 *      to be inherited by all the other Cards
 */

public abstract class Card {
    private String type, name;
    private String description;
    private Player owner;
    private Player[] otherPlayers;

    public Card(String type, String name, String description) {
        this.type = type;
        this.name = name;
        this.description = description;
    }

    public Card(String type, String description) {
        this.type = type;
        this.description = description;
    }

    /**
     * Sets the owner of the Card
     * @param owner the owner to be assigned
     */
    public void setOwner(Player owner) {
        this.owner = owner;
    }

    /**
     * Gets the other players besides the owner of the Card then set it to the otherPlayers attribute
     * @param players all the players
     */
    public void setOtherPlayers(Player[] players) {
        ArrayList<Player> otherPlayers = new ArrayList<Player>();

        // gets the other players besides the owner of the Card
        for(Player player : players) {
            if(!player.equals(owner)) {
                otherPlayers.add(player);
            }
        }

        this.otherPlayers = otherPlayers.toArray(new Player[0]);
    }

    /**
     * Allows the Player to choose a another Player from the other Players
     * @return the chosen other player
     */
    public Player chooseOtherPlayer() {
        Player chosenPlayer;
        int choice;

        // if more than 1 other player exists, let the player who drew the card choose between them
        if(getOtherPlayers().length > 1) {
            // loops and outputs the list of other players to choose from
            System.out.println("Other Players:");
            for(int i = 1; i <= getOtherPlayers().length; i++) {
                System.out.println("[" + i + "] : " + getOtherPlayers()[i - 1].getName());
            }

            // accepts only inputs of 1-# of players
            choice = InputUtil.scanInt("Choose the other player: ", 1, getOtherPlayers().length);

            chosenPlayer = getOtherPlayers()[choice - 1];
        } else if(getOtherPlayers().length == 1) { // if not, set the default chosen player as the other player indexed at 0
            chosenPlayer = getOtherPlayers()[0];
        } else {
            chosenPlayer = null;
            System.out.println("There are no other players...");
        }
        return chosenPlayer;
    }

    /**
     * @return the owner of the card
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * @return other players besides the Player who drew the Card
     */
    public Player[] getOtherPlayers() {
        return otherPlayers;
    }

    /**
     * @return type of the Card
     */
    public String getType() {
        return type;
    }

    /**
     * @return name of the Card
     */
    public String getName() {
        return name;
    }

    /**
     * @return description of the Card
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method displays the card into a 12(height) x 25(width) unit layout
     */
    public void  displayCard() {
        final int length = 23, descriptionHeight = 8;
        String[] splittedString = StringUtil.splitStringLength(getDescription(), length).toArray(new String[0]);

        System.out.println("╭───────────────────────╮");
        System.out.println("│" + StringUtil.centerString(getType(), length)                  + "│");
        System.out.println("├───────────────────────┤");
        for(int i = 0; i < descriptionHeight; i++) {
            if(i < splittedString.length) {
                System.out.println("│" + StringUtil.centerString(splittedString[i], length)  + "│");
            } else {
                System.out.println("│" + StringUtil.centerString("", length)            + "│");
            }
        }
        System.out.println("╰───────────────────────╯");
    }

    @Override
    public String toString() {
        return String.format("Card{type=%sname=%s}", getType(), getName());
    }
}
