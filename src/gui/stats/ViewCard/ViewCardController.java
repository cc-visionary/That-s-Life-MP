package gui.stats.ViewCard;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import model.cards.Card;

import javax.swing.text.html.ImageView;

public class ViewCardController {
    @FXML
    private ImageView cardImage;

    @FXML
    private Button continueButton;

    public ViewCardController(Card card) {
        continueButton.setOnAction(e -> {

        });
    }
}
