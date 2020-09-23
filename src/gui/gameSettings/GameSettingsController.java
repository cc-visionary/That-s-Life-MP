package gui.gameSettings;

import gui.game.DisplayWinner.DisplayWinnerController;
import gui.game.GameScreen.GameScreenController;
import gui.modals.Modal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.GameOfLife;
import model.GameSettings;
import model.cards.CareerCard.CareerCard;
import model.cards.SalaryCard.SalaryCard;



public class GameSettingsController {
    private GameSettings gameSettings;
    @FXML
    Button imButton, dmButton, ipButton, dpButton;
    @FXML
    Label moneyLabel, playerLabel;
    @FXML
    public void runGame(ActionEvent event) {
        GameOfLife gameOfLife = new GameOfLife();
        gameOfLife.startGame();

        FXMLLoader gameScreenLoader = new FXMLLoader(getClass().getResource("/gui/game/GameScreen/GameScreen.fxml"));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // set the scene to the Game's Screen
        try {
            StackPane root = gameScreenLoader.load();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.setMaximized(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        GameScreenController gameScreenController = gameScreenLoader.getController();

        while (!gameOfLife.hasWinner()) {
            gameScreenController.refreshGameScreen(gameOfLife.getCollegePath(), gameOfLife.getCareerPath(), gameOfLife.getCurrentPlayer());

            // if player has no path, let him/her choose from the beginning paths
            if(gameOfLife.getCurrentPlayer().getPath() == null) {
                new Modal().choosePath(gameOfLife.getCurrentPlayer(), gameOfLife.getCareerPath(), gameOfLife.getCollegePath());
                // if the player started in Career Path, give 1 salary card and career card
                if(gameOfLife.getCurrentPlayer().getPath().getName().equals("Career Path")) {
                    // only gets the Career which can be given to those players without College Degree
                    CareerCard careerCard = (CareerCard) gameOfLife.getCareerDeck().pickTopCard();
                    while(careerCard.isRequireCollegeDegree()) {
                        gameOfLife.getCareerDeck().addCard(careerCard);
                        careerCard = (CareerCard) gameOfLife.getCareerDeck().pickTopCard();
                    }
                    gameOfLife.getCurrentPlayer().setCareerCard(careerCard);
                    gameOfLife.getCurrentPlayer().setSalaryCard((SalaryCard) gameOfLife.getSalaryDeck().pickTopCard());
                }
                gameOfLife.getCurrentPlayer().getPath().getSpaces()[0].addPlayer(gameOfLife.getCurrentPlayer());
                gameScreenController.refreshGameScreen(gameOfLife.getCollegePath(), gameOfLife.getCareerPath(), gameOfLife.getCurrentPlayer());
            }

            // lets the player choose a move
            new Modal().displayChooseMove(gameOfLife, gameScreenController);

            gameOfLife.setTurn(gameOfLife.getTurn() + 1);
            if(gameOfLife.getTurn() == gameOfLife.getNPlayers()) {
                new Modal().openRoundStats();
                gameOfLife.setRound(gameOfLife.getRound() + 1);
                gameOfLife.setTurn(0);
            }
        }
        gameOfLife.endGame();

        // detect who the winner was
        try {
            FXMLLoader diplayWinnerLoader = new FXMLLoader(getClass().getResource("/gui/game/DisplayWinner/DisplayWinner.fxml"));
            stage.setScene(new Scene(diplayWinnerLoader.load()));
            ((DisplayWinnerController) diplayWinnerLoader.getController()).setWinner(gameOfLife);
        } catch(Exception exception) {
            exception.printStackTrace();
        }
    }
    @FXML
    public void incrementMoney(){
        gameSettings.setMoney(gameSettings.getMoney()+50000);
        if(gameSettings.getMoney() == 1000000)
            imButton.setDisable(true);
        else dmButton.setDisable(false);
        moneyLabel.setText(""+gameSettings.getMoney());
    }
    @FXML
    public void decrementMoney(){
        gameSettings.setMoney(gameSettings.getMoney()-50000);
        if(gameSettings.getMoney() == 150000)
            dmButton.setDisable(true);
        else imButton.setDisable(false);
        moneyLabel.setText(""+gameSettings.getMoney());
    }
    @FXML
    public void incrementPlayer(){
        gameSettings.setPlayer(gameSettings.getPlayer()+1);
        if(gameSettings.getPlayer() == 3)
            ipButton.setDisable(true);
        else dpButton.setDisable(false);
        playerLabel.setText("" + gameSettings.getPlayer());
    }
    @FXML
    public void decrementPlayer(){
        gameSettings.setPlayer(gameSettings.getPlayer()+1);
        if(gameSettings.getPlayer() == 2)
            dpButton.setDisable(true);
        else ipButton.setDisable(false);
        playerLabel.setText("" + gameSettings.getPlayer());
    }
    @FXML
    public void backToMenu(){

    }
}
