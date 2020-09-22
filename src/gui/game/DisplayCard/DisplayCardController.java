package gui.game.DisplayCard;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import model.cards.Card;

import javax.swing.text.html.ImageView;

public class DisplayCardController {
    @FXML
    private ImageView cardImage;

    @FXML
    private Button continueButton;

    public DisplayCardController(Card card) {
        continueButton.setOnAction(e -> {

        });
    }
}
