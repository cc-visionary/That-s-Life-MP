package gui.choose.ChooseMove;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ChooseMoveController {
    @FXML
    private Button rollDice, viewPlayerStats, payDebt;

    public ChooseMoveController() {
        rollDice.setOnAction(e -> {

        });

        viewPlayerStats.setOnAction(e -> {

        });

        payDebt.setOnAction(e -> {

        });

        if(true) payDebt.setDisable(true);
    }
}
