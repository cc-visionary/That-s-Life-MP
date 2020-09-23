package gui.game.DisplayWinner;

import gui.stats.GameStats.GameStatsController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.GameOfLife;
import model.players.Player;

public class DisplayWinnerController {
    @FXML
    private Label winner;

    @FXML
    private Button continueButton;

    public void setWinner(GameOfLife gameOfLife) {
        Player playerWinner = null;
        for(Player player : gameOfLife.getAllPlayers()) {
            if(player.isRetired()) {
                playerWinner = player;
                break;
            }
        }
        winner.setText(playerWinner.getName() + " won!");
        continueButton.setOnAction(e -> {
            // display game stats
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            try {
                FXMLLoader gameStatsLoader = new FXMLLoader(getClass().getResource("/gui/stats/GameStats/GameStats.fxml"));
                stage.setScene(new Scene(gameStatsLoader.load()));
                stage.setMaximized(true);
                ((GameStatsController) gameStatsLoader.getController()).setData(gameOfLife);
            } catch(Exception exception) {
                exception.printStackTrace();
            }
        });
    }
}
