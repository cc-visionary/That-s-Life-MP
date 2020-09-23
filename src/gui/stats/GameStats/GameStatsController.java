package gui.stats.GameStats;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import model.GameOfLife;
import model.Players.Player;

public class GameStatsController {
    @FXML
    private TabPane tabPane;

    @FXML
    private Button continueButton;

    public void setData(GameOfLife gameOfLife) {
        for(Player player : gameOfLife.getAllPlayers()) {
            Tab tab = new Tab();
            tab.setText(player.getName());
            tabPane.getTabs().add(tab);
        }

        continueButton.setOnAction(e -> {
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/gui/Menu/Menu.fxml"))));
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
    }
}
