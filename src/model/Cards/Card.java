package model.Cards;

import gui.modals.Modal;
import javafx.scene.layout.StackPane;
import model.Players.Player;

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
    private String imageURL;

    public Card(String type, String name, String description, String imageURL) {
        this.type = type;
        this.name = name;
        this.description = description;
        this.imageURL = imageURL;
    }

    public Card(String type, String description, String imageURL) {
        this.type = type;
        this.description = description;
        this.imageURL = imageURL;
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
        if(players != null) {
            ArrayList<Player> otherPlayers = new ArrayList<Player>();

            // gets the other players besides the owner of the Card
            for(Player player : players) {
                if(!player.equals(owner)) {
                    otherPlayers.add(player);
                }
            }

            this.otherPlayers = otherPlayers.toArray(new Player[0]);
        } else {
            this.otherPlayers = null;
        }
    }

    /**
     * Allows the Player to choose a another Player from the other Players
     * @return the chosen other player
     */
    public Player chooseOtherPlayer() {
        Player chosenPlayer;

        // if more than 1 other player exists, let the player who drew the card choose between them
        if(getOtherPlayers().length > 1) {
            chosenPlayer = new Modal().choosePlayer(getOtherPlayers());
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
     * @return image url for the Card
     */
    public String getImageURL() {
        return imageURL;
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
     * Formats the Card into an image with corresponding texts
     * @return StackPane containing the formatted image
     */
    public abstract StackPane displayCard();

    @Override
    public String toString() {
        return String.format("Card{type=%s,name=%s}", getType(), getName());
    }
}
