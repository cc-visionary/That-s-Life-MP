package gui.modals.ChooseCard;

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
 * Controls the Choose Card UI (ChooseCard.fxml)
 */

public class ChooseCardController implements Initializable {
    @FXML
    private Button card1Button, card2Button;

    private Card chosenCard;

    private Card card1, card2;

    public ChooseCardController(Card card1, Card card2) {
        this.card1 = card1;
        this.card2 = card2;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // card1 stuff
        card1Button.setOnAction(e -> {
            AudioClip audioPlayer = new AudioClip(new Media(getClass().getResource("/audio/click.wav").toString()).getSource());
            audioPlayer.play();

            chosenCard = card1;
            ((Stage)((Node) e.getSource()).getScene().getWindow()).close();
        });
        card1Button.setTooltip(new Tooltip("choose " + card1.getName()));
        card1Button.setGraphic(card1.displayCard());

        // card 2 stuff
        card2Button.setOnAction(e -> {
            AudioClip audioPlayer = new AudioClip(new Media(getClass().getResource("/audio/click.wav").toString()).getSource());
            audioPlayer.play();

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
