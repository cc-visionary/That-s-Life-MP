package model.cards.CareerCard;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import model.Constants;
import model.cards.Card;
import utilities.RandomUtil;
import utilities.StringUtil;

/**
 * Represents the Career Card
 */

final public class CareerCard extends Card {
    private int maxPayRaise;
    private boolean requireCollegeDegree;

    public CareerCard(String name, String description, int minPayRaise, int maxPayRaise, boolean requireCollegeDegree) {
        super(Constants.CAREER_CARD, name, description, "/images/Cards/Career Card.png");

        // randomly chooses the maxPayRaise from parameters minPayRaise - maxPayRaise
        this.maxPayRaise = RandomUtil.chooseRandomNumber(minPayRaise, maxPayRaise);
        this.requireCollegeDegree = requireCollegeDegree;
    }

    /**
     * Returns the value of the Maximum Pay Raise for the Career
     * @return maxPayRaise
     */
    public int getMaxPayRaise() {
        return maxPayRaise;
    }

    /**
     * Returns whether the career card needs a college degree or not
     * @return requireCollegeDegree
     */
    public boolean isRequireCollegeDegree() {
        return requireCollegeDegree;
    }

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
        Label maxPayRaise = new Label("Max Pay Raise: " + getMaxPayRaise());
        Label collegeDegree = new Label("College Degree: " + (isRequireCollegeDegree() ? "Yes" : "No"));
        nameBox.setAlignment(Pos.CENTER);
        nameBox.setTranslateY(130 - 10 * StringUtil.splitStringLength(getDescription(), 40).size());
        nameBox.getChildren().addAll(cardName, maxPayRaise, collegeDegree);

        formattedBox.getChildren().addAll(description, nameBox);
        formattedBox.setAlignment(Pos.CENTER);

        stackPane.getChildren().addAll(cardImage, formattedBox);
        formattedBox.setAlignment(Pos.CENTER);

        return stackPane;
    }

    @Override
    public String toString() {
        return String.format("CareerCard{name=%s,maxPayRaise=%d,collegeRequired=%s}", getName(), getMaxPayRaise(), isRequireCollegeDegree() ? "Yes" : "No");
    }
}
