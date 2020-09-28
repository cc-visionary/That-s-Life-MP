package gui.modals.ChoosePlayer;

import com.sun.org.apache.xml.internal.security.Init;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.stage.Stage;
import model.Players.Player;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Controls the Choose Player UI (ChoosePlayer.fxml)
 */

public class ChoosePlayerController implements Initializable {
    @FXML
    private Button continueButton;

    @FXML
    private ComboBox playerSelection;

    private Player chosenPlayer;

    private Player[] otherPlayers;

    public ChoosePlayerController(Player[] otherPlayers) {
        this.otherPlayers = otherPlayers;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<String> choices = new ArrayList<String>();
        for(Player player : otherPlayers) choices.add(player.getName() + " ($" + player.getBalance() + ")");

        playerSelection.getItems().addAll(choices);
        playerSelection.setValue(choices.get(0));

        continueButton.setOnAction(e -> {
            AudioClip audioPlayer = new AudioClip(new Media(getClass().getResource("/audio/click.wav").toString()).getSource());
            audioPlayer.play();

            chosenPlayer = otherPlayers[playerSelection.getSelectionModel().getSelectedIndex()];
            ((Stage)((Node) e.getSource()).getScene().getWindow()).close();
        });
    }

    public Player getChosenPlayer() {
        return chosenPlayer;
    }
}
