package gui.stats.RoundStats;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class RoundStatsController {
    @FXML
    private VBox list;

    @FXML
    private Label roundLabel;

    @FXML
    public void setList(int round, String[] roundStats) {
        roundLabel.setText("Round " + round + ":");

        for(String roundStat : roundStats) {
            Label label = new Label(String.format("- %s", roundStat));
            label.setMaxWidth(Double.MAX_VALUE);
            list.getChildren().add(label);
        }
    }
}
