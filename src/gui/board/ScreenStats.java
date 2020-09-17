package gui.board;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import main.GameOfLife;

public class ScreenStats extends GridPane {
    public ScreenStats(GameOfLife gameOfLife) {
        // sets the font size for this GridPane to be 24px
        setStyle("-fx-font-size: 24px;");

        // set the GridPane layout settings
        setPadding(new Insets(10, 10, 10, 10));
        setVgap(Screen.getPrimary().getBounds().getMaxY() - 200);
        setHgap(Screen.getPrimary().getBounds().getMaxX() / 10);

        // creates the Grid Elements
        Label playerName = new Label(gameOfLife.getCurrentPlayer().getName() + "'s turn");
        playerName.setFont(Font.font(40));

        Image information = new Image(getClass().getResourceAsStream("../../images/information.png"));
        Button playerInfo = new Button();
        playerInfo.setGraphic(new ImageView(information));
        Label careerCard = new Label("Career: " + gameOfLife.getCurrentPlayer().getCareerCard().getName());
        Label salaryCard = new Label("Salary: $" + gameOfLife.getCurrentPlayer().getSalaryCard().getSalary());
        Label path = new Label("Path: " + gameOfLife.getCurrentPlayer().getPath().getName());
        Label balance = new Label("Balance: $" + gameOfLife.getCurrentPlayer().getBalance());

        // add the Grid Elements
        add(playerName, 0, 0);
        add(playerInfo, 0, 1);
        add(careerCard, 1, 1);
        add(salaryCard, 2, 1);
        add(path, 3, 1);
        add(balance, 4, 1);
    }
}
