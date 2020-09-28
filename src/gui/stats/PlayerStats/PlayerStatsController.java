package gui.stats.PlayerStats;

import gui.modals.Modal;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import model.Players.Player;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controls the Player Stats UI (PlayerStats.fxml)
 */

public class PlayerStatsController implements Initializable {
    @FXML
    private Label name, balance, debt, path, career, salary, house, graduated, married, children;

    @FXML
    private Button careerButton, salaryButton, houseButton;

    private Player player;

    public PlayerStatsController(Player player) {
        this.player = player;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        name.setText(player.getName());
        balance.setText("$" + player.getBalance());
        debt.setText(player.getDebt() > 0 ? "$" + player.getDebt() : "None");
        path.setText(player.getPath() != null ? player.getPath().getName() : "None");
        career.setText(player.getCareerCard() != null ? player.getCareerCard().getName() : "None");
        salary.setText(player.getSalaryCard() != null ? "$" + player.getSalaryCard().getSalary() : "None");
        house.setText(player.getHouseCard() != null ? player.getHouseCard().getName() : "None");
        graduated.setText(player.isGraduated() ? "Yes" : "No");
        married.setText(player.isMarried() ? "Yes" : "No");
        children.setText(player.getNBabies() + "");

        if(player.getCareerCard() != null) {
            careerButton.setOnAction(e -> {
                AudioClip audioPlayer = new AudioClip(new Media(getClass().getResource("/audio/click.wav").toString()).getSource());
                audioPlayer.play();

                new Modal().displayCard(player.getCareerCard());
            });
        } else careerButton.setDisable(true);

        if(player.getSalaryCard() != null) {
            salaryButton.setOnAction(e -> {
                AudioClip audioPlayer = new AudioClip(new Media(getClass().getResource("/audio/click.wav").toString()).getSource());
                audioPlayer.play();

                new Modal().displayCard(player.getSalaryCard());
            });
        } else salaryButton.setDisable(true);

        if(player.getHouseCard() != null) {
            houseButton.setOnAction(e -> {
                AudioClip audioPlayer = new AudioClip(new Media(getClass().getResource("/audio/click.wav").toString()).getSource());
                audioPlayer.play();

                new Modal().displayCard(player.getHouseCard());
            });
        } else houseButton.setDisable(true);
    }
}
