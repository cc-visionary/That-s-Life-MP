package gui.stats.PlayerStats;

import gui.choose.ChooseCard;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.players.Player;

import java.io.IOException;

public class PlayerStatsController {
    @FXML
    private Label name, balance, debt, path, career, salary, house, graduated, married, children;

    @FXML
    private Button careerButton, salaryButton, houseButton;

    @FXML
    public void setStats(Player player) {
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
                ChooseCard.displayCard(player.getCareerCard());
            });
        } else careerButton.setDisable(true);

        if(player.getSalaryCard() != null) {
            salaryButton.setOnAction(e -> {
                ChooseCard.displayCard(player.getSalaryCard());
            });
        } else salaryButton.setDisable(true);

        if(player.getHouseCard() != null) {
            houseButton.setOnAction(e -> {
                ChooseCard.displayCard(player.getHouseCard());
            });
        } else houseButton.setDisable(true);
    }
}
