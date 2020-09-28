package gui.modals.DisplayCard;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.stage.Stage;
import model.Cards.Card;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * Controls the Choose Move UI (ChooseMove.fxml)
 */

public class DisplayCardController implements Initializable {
    @FXML
    private Button continueButton;

    private Card card;

    public DisplayCardController(Card card) {
        this.card = card;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        continueButton.setOnAction(e -> {
            AudioClip audioPlayer = new AudioClip(new Media(getClass().getResource("/audio/click.wav").toString()).getSource());
            audioPlayer.play();

            ((Stage)((Node) e.getSource()).getScene().getWindow()).close();
        });
        continueButton.setTooltip(new Tooltip("click to continue"));
        continueButton.setGraphic(card.displayCard());
    }
}
