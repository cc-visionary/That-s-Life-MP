package gui.ScreenStats;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.Players.Player;

public class ScreenStatsController {
    @FXML
    private Label turn, career, salary, path, balance;

    @FXML
    public void updateStats(Player currentPlayer) {
        turn.setText(currentPlayer.getName() + "'s turn");
        if(currentPlayer.getCareerCard() != null) career.setText("Career: " + currentPlayer.getCareerCard().getName());
        else career.setText("Career: None");
        if(currentPlayer.getSalaryCard() != null) salary.setText(String.format("Salary: $%d", currentPlayer.getSalaryCard().getSalary()));
        else salary.setText("Salary: None");
        if(currentPlayer.getPath() != null) path.setText("Path: " + currentPlayer.getPath().getName());
        else path.setText("Path: None");
        balance.setText("Balance: $" + currentPlayer.getBalance());
    }
}
