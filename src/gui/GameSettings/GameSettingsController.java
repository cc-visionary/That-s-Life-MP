package gui.GameSettings;

import gui.modals.DisplayWinner.DisplayWinnerController;
import gui.Game.GameController;
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
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.stage.Stage;
import model.Constants;
import model.GameOfLife;
import model.GameSettings;
import model.Cards.CareerCard.CareerCard;
import model.Cards.SalaryCard.SalaryCard;

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
        AudioClip audioPlayer = new AudioClip(new Media(getClass().getResource("/audio/click.wav").toString()).getSource());
        audioPlayer.play();

        FXMLLoader gameLoader = new FXMLLoader(getClass().getResource("/gui/Game/Game.fxml"));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        GameController gameController = new GameController(gameSettings.getNPlayers(), gameSettings.getStartingMoney(), stage);
        gameLoader.setController(gameController);

        // set the scene to the Game's Screen
        try {
            StackPane root = gameLoader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setMaximized(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        gameController.startGame();
    }

    /**
     * Decrement the Starting Money
     */
    @FXML
    public void incrementMoney(){
        AudioClip audioPlayer = new AudioClip(new Media(getClass().getResource("/audio/small_click.wav").toString()).getSource());
        audioPlayer.play();

        gameSettings.setMoney(gameSettings.getStartingMoney() + Constants.MONEY_INC);
        moneyLabel.setText(Integer.toString(gameSettings.getStartingMoney()));
        if(gameSettings.getStartingMoney() >= Constants.MAX_MONEY) imButton.setDisable(true);
        if(gameSettings.getStartingMoney() > Constants.MIN_MONEY) dmButton.setDisable(false);
    }

    /**
     * Increment the Starting Money
     */
    @FXML
    public void decrementMoney(){
        AudioClip audioPlayer = new AudioClip(new Media(getClass().getResource("/audio/small_click.wav").toString()).getSource());
        audioPlayer.play();

        gameSettings.setMoney(gameSettings.getStartingMoney() - Constants.MONEY_INC);
        moneyLabel.setText(Integer.toString(gameSettings.getStartingMoney()));
        if(gameSettings.getStartingMoney() <= Constants.MIN_MONEY) dmButton.setDisable(true);
        if(gameSettings.getStartingMoney() < Constants.MAX_MONEY) imButton.setDisable(false);
    }

    /**
     * Increment the amount of Player
     */
    @FXML
    public void incrementPlayer(){
        AudioClip audioPlayer = new AudioClip(new Media(getClass().getResource("/audio/small_click.wav").toString()).getSource());
        audioPlayer.play();

        gameSettings.setNPlayers(gameSettings.getNPlayers() + 1);
        playerLabel.setText(Integer.toString(gameSettings.getNPlayers()));
        if(gameSettings.getNPlayers() >= Constants.MAX_PLAYER) ipButton.setDisable(true);
        if(gameSettings.getNPlayers() > Constants.MIN_PLAYER) dpButton.setDisable(false);
    }

    /**
     * Decrement the amount of Player
     */
    @FXML
    public void decrementPlayer(){
        AudioClip audioPlayer = new AudioClip(new Media(getClass().getResource("/audio/small_click.wav").toString()).getSource());
        audioPlayer.play();

        gameSettings.setNPlayers(gameSettings.getNPlayers() - 1);
        playerLabel.setText(Integer.toString(gameSettings.getNPlayers()));
        if(gameSettings.getNPlayers() <= Constants.MIN_PLAYER) dpButton.setDisable(true);
        if(gameSettings.getNPlayers() < Constants.MAX_PLAYER) ipButton.setDisable(false);
    }

    /**
     * Set scene back to menu
     * @param event event parameter
     */
    @FXML
    public void backToMenu(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        AudioClip audioPlayer = new AudioClip(new Media(getClass().getResource("/audio/click.wav").toString()).getSource());
        audioPlayer.play();

        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/gui/Menu/Menu.fxml"))));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
