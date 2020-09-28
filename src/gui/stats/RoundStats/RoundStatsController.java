package gui.stats.RoundStats;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

/**
 * Controls the Round Stats UI (RoundStats.fxml)
 */

public class RoundStatsController {
    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox list;

    @FXML
    private Label roundLabel;

    @FXML
    public void setList(int round, String[] roundStats) {
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.getStylesheets().add("/styles/modal.css");
        roundLabel.setText("Round " + round + ":");

        for(String roundStat : roundStats) {
            Label label = new Label(String.format("- %s", roundStat));
            label.setMaxWidth(Double.MAX_VALUE);
            list.getChildren().add(label);
        }
    }
}
