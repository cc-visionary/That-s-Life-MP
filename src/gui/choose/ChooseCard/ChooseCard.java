package gui.choose.ChooseCard;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Constants;
import model.cards.ActionCard.ActionCard;
import model.cards.BlueCard.BlueCard;
import model.cards.Card;
import model.cards.CareerCard.CareerCard;
import model.cards.HouseCard.HouseCard;
import model.cards.SalaryCard.SalaryCard;

public class ChooseCard {
    private static Card chosenCard;

    public static void displayCard(Card card) {
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);

        HBox hbox = new HBox();
        hbox.setStyle("-fx-background-color: aliceblue; -fx-font-size: 16; -fx-border-width: 1; -fx-border-color: gray");

        Button cardButton = new Button();
        cardButton.setGraphic(handleCard(card));
        cardButton.setOnAction(e -> {
            stage.close();
        });
        Tooltip tooltip = new Tooltip("click to continue");
        cardButton.setTooltip(tooltip);

        hbox.getChildren().add(cardButton);
        stage.setScene(new Scene(hbox));

        stage.showAndWait();
    }

    /**
     * Show and choose from 2 Cards
     * @param card1
     * @param card2
     */
    public static Card choose2Cards(Card card1, Card card2) {
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        HBox hbox = new HBox();

        Tooltip card1Tooltip = new Tooltip("choose " + (card1.getName() != null ? card1.getName() + "(" + card1.getType() + ")" : card1.getType()));
        Button card1Button = new Button();
        card1Button.setGraphic(handleCard(card1));
        card1Button.setOnAction(e -> {
            chosenCard = card1;
            stage.close();
        });
        card1Button.setTooltip(card1Tooltip);

        Tooltip card2Tooltip = new Tooltip("choose " + (card2.getName() != null ? card2.getName() + "(" + card2.getType() + ")" : card2.getType()));
        Button card2Button = new Button();
        card2Button.setGraphic(handleCard(card2));
        card2Button.setOnAction(e -> {
            chosenCard = card2;
            stage.close();
        });
        card1Button.setTooltip(card2Tooltip);

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
