package gui.gameSettings;

import com.sun.org.apache.xml.internal.security.Init;
import gui.game.DisplayWinner.DisplayWinnerController;
import gui.game.GameScreen.GameScreenController;
import gui.modals.Modal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.Constants;
import model.GameOfLife;
import model.GameSettings;
import model.cards.CareerCard.CareerCard;
import model.cards.SalaryCard.SalaryCard;

import java.net.URL;
import java.util.ResourceBundle;

public class GameSettingsController implements Initializable {
    private GameSettings gameSettings;

    @FXML
    private Button imButton, dmButton, ipButton, dpButton;
    @FXML
    private Label moneyLabel, playerLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gameSettings = new GameSettings();
        Image prevIcon = new Image("/images/icons/previous.png");
        Image nextIcon = new Image("/images/icons/next.png");
        imButton.setGraphic(new ImageView(nextIcon));
        dmButton.setGraphic(new ImageView(prevIcon));
        ipButton.setGraphic(new ImageView(nextIcon));
        dpButton.setGraphic(new ImageView(prevIcon));
        dpButton.setDisable(true);
    }

    /**
     * Start running the game
     * Contains the Game Loop
     * @param event event parameter
     */
    @FXML
    public void runGame(ActionEvent event) {
        GameOfLife gameOfLife = new GameOfLife();
        gameOfLife.startGame(gameSettings.getPlayer(), gameSettings.getMoney());

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

    /**
     * Decrement the Starting Money
     */
    @FXML
    public void incrementMoney(){
        gameSettings.setMoney(gameSettings.getMoney() + 50000);
        moneyLabel.setText(Integer.toString(gameSettings.getMoney()));
        if(gameSettings.getMoney() >= Constants.MAX_MONEY) imButton.setDisable(true);
        if(gameSettings.getMoney() > Constants.MIN_MONEY) dmButton.setDisable(false);
    }

    /**
     * Increment the Starting Money
     */
    @FXML
    public void decrementMoney(){
        gameSettings.setMoney(gameSettings.getMoney() - 50000);
        moneyLabel.setText(Integer.toString(gameSettings.getMoney()));
        if(gameSettings.getMoney() <= Constants.MIN_MONEY) dmButton.setDisable(true);
        if(gameSettings.getMoney() < Constants.MAX_MONEY) imButton.setDisable(false);
    }

    /**
     * Increment the amount of Player
     */
    @FXML
    public void incrementPlayer(){
        gameSettings.setPlayer(gameSettings.getPlayer() + 1);
        playerLabel.setText(Integer.toString(gameSettings.getPlayer()));
        if(gameSettings.getPlayer() >= Constants.MAX_PLAYER) ipButton.setDisable(true);
        if(gameSettings.getPlayer() > Constants.MIN_PLAYER) dpButton.setDisable(false);
    }

    /**
     * Decrement the amount of Player
     */
    @FXML
    public void decrementPlayer(){
        gameSettings.setPlayer(gameSettings.getPlayer() - 1);
        playerLabel.setText(Integer.toString(gameSettings.getPlayer()));
        if(gameSettings.getPlayer() <= Constants.MIN_PLAYER) dpButton.setDisable(true);
        if(gameSettings.getPlayer() < Constants.MAX_PLAYER) ipButton.setDisable(false);
    }

    /**
     * Set scene back to menu
     * @param event event parameter
     */
    @FXML
    public void backToMenu(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/gui/menu/Menu.fxml"))));
            stage.setMaximized(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
