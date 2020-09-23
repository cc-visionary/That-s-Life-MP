package model.Cards.BlueCard;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import model.Constants;
import model.GameOfLife;
import model.Cards.Card;
import utilities.StringUtil;
import model.Players.Player;

import java.util.ArrayList;

/**
 * Represents the Blue Card.
 *      This is inherited by different types of Blue Cards
 */

public abstract class BlueCard extends Card {
    private String career;

    public BlueCard(String name, String description, String career) {
        super(Constants.BLUE_CARD, name, "If player's Career is not the same with the Card's, " + description, "/images/Cards/Blue Card.png");

        this.career = career;
    }

    /**
     * Returns an ArrayList of other Players which has the same career as the card (to be paid to)
     * @param otherPlayers : Player[]
     * @return players : ArrayList<Player></Player>
     */
    private ArrayList<Player> playersWithSameCareer(Player[] otherPlayers) {
        ArrayList<Player> players = new ArrayList<Player>();

        for(Player player : otherPlayers) {
            if(isSameCareer(player)) {
                players.add(player);
            }
        }

        return players;
    }

    /**
     * Checks whether a Player's Career Card is the same career with the card
     * @param player : Player
     * @return true/false : boolean
     */
    private boolean isSameCareer(Player player) {
        return player.getCareerCard().getName() == this.career;
    }

    /**
     * Returns the name of the Career
     * @return career : String
     */
    public String getCareer() {
        return career;
    }

    /**
     * Activates the card by checking if the player who drew the card
     * has the same career with the card. If yes, player receives $15000.
     * If not, player pays the other players with that career / bank.
     */
    public void activate() {
        if(isSameCareer(getOwner())) { // player receives 15000
            getOwner().addBalance(15000);
            GameOfLife.addRoundStat(getOwner().getName() + " receives $15000");
        } else {
            double amountToBePayed = getAmount();

            Player[] playersWithSameCareer = playersWithSameCareer(getOtherPlayers()).toArray(new Player[0]);
            if(playersWithSameCareer.length > 0) { // player pays all the players with the same career
                for(Player otherPlayer : playersWithSameCareer) {
                    getOwner().payBalance(amountToBePayed);
                    otherPlayer.addBalance(amountToBePayed);
                    GameOfLife.addRoundStat(getOwner().getName() + " paid $" + amountToBePayed + " to " + otherPlayer.getName());
                }
            } else { // player pays the bank
                getOwner().payBalance(amountToBePayed);
                GameOfLife.addRoundStat(getOwner().getName() + " paid $" + amountToBePayed + " to the bank.");
            }
        }
    }

    public abstract double getAmount();

    /**
     * Formats the Card into an image with corresponding texts
     * @return StackPane containing the formatted image
     */
    @Override
    public StackPane displayCard() {
        StackPane stackPane = new StackPane();

        ImageView cardImage = new ImageView(getImageURL());
        cardImage.setFitHeight(Screen.getPrimary().getBounds().getMaxY() / 2);
        cardImage.setFitWidth(Screen.getPrimary().getBounds().getMaxX() / 5);

        VBox formattedBox = new VBox();

        Label description = new Label(StringUtil.splitStringIntoNewLine(getDescription(), 40));
        description.setAlignment(Pos.CENTER);

        VBox nameBox = new VBox();
        Label cardName = new Label(getName() + "(" + getType() + ")");
        Label career = new Label("Career: " + getCareer());
        nameBox.setAlignment(Pos.CENTER);
        nameBox.setTranslateY(130 - 10 * StringUtil.splitStringLength(getDescription(), 40).size());
        nameBox.getChildren().addAll(cardName, career);

        formattedBox.getChildren().addAll(description, nameBox);
        formattedBox.setAlignment(Pos.CENTER);

        stackPane.getChildren().addAll(cardImage, formattedBox);
        formattedBox.setAlignment(Pos.CENTER);

        return stackPane;
    }

    @Override
    public String toString() {
        return String.format("BlueCard{name=%s,career=%s}", getName(), getCareer());
    }
}
