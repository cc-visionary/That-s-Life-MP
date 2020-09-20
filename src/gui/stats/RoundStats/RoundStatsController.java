package gui.stats.RoundStats;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class RoundStatsController {
    @FXML
    VBox list;

    @FXML
    public void setList(String[] roundStats) {
        for(String roundStat : roundStats) {
            list.getChildren().add(new Label(String.format("- %s", roundStat)));
        }
    }
}
