package gui.stats.UpdateStats;

import javafx.scene.control.Alert;
import javafx.stage.Modality;

public class UpdateStats {
    public static void showUpdate(String message) {
        Alert update = new Alert(Alert.AlertType.INFORMATION);
        update.initModality(Modality.APPLICATION_MODAL);
        update.setContentText(message);
        update.setTitle("Update");
        update.showAndWait();
    }
}
