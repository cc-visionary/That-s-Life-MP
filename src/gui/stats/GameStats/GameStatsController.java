package gui.stats.GameStats;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import model.GameOfLife;
import model.players.Player;

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
//            stage.setScene(new FXMLLoader(getClass().getResource("/gui/menu")));
            stage.setMaximized(true);
        });
    }
}
