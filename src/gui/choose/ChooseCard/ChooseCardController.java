package gui.choose.ChooseCard;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.cards.Card;

import javax.swing.text.html.ImageView;

public class ChooseCardController {
    @FXML
    private ImageView cardImage1, cardImage2;

    @FXML
    private Button card1Button, card2Button;

    public void setCard(Card card1, Card card2) {
        card1Button.setOnAction(e -> {
            ((Stage)((Node) e.getSource()).getScene().getWindow()).close();
        });

        card2Button.setOnAction(e -> {
            ((Stage)((Node) e.getSource()).getScene().getWindow()).close();
        });
    }
}
