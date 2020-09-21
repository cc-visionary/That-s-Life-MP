package gui.choose.ChooseMove;

import gui.stats.PlayerStats.PlayerStatsController;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import gui.GameOfLife;
import model.players.Player;

import java.io.IOException;

public class ChooseMove {
    public void chooseMove(GameOfLife gameOfLife) {
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);

        VBox vbox = new VBox();
        vbox.setStyle("-fx-background-color: aliceblue; -fx-font-size: 16; -fx-border-width: 1; -fx-border-color: gray");
        vbox.setMaxWidth(Screen.getPrimary().getBounds().getMaxX() / 4);
        vbox.setMaxHeight(Screen.getPrimary().getBounds().getMaxY() / 2);

        Label chooseMove = new Label("Choose your move:");
        chooseMove.setAlignment(Pos.CENTER);

        // Roll Dice
        Button rollDice = new Button("Roll Dice");
        rollDice.setOnAction(e -> {
            gameOfLife.movePlayer(gameOfLife.getCurrentPlayer().rollDice());
            stage.close();
        });
        rollDice.setMaxWidth(Double.MAX_VALUE);

        // View Player Stats
        Button playerStats = new Button("View Player Stats");
        playerStats.setOnAction(e -> {
            try {
                displayStats(gameOfLife.getCurrentPlayer());
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        playerStats.setMaxWidth(Double.MAX_VALUE);

        // Pay Debt
        Button payDebt = new Button("Pay Debt");
        payDebt.setOnAction(e -> {
            payDebt(gameOfLife.getCurrentPlayer());
            gameOfLife.getScreenStats().updateStats(gameOfLife.getCurrentPlayer());
        });
        payDebt.setMaxWidth(Double.MAX_VALUE);

        vbox.setPadding(new Insets(10));
        vbox.getChildren().addAll(chooseMove, rollDice, playerStats);
        // only add pay debt button if current player has debt
        if(gameOfLife.getCurrentPlayer().getDebt() > 0) vbox.getChildren().add(payDebt);
        stage.setScene(new Scene(vbox));
        stage.showAndWait();
    }

    private void displayStats(Player player) throws IOException {
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/stats/PlayerStats/PlayerStats.fxml"));

        Pane pane = fxmlLoader.load();
        pane.setStyle("-fx-background-color: aliceblue; -fx-font-size: 16; -fx-border-width: 1; -fx-border-color: gray");
        ((PlayerStatsController) fxmlLoader.getController()).setStats(player);

        stage.setScene(new Scene(pane));
        stage.show();
    }

    private void payDebt(Player player) {
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);

        VBox vBox = new VBox();
        vBox.setStyle("-fx-background-color: aliceblue; -fx-font-size: 16; -fx-border-width: 1; -fx-border-color: gray");

        Label amount = new Label();

        Slider slider = new Slider();
        slider.setMin(25000);
        slider.setMax(player.getDebt());
        slider.setMajorTickUnit(25000);
        slider.setMinorTickCount(player.getNBankLoan() - 2);
        slider.setBlockIncrement(25000);
        slider.setSnapToTicks(true);
        slider.setShowTickMarks(true);
        slider.valueProperty().addListener(e -> {
            amount.setText("Amount: $" + String.format("%.2f", slider.getValue()));
        });

        amount.setText("Amount: $" + String.format("%.2f", slider.getValue()));

        Button confirm = new Button("Confirm");
        confirm.setOnAction(e -> {
            if(slider.getValue() % 25000 == 0) player.payDebt((int) slider.getValue() / 25000);

            stage.close();
        });

        vBox.getChildren().addAll(amount, slider, confirm);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(10));

        stage.setScene(new Scene(vBox));
        stage.showAndWait();
    }
}
