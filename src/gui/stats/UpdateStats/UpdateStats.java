package gui.stats.UpdateStats;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;

public class UpdateStats {
    public static void showUpdate(String message) {
        Alert update = new Alert(Alert.AlertType.NONE, message, ButtonType.CLOSE);
        update.initModality(Modality.APPLICATION_MODAL);
        update.setTitle("Update");
        update.showAndWait();
    }
}
