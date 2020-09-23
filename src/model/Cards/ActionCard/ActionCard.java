package model.Cards.ActionCard;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import model.Constants;
import model.Cards.Card;
import utilities.StringUtil;

/**
 * Represents the Action Card.
 *      This is inherited by different types of Action Cards
 *
 * @since       Aug 18, 2020
 */

public abstract class ActionCard extends Card {
    private double amount;
    public ActionCard(String name, String description, double amount) {
        super(Constants.ACTION_CARD, name, description, "/images/Cards/Action Card.png");
        this.amount = amount;
    }

    /**
     * Returns the value of the amount
     * @return amount
     */
    public double getAmount() {
        return amount;
    }

    public abstract void activate();

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
        Label amount = new Label("Amount: $" + getAmount());
        nameBox.setAlignment(Pos.CENTER);
        nameBox.setTranslateY(130 - 10 * StringUtil.splitStringLength(getDescription(), 40).size());
        nameBox.getChildren().addAll(cardName, amount);

        formattedBox.getChildren().addAll(description, nameBox);
        formattedBox.setAlignment(Pos.CENTER);

        stackPane.getChildren().addAll(cardImage, formattedBox);

        return stackPane;
    }
}
