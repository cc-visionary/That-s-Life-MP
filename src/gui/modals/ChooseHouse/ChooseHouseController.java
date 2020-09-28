package gui.modals.ChooseHouse;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.stage.Stage;
import model.Cards.Card;
import model.Cards.HouseCard.HouseCard;
import model.Decks.Deck;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Controls the Choose House UI (ChooseHouse.fxml)
 */

public class ChooseHouseController implements Initializable {
    @FXML
    private ComboBox choices;

    @FXML
    private Button buyButton;

    private HouseCard chosenHouse;

    private Deck houseDeck;

    public ChooseHouseController(Deck houseDeck) {
        this.houseDeck = houseDeck;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<String> stringChoices = new ArrayList<String>();

        for(Card card : houseDeck.getCards()) {
            HouseCard houseCard = (HouseCard) card;
            stringChoices.add(String.format("%s ($%d)", houseCard.getName(), houseCard.getCost()));
        }

        choices.getItems().addAll(stringChoices);
        choices.setValue(stringChoices.get(0));

        buyButton.setOnAction(e -> {
            AudioClip audioPlayer = new AudioClip(new Media(getClass().getResource("/audio/click.wav").toString()).getSource());
            audioPlayer.play();

            chosenHouse = (HouseCard) houseDeck.getCards().get(choices.getSelectionModel().getSelectedIndex());
            ((Stage) ((Node) e.getSource()).getScene().getWindow()).close();
        });
    }

    public HouseCard getChosenHouse() {
        return chosenHouse;
    }
}
