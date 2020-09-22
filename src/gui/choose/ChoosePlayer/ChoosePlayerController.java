package gui.choose.ChoosePlayer;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import model.players.Player;

import java.util.ArrayList;

public class ChoosePlayerController {
    @FXML
    private Button continueButton;

    @FXML
    private ComboBox playerSelection;

    public void setPlayer(Player[] otherPlayers) {
        ArrayList<String> choices = new ArrayList<String>();
        for(Player player : otherPlayers) choices.add(player.getName() + " ($" + player.getBalance() + ")");

        playerSelection.getItems().addAll(choices);
        playerSelection.setValue(choices.get(0));

        continueButton.setOnAction(e -> {
//            otherPlayers[playerSelection.getSelectionModel().getSelectedIndex()]
            ((Stage)((Node) e.getSource()).getScene().getWindow()).close();
        });
    }
}
