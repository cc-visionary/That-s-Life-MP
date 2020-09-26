package model.Cards.HouseCard;

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
 * Represents the  House Card
 *      which contains details about a House which can be bought by the Player in BuyAHouseSpace
 * @see model.Spaces.MagentaSpace.BuyAHouseSpace
 */

public class HouseCard extends Card {
    private int cost;
    public HouseCard(String name, String description, int cost) {
        super(Constants.HOUSE_CARD, name, description, "/images/Cards/House Card.png");
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return String.format("HouseCard{name=%s,cost=%d}", getName(), getCost());
    }

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
        Label cost = new Label("Cost: $" + getCost());
        nameBox.setAlignment(Pos.CENTER);
        nameBox.setTranslateY(130 - 10 * StringUtil.splitStringLength(getDescription(), 40).size());
        nameBox.getChildren().addAll(cardName, cost);

        formattedBox.getChildren().addAll(description, nameBox);
        formattedBox.setAlignment(Pos.CENTER);

        stackPane.getChildren().addAll(cardImage, formattedBox);
        formattedBox.setAlignment(Pos.CENTER);


        return stackPane;
    }
}
