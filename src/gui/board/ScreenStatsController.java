package gui.board;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import main.players.Player;

public class ScreenStatsController {
    @FXML
    private Label turn, career, salary, path, balance;

    @FXML
    public void updateStats(Player currentPlayer) {
        turn.setText(currentPlayer.getName() + "'s turn");
        career.setText("Career: " + currentPlayer.getCareerCard().getName());
        salary.setText("Salary: $" + currentPlayer.getSalaryCard().getSalary());
        path.setText("Path: " + currentPlayer.getPath().getName());
        balance.setText("Balance: $" + currentPlayer.getBalance());
    }
}
