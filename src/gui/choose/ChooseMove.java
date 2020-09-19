package gui.choose;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.GameOfLife;
import main.players.Player;

public class ChooseMove {
    public static void chooseMove(GameOfLife gameOfLife) {
        Stage newStage = new Stage();
        newStage.initStyle(StageStyle.UNDECORATED);

        VBox vbox = new VBox();
        vbox.setStyle("-fx-background-color: aliceblue; -fx-font-size: 16; -fx-border-width: 1; -fx-border-color: gray");
        vbox.setMaxWidth(Screen.getPrimary().getBounds().getMaxX() / 4);
        vbox.setMaxHeight(Screen.getPrimary().getBounds().getMaxY() / 2);

        Button rollDice = new Button("Roll Dice");
        rollDice.setOnAction(e -> {
            gameOfLife.movePlayer(gameOfLife.getCurrentPlayer().rollDice());
            newStage.close();
        });

        Button payDebt = new Button("Pay Debt");
        payDebt.setOnAction(e -> {
            payDebt(gameOfLife.getCurrentPlayer());
        });

        vbox.getChildren().addAll(rollDice);
        // only add pay debt button if current player has debt
        if(gameOfLife.getCurrentPlayer().getDebt() > 0) vbox.getChildren().add(payDebt);
        newStage.setScene(new Scene(vbox));
        newStage.showAndWait();
    }

    private static void payDebt(Player player) {
        Stage newStage = new Stage();

        // slider - multiple of 2500
        // button

        newStage.showAndWait();
    }
}
