package gui.modals;

import gui.modals.ChooseHouse.ChooseHouseController;
import gui.modals.PayDebt.PayDebtController;
import gui.stats.PlayerStats.PlayerStatsController;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.stage.Screen;
import model.GameOfLife;
import gui.modals.ChooseCard.ChooseCardController;
import gui.modals.ChooseMove.ChooseMoveController;
import gui.modals.ChoosePath.ChoosePathController;
import gui.modals.ChoosePlayer.ChoosePlayerController;
import gui.modals.DisplayCard.DisplayCardController;
import gui.Game.GameController;
import gui.stats.RoundStats.RoundStatsController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Cards.Card;
import model.Cards.HouseCard.HouseCard;
import model.Decks.Deck;
import model.Paths.Path;
import model.Players.Player;

import java.util.Optional;

/**
 * Contains methods to create stage for dialogs to update and get inputs from user.
 */

public class Modal {
    /**
     * Opens a Round Stats GUI and shows the Round Statistics
     */
    public void openRoundStats() {
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Round Stats");

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/stats/RoundStats/RoundStats.fxml"));
        RoundStatsController roundStatsController = new RoundStatsController(GameOfLife.getRound(),  GameOfLife.getRoundStats().toArray(new String[0]));
        fxmlLoader.setController(roundStatsController);

        try {
            ScrollPane pane = fxmlLoader.load();

            // clears the round stats
            GameOfLife.getRoundStats().clear();
            stage.setScene(new Scene(pane));

            pane.addEventHandler(KeyEvent.KEY_PRESSED, ev -> {
                if(ev.getCode() == KeyCode.ESCAPE || ev.getCode() == KeyCode.ENTER) {
                    stage.close();
                }
            });
        } catch(Exception e) {
            e.printStackTrace();
        }

        stage.setOnCloseRequest(e -> {
            AudioClip audioPlayer = new AudioClip(new Media(getClass().getResource("/audio/click.wav").toString()).getSource());
            audioPlayer.play();
        });

        stage.showAndWait();
    }

    public void showPlayerStats(Player player) {
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);

        FXMLLoader playerStatsLoader = new FXMLLoader(getClass().getResource("/gui/stats/PlayerStats/PlayerStats.fxml"));
        PlayerStatsController playerStatsController = new PlayerStatsController(player);
        playerStatsLoader.setController(playerStatsController);

        try {
            Parent root = playerStatsLoader.load();

            stage.setScene(new Scene(root));

            root.addEventHandler(KeyEvent.KEY_PRESSED, ev -> {
                if(ev.getCode() == KeyCode.ESCAPE || ev.getCode() == KeyCode.ENTER) {
                    stage.close();
                }
            });
        } catch(Exception exception) {
            exception.printStackTrace();
        }

        stage.setOnCloseRequest(e -> {
            AudioClip audioPlayer = new AudioClip(new Media(getClass().getResource("/audio/click.wav").toString()).getSource());
            audioPlayer.play();
        });

