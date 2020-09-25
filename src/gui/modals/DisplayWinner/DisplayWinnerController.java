package gui.modals.DisplayWinner;

import gui.stats.GameStats.GameStatsController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import model.GameOfLife;
import model.Players.Player;

public class DisplayWinnerController {
    @FXML
    private Label winner;

    @FXML
    private Button continueButton;

    public void setWinner(GameOfLife gameOfLife) {
        MediaPlayer mediaPlayer = new MediaPlayer(new Media(getClass().getResource("/audio/win.wav").toString()));
        mediaPlayer.play();
        Player playerWinner = null;
        for(Player player : gameOfLife.getAllActivePlayers()) {
            if(player.isRetired()) {
                playerWinner = player;
                break;
            }
        }
        winner.setText(playerWinner.getName() + " won!");
        continueButton.setOnAction(e -> {
            AudioClip audioPlayer = new AudioClip(new Media(getClass().getResource("/audio/click.wav").toString()).getSource());
            audioPlayer.play();

            // display game stats
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            try {
                FXMLLoader gameStatsLoader = new FXMLLoader(getClass().getResource("/gui/stats/GameStats/GameStats.fxml"));
                stage.setScene(new Scene(gameStatsLoader.load()));
                ((GameStatsController) gameStatsLoader.getController()).setData(gameOfLife);
            } catch(Exception exception) {
                exception.printStackTrace();
            }
        });
    }
}
