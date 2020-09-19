package main.cards.HouseCard;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import main.Constants;
import main.cards.Card;

/**
 * Represents the  House Card
 *      which contains details about a House which can be bought by the Player in BuyAHouseSpace
 * @see main.spaces.MagentaSpace.BuyAHouseSpace
 */

public class HouseCard extends Card {
    private double cost;
    public HouseCard(String name, String description, double cost) {
        super(Constants.HOUSE_CARD, name, description, "/images/Cards/House Card.png");
        this.cost = cost;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return String.format("HouseCard{name=%s,cost=%.2f}", getName(), getCost());
    }

    @Override
    public StackPane displayCard() {
        StackPane stackPane = new StackPane();

        ImageView cardImage = new ImageView(getImageURL());
        cardImage.setFitHeight(Screen.getPrimary().getBounds().getMaxY() / 2);
        cardImage.setFitWidth(Screen.getPrimary().getBounds().getMaxX() / 5);

        VBox nameBox = new VBox();
        Label type = new Label(getType());
        Label name = new Label(getName());
        Label cost = new Label("Cost: $" + getCost());
        Label description = new Label(getDescription());

        nameBox.getChildren().addAll(type, name, cost);
        nameBox.setAlignment(Pos.BOTTOM_CENTER);

        stackPane.getChildren().addAll(cardImage, description, nameBox);
        stackPane.setAlignment(nameBox, Pos.BOTTOM_CENTER);


        return stackPane;
    }
}
