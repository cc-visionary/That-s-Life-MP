package gui.choose;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.Constants;
import main.cards.ActionCard.ActionCard;
import main.cards.BlueCard.BlueCard;
import main.cards.Card;
import main.cards.CareerCard.CareerCard;
import main.cards.HouseCard.HouseCard;
import main.cards.SalaryCard.SalaryCard;
import main.decks.Deck;
import main.players.Player;

public class ChooseCard {
    private static Card chosenCard;

    public static void displayCard(Card card) {
        Stage newStage = new Stage();
        newStage.initStyle(StageStyle.UNDECORATED);

        HBox hbox = new HBox();

        Button cardButton = new Button();
        cardButton.setGraphic(handleCard(card));
        cardButton.setOnAction(e -> {
            newStage.close();
        });
        Tooltip tooltip = new Tooltip("Continue");
        cardButton.setTooltip(tooltip);

        hbox.getChildren().add(cardButton);
        newStage.setScene(new Scene(hbox));

        newStage.showAndWait();
    }

    /**
     * Show and choose from 2 Cards
     * @param card1
     * @param card2
     */
    public static Card choose2Cards(Card card1, Card card2) {
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        HBox hbox = new HBox();

        Button card1Button = new Button();
        card1Button.setGraphic(handleCard(card1));
        card1Button.setOnAction(e -> {
            chosenCard = card1;
            stage.close();
        });


        Button card2Button = new Button();
        card2Button.setGraphic(handleCard(card2));
        card2Button.setOnAction(e -> {
            chosenCard = card2;
            stage.close();
        });

        hbox.getChildren().addAll(card1Button, card2Button);
        stage.setScene(new Scene(hbox));

        stage.showAndWait();
        return getChosenCard();
    }

    /**
     * Handles the displaying of the card in a way that type casts and uses
     * the overriden function
     * @param card to be handled
     * @return output Pane
     */
    private static Pane handleCard(Card card) {
        if(card.getType().equals(Constants.ACTION_CARD)) return ((ActionCard) card).displayCard();
        else if(card.getType().equals(Constants.BLUE_CARD)) return ((BlueCard) card).displayCard();
        else if(card.getType().equals(Constants.HOUSE_CARD)) return ((HouseCard) card).displayCard();
        else if(card.getType().equals(Constants.CAREER_CARD)) return ((CareerCard) card).displayCard();
        else if(card.getType().equals(Constants.SALARY_CARD)) return ((SalaryCard) card).displayCard();

        return card.displayCard();
    }

    public static Card getChosenCard() {
        return chosenCard;
    }
}
