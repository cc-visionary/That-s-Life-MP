package gui.choose;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.GameOfLife;
import main.players.Player;

public class ChooseMove {
    public static void chooseMove(GameOfLife gameOfLife) {
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);

        VBox vbox = new VBox();
        vbox.setStyle("-fx-background-color: aliceblue; -fx-font-size: 16; -fx-border-width: 1; -fx-border-color: gray");
        vbox.setMaxWidth(Screen.getPrimary().getBounds().getMaxX() / 4);
        vbox.setMaxHeight(Screen.getPrimary().getBounds().getMaxY() / 2);

        Button rollDice = new Button("Roll Dice");
        rollDice.setOnAction(e -> {
            gameOfLife.movePlayer(gameOfLife.getCurrentPlayer().rollDice());
            stage.close();
        });

        Button payDebt = new Button("Pay Debt");
        payDebt.setOnAction(e -> {
            payDebt(gameOfLife.getCurrentPlayer());
        });

        vbox.getChildren().addAll(rollDice);
        // only add pay debt button if current player has debt
        if(gameOfLife.getCurrentPlayer().getDebt() > 0) vbox.getChildren().add(payDebt);
        stage.setScene(new Scene(vbox));
        stage.showAndWait();
    }

    private static void payDebt(Player player) {
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);

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
