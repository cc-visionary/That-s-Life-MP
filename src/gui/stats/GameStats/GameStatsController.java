package gui.stats.GameStats;

import gui.modals.Modal;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.stage.Stage;
import model.GameOfLife;
import model.Players.Player;

import java.util.ArrayList;

/**
 * Controls the Game Stats UI (GameStats.fxml)
 */

public class GameStatsController {
    @FXML
    private VBox list;

    @FXML
    private Button continueButton;

    public void setData(GameOfLife gameOfLife) {
        new AudioClip(new Media(getClass().getResource("/audio/win.mp3").toString()).getSource()).play();

        ArrayList<Player> retiredPlayers = new ArrayList<>();
        for(Player retiredPlayer : gameOfLife.getAllRetiredPlayers()) retiredPlayers.add(retiredPlayer);

        retiredPlayers.sort((o1, o2) -> o2.getBalance() - o1.getBalance());

        int place = 1;
        for(Player retiredPlayer : retiredPlayers) {
            HBox hBox = new HBox();
            hBox.setSpacing(20);

            Label statsLabel = new Label(String.format("%d. %s (%d) %s", place, retiredPlayer.getName(), retiredPlayer.getBalance(), place == 1 ? "-> Winner!!" : "           "));
            statsLabel.setAlignment(Pos.CENTER);
            statsLabel.setMaxWidth(400);

            Button viewStatsButton = new Button("View Player");
            viewStatsButton.setMaxWidth(250);

            viewStatsButton.setOnAction(e -> {
                new AudioClip(new Media(getClass().getResource("/audio/click.wav").toString()).getSource()).play();

                new Modal().showPlayerStats(retiredPlayer);
            });

            hBox.getChildren().addAll(statsLabel, viewStatsButton);
            list.getChildren().add(hBox);
            place++;
        }

        continueButton.setOnAction(e -> {
            new AudioClip(new Media(getClass().getResource("/audio/click.wav").toString()).getSource()).play();

            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/gui/Menu/Menu.fxml"))));
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
    }
}
