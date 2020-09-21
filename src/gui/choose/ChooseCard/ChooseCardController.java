package gui.choose.ChooseCard;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import model.cards.Card;

import javax.swing.text.html.ImageView;

public class ChooseCardController {
    @FXML
    private ImageView cardImage1, cardImage2;

    @FXML
    private Button card1Button, card2Button;

    public ChooseCardController(Card card1, Card card2) {
        card1Button.setOnAction(e -> {

        });

        card2Button.setOnAction(e -> {

        });
    }
}
