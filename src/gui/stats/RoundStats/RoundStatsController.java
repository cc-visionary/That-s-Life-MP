package gui.stats.RoundStats;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controls the Round Stats UI (RoundStats.fxml)
 */

public class RoundStatsController implements Initializable {
    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox list;

    @FXML
    private Label roundLabel;

    private int round;
    private String[] roundStats;

    public RoundStatsController(int round, String[] roundStats) {
        this.round = round;
        this.roundStats = roundStats;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
