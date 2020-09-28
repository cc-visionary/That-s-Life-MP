package gui.modals.PayDebt;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.stage.Stage;
import model.Players.Player;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controls the Pay Debt UI (PayDebt.fxml)
 */

public class PayDebtController implements Initializable {
    @FXML
    private Label value;

    @FXML
    private Slider slider;

    @FXML
    private Button continueButton;

    private Player player;

    public PayDebtController(Player player) {
        this.player = player;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
