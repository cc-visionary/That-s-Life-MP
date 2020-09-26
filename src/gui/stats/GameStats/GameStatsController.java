package gui.stats.GameStats;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.stage.Stage;
import model.GameOfLife;
import model.Players.Player;

import java.util.ArrayList;
import java.util.Collections;

public class GameStatsController {
    @FXML
    private VBox list;

    @FXML
    private Button continueButton;

    public void setData(GameOfLife gameOfLife) {
        ArrayList<Player> retiredPlayers = new ArrayList<>();
        for(Player retiredPlayer : gameOfLife.getAllRetiredPlayers()) retiredPlayers.add(retiredPlayer);

        retiredPlayers.sort((o1, o2) -> o1.getBalance() - o2.getBalance());

        int place = 1;
        for(Player retiredPlayer : retiredPlayers) {
            list.getChildren().add(new Label(String.format("%d. %s (%d)", place, retiredPlayer.getName(), retiredPlayer.getBalance())));
            place++;
        }

        continueButton.setOnAction(e -> {
            AudioClip audioPlayer = new AudioClip(new Media(getClass().getResource("/audio/click.wav").toString()).getSource());
            audioPlayer.play();

            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/gui/Menu/Menu.fxml"))));
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
    }
}
