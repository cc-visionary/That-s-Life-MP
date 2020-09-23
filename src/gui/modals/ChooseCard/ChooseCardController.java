package gui.modals.ChooseCard;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import model.Cards.Card;

public class ChooseCardController {
    @FXML
    private Button card1Button, card2Button;

    private Card chosenCard;

    public void setCards(Card card1, Card card2) {
        // card1 stuff
        card1Button.setOnAction(e -> {
            chosenCard = card1;
            ((Stage)((Node) e.getSource()).getScene().getWindow()).close();
        });
        card1Button.setTooltip(new Tooltip("choose " + card1.getName()));
        card1Button.setGraphic(card1.displayCard());

        // card 2 stuff
        card2Button.setOnAction(e -> {
            chosenCard = card2;
            ((Stage)((Node) e.getSource()).getScene().getWindow()).close();
        });
        card2Button.setTooltip(new Tooltip("choose " + card2.getName()));
        card2Button.setGraphic(card2.displayCard());
    }

    public Card getChosenCard() {
        return chosenCard;
    }
}
