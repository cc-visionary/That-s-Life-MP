package gui.modals.PayDebt;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.stage.Stage;
import model.Players.Player;

/**
 * Controls the Pay Debt UI (PayDebt.fxml)
 */

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
            AudioClip audioPlayer = new AudioClip(new Media(getClass().getResource("/audio/click.wav").toString()).getSource());
            audioPlayer.play();

            player.payDebt((int) slider.getValue() / 25000);
            ((Stage)((Node) e.getSource()).getScene().getWindow()).close();
        });
    }
}
