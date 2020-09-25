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
            VBox vbox = new VBox();

            Label name = new Label(String.format("Name: %s", player.getName()));
            Label money = new Label(String.format("Money: %.2f", player.getBalance()));
            Label debt = new Label(String.format("Debt: %.2f", player.getDebt()));
            Label noChildren = new Label(String.format("Number of Children: %d", player.getNBabies()));
            Label hasGraduated = new Label(String.format("Graduated: %s", player.isGraduated() ? "Yes" : "No"));
            Label isMarried = new Label(String.format("Married: %s", player.isMarried() ? "Yes" : "No"));
            Label isRetired = new Label(String.format("Retired: %s", player.isRetired() ? "Yes" : "No"));

            vbox.getChildren().addAll(name, money, debt, noChildren, hasGraduated, isMarried, isRetired);
            tab.setContent(vbox);
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