        stage.show();
    }

    public void payDebt(Player player) {
        Stage payDebtStage = new Stage();
        payDebtStage.initStyle(StageStyle.UNDECORATED);
        payDebtStage.initModality(Modality.APPLICATION_MODAL);

        FXMLLoader payDebtLoader = new FXMLLoader(getClass().getResource("/gui/modals/PayDebt/PayDebt.fxml"));
        PayDebtController payDebtController = new PayDebtController(player);
        payDebtLoader.setController(payDebtController);

        try {
            payDebtStage.setScene(new Scene(payDebtLoader.load()));
        } catch(Exception exception) {
            exception.printStackTrace();
        }

        payDebtStage.showAndWait();
    }

    /**
     * Allows the Player to make a move
     * @param gameOfLife           game of life model
     * @param gameScreenController screen controller to update the screen stats
     */
    public void displayChooseMove(GameOfLife gameOfLife, GameController gameScreenController) {
        Stage stage = new Stage();
        stage.setX(Screen.getPrimary().getBounds().getWidth() - Screen.getPrimary().getBounds().getWidth() / 4);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.WINDOW_MODAL);

        FXMLLoader chooseMoveLoader = new FXMLLoader(getClass().getResource("/gui/modals/ChooseMove/ChooseMove.fxml"));
        ChooseMoveController chooseMoveController = new ChooseMoveController(gameOfLife, gameScreenController);
        chooseMoveLoader.setController(chooseMoveController);

        try {
            Scene scene = new Scene(chooseMoveLoader.load());
            stage.setScene(scene);
        } catch(Exception e) {
            e.printStackTrace();
        }

        stage.showAndWait();
    }

    /**
     * Allows a player to choose a Path through a GUI modal
     * @param player player who'll choose the path
     * @param path1  path option #1
     * @param path2  path option #2
     */
    public void choosePath(Player player, Path path1, Path path2) {
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);

        FXMLLoader choosePathLoader = new FXMLLoader(getClass().getResource("/gui/modals/ChoosePath/ChoosePath.fxml"));
        ChoosePathController choosePathController = new ChoosePathController(player, path1, path2);
        choosePathLoader.setController(choosePathController);

        try {
            stage.setScene(new Scene(choosePathLoader.load()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        stage.showAndWait();
    }

    /**
     * Allows the Player to Display a Card
     * @param card card to be displayed
     */
    public void displayCard(Card card) {
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);

        FXMLLoader displayCardLoader = new FXMLLoader(getClass().getResource("/gui/modals/DisplayCard/DisplayCard.fxml"));
        DisplayCardController displayCardController = new DisplayCardController(card);
        displayCardLoader.setController(displayCardController);

        try {
            Scene scene = new Scene(displayCardLoader.load());
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }

        stage.showAndWait();
    }

    /**
     * Allows the Player to choose a player from other Players
     * @param otherPlayers other players to choose from
     * @return             chosen Player
     */
    public Player choosePlayer(Player[] otherPlayers) {
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);

        FXMLLoader choosePlayerLoader = new FXMLLoader(getClass().getResource("/gui/modals/ChoosePlayer/ChoosePlayer.fxml"));
        ChoosePlayerController choosePlayerController = new ChoosePlayerController(otherPlayers);
        choosePlayerLoader.setController(choosePlayerController);

        try {
            Scene scene = new Scene(choosePlayerLoader.load());
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }

        stage.showAndWait();
        return ((ChoosePlayerController) choosePlayerLoader.getController()).getChosenPlayer();
    }

    /**
     * Allows the Player to choose between 2 cards
     * @param card1 card option #1
     * @param card2 card option #2
     * @return      chosen Card
     */
    public Card chooseCard(Card card1, Card card2) {
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);

        FXMLLoader chooseCardLoader = new FXMLLoader(getClass().getResource("/gui/modals/ChooseCard/ChooseCard.fxml"));
        ChooseCardController chooseCardController = new ChooseCardController(card1, card2);
        chooseCardLoader.setController(chooseCardController);

        try {
            Scene scene = new Scene(chooseCardLoader.load());
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }

        stage.showAndWait();
        return chooseCardController.getChosenCard();
    }

    /**
     * Allows the Player to choose a house from the house deck
     * @param houseDeck house deck to choose a house from
     * @return          chosen house
     */
    public HouseCard chooseHouse(Deck houseDeck) {
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);

        FXMLLoader houseCardLoader = new FXMLLoader(getClass().getResource("/gui/modals/ChooseHouse/ChooseHouse.fxml"));
        ChooseHouseController chooseHouseController = new ChooseHouseController(houseDeck);
        houseCardLoader.setController(chooseHouseController);

        try {
            Scene scene = new Scene(houseCardLoader.load());
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }

        stage.showAndWait();
        return ((ChooseHouseController) houseCardLoader.getController()).getChosenHouse();
    }

    public void showUpdate(String message) {
        Alert update = new Alert(Alert.AlertType.NONE, message, ButtonType.OK);
        update.getDialogPane().getStylesheets().add("/styles/modal.css");
        update.initStyle(StageStyle.UNDECORATED);
        update.initModality(Modality.APPLICATION_MODAL);

        update.setOnCloseRequest(e -> {
            AudioClip audioPlayer = new AudioClip(new Media(getClass().getResource("/audio/click.wav").toString()).getSource());
            audioPlayer.play();
        });

        update.showAndWait();
    }

    public String askPlayerName() {
        TextInputDialog inputName = new TextInputDialog();
        inputName.getDialogPane().getStylesheets().add("/styles/modal.css");
        inputName.initStyle(StageStyle.UNDECORATED);
        inputName.initModality(Modality.APPLICATION_MODAL);
        inputName.setHeaderText(null);
        inputName.setGraphic(null);
        inputName.setContentText("Enter your name:");

        inputName.getDialogPane().lookupButton(ButtonType.CANCEL).setDisable(true);

        inputName.setOnCloseRequest(e -> {
            AudioClip audioPlayer = new AudioClip(new Media(getClass().getResource("/audio/click.wav").toString()).getSource());
            audioPlayer.play();
        });

        Optional<String> result = inputName.showAndWait();
        return result.get();
    }

    public boolean askYesNo(String title, String question) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, question, ButtonType.YES, ButtonType.NO);
        alert.getDialogPane().getStylesheets().add("/styles/modal.css");
        alert.initStyle(StageStyle.UNDECORATED);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setHeaderText(null);
        alert.setGraphic(null);

        Optional<ButtonType> result = alert.showAndWait();

        return result.get() == ButtonType.YES;
    }
}
