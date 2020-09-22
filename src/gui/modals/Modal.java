package gui.modals;

import model.GameOfLife;
import gui.choose.ChooseCard.ChooseCardController;
import gui.choose.ChooseMove.ChooseMoveController;
import gui.choose.ChoosePath.ChoosePathController;
import gui.choose.ChoosePlayer.ChoosePlayerController;
import gui.game.DisplayCard.DisplayCardController;
import gui.game.GameScreen.GameScreenController;
import gui.stats.RoundStats.RoundStatsController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.cards.Card;
import model.paths.Path;
import model.players.Player;

public class Modal {
    /**
     * Opens a Round Stats GUI
     */
    public void openRoundStats() {
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Round Stats");

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/stats/RoundStats/RoundStats.fxml"));

        try {
            ScrollPane pane = fxmlLoader.load();
            pane.setStyle("-fx-background-color: aliceblue; -fx-font-size: 16; -fx-border-width: 1; -fx-border-color: gray");
            ((RoundStatsController) fxmlLoader.getController()).setList(GameOfLife.getRound(),  GameOfLife.getRoundStats().toArray(new String[0]));

            // clears the round stats
            GameOfLife.getRoundStats().clear();
            stage.setScene(new Scene(pane));
        } catch(Exception e) {
            e.printStackTrace();
        }

        stage.showAndWait();
    }

    /**
     * Allows the Player to make a move
     * @param gameOfLife
     * @param gameScreenController
     */
    public void displayChooseMove(GameOfLife gameOfLife, GameScreenController gameScreenController) {
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.WINDOW_MODAL);

        FXMLLoader chooseMoveLoader = new FXMLLoader(getClass().getResource("/gui/choose/ChooseMove/ChooseMove.fxml"));

        try {
            Scene scene = new Scene(chooseMoveLoader.load());
            stage.setScene(scene);
        } catch(Exception e) {
            e.printStackTrace();
        }

        ChooseMoveController chooseMoveController = chooseMoveLoader.getController();
        chooseMoveController.setGameOfLife(gameOfLife, gameScreenController);

        stage.showAndWait();
    }

    /**
     * Allows a player to choose a Path through a GUI modal
     * @param player player who'll choose the path
     * @param path1 path option #1
     * @param path2 path option #2
     */
    public void choosePath(Player player, Path path1, Path path2) {
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);

        FXMLLoader choosePathLoader = new FXMLLoader(getClass().getResource("/gui/choose/ChoosePath/ChoosePath.fxml"));

        try {
            Scene scene = new Scene(choosePathLoader.load());
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ChoosePathController choosePathController = choosePathLoader.getController();
        choosePathController.setPaths(player, path1, path2);

        stage.showAndWait();
    }

    /**
     *
     * @param card
     */
    public void displayCard(Card card) {
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);

        FXMLLoader displayCardLoader = new FXMLLoader(getClass().getResource("/gui/game/DisplayCard/DisplayCard.fxml"));
        try {
            Scene scene = new Scene(displayCardLoader.load());
            ((DisplayCardController) displayCardLoader.getController()).setCard(card);
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }

        stage.showAndWait();
    }

    /**
     *
     * @param otherPlayers
     * @return chosen Player
     */
    public Player choosePlayer(Player[] otherPlayers) {
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);

        FXMLLoader choosePlayerLoader = new FXMLLoader(getClass().getResource("/gui/choose/ChoosePlayer/ChoosePlayer.fxml"));
        try {
            Scene scene = new Scene(choosePlayerLoader.load());
            ((ChoosePlayerController) choosePlayerLoader.getController()).setPlayer(otherPlayers);
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
     * @return chosen Card
     */
    public Card chooseCard(Card card1, Card card2) {
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);

        FXMLLoader chooseCardLoader = new FXMLLoader(getClass().getResource("/gui/choose/ChooseCard/ChooseCard.fxml"));
        try {
            Scene scene = new Scene(chooseCardLoader.load());
            ((ChooseCardController) chooseCardLoader.getController()).setCards(card1, card2);
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }

        stage.showAndWait();
        return ((ChooseCardController) chooseCardLoader.getController()).getChosenCard();
    }
}
