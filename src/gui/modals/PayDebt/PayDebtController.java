package gui.modals.PayDebt;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.stage.Stage;
import model.Players.Player;

public class PayDebtController {
    @FXML
    private Label value;

    @FXML
    private Slider slider;

    @FXML
    private Button continueButton;

    public void setPlayer(Player player) {
        value.setText("$" + slider.getValue());

        slider.setMax(player.getDebt());
        slider.setMinorTickCount(player.getNBankLoan() - 2);

        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                value.setText("$" + newValue);
            }
        });

        continueButton.setOnAction(e -> {
            player.payDebt((int) slider.getValue() / 2500);
            ((Stage)((Node) e.getSource()).getScene().getWindow()).close();
        });
    }
}
