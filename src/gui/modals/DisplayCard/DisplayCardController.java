package gui.modals.DisplayCard;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import model.Cards.Card;

public class DisplayCardController {
    @FXML
    private Button continueButton;

    public void setCard(Card card) {
        continueButton.setOnAction(e -> {
            AudioClip audioPlayer = new AudioClip(new Media(getClass().getResource("/audio/click.wav").toString()).getSource());
            audioPlayer.play();

            ((Stage)((Node) e.getSource()).getScene().getWindow()).close();
        });
        continueButton.setTooltip(new Tooltip("click to continue"));
        continueButton.setGraphic(card.displayCard());
    }
}
