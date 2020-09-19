package gui.choose;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.players.Player;

import java.util.ArrayList;

public class ChoosePlayer {
    public static Player chooseOtherPlayers(Player[] otherPlayers) {
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);

        VBox vBox = new VBox();
        vBox.setStyle("-fx-background-color: aliceblue; -fx-font-size: 16; -fx-border-width: 1; -fx-border-color: gray");
        vBox.setPadding(new Insets(10));

        ComboBox playersCombo = new ComboBox();
        ArrayList<String> choices = new ArrayList<String>();
        for(Player player : otherPlayers) choices.add(player.getName() + " ($" + player.getBalance() + ")");

        playersCombo.getItems().addAll(choices);
        playersCombo.setValue(choices.get(0));

        Button select = new Button("Select");
        select.setOnAction(e -> {
            stage.close();
        });
        vBox.getChildren().addAll(playersCombo, select);
        vBox.setAlignment(Pos.CENTER);

        stage.setScene(new Scene(vBox));
        stage.showAndWait();
        return otherPlayers[playersCombo.getSelectionModel().getSelectedIndex()];
    }
}
