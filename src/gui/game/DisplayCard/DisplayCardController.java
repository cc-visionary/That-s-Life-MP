package gui.game.DisplayCard;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.cards.Card;
import utilities.StringUtil;

public class DisplayCardController {
    @FXML
    private Button continueButton;

    public void setCard(Card card) {
        continueButton.setOnAction(e -> {
            ((Stage)((Node) e.getSource()).getScene().getWindow()).close();
        });
        continueButton.setTooltip(new Tooltip("click to continue"));
        continueButton.setGraphic(card.displayCard());
    }
}
