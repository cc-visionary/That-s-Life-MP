package gui.modals.ChoosePlayer;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.stage.Stage;
import model.Players.Player;

import java.util.ArrayList;

public class ChoosePlayerController {
    @FXML
    private Button continueButton;

    @FXML
    private ComboBox playerSelection;

    private Player chosenPlayer;

    public void setPlayer(Player[] otherPlayers) {
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
