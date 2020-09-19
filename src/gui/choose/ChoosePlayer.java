package gui.choose;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.players.Player;

public class ChoosePlayer {
    private Player chosenPlayer;

    public void chooseOtherPlayers(Player[] otherPlayers) {
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);

        VBox vBox = new VBox();

        Button select = new Button("Select");
        select.setOnAction(e -> {
            stage.close();
        });

        stage.setScene(new Scene(vBox));
        stage.showAndWait();
    }

    public Player getChosenPlayer() {
        return chosenPlayer;
    }
}
