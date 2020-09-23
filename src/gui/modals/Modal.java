package gui.modals;

import gui.modals.ChooseHouse.ChooseHouseController;
import javafx.stage.Screen;
import model.GameOfLife;
import gui.modals.ChooseCard.ChooseCardController;
import gui.modals.ChooseMove.ChooseMoveController;
import gui.modals.ChoosePath.ChoosePathController;
import gui.modals.ChoosePlayer.ChoosePlayerController;
import gui.modals.DisplayCard.DisplayCardController;
import gui.GameScreen.GameScreenController;
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
     * @param gameOfLife           game of life model
     * @param gameScreenController screen controller to update the screen stats
     */
    public void displayChooseMove(GameOfLife gameOfLife, GameScreenController gameScreenController) {
        Stage stage = new Stage();
        stage.setX(Screen.getPrimary().getBounds().getWidth() - Screen.getPrimary().getBounds().getWidth() / 4);
        stage.setY(Screen.getPrimary().getBounds().getHeight() - Screen.getPrimary().getBounds().getHeight() / 4);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.WINDOW_MODAL);

        FXMLLoader chooseMoveLoader = new FXMLLoader(getClass().getResource("/gui/modals/ChooseMove/ChooseMove.fxml"));

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
     * @param path1  path option #1
     * @param path2  path option #2
     */
    public void choosePath(Player player, Path path1, Path path2) {
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);

        FXMLLoader choosePathLoader = new FXMLLoader(getClass().getResource("/gui/modals/ChoosePath/ChoosePath.fxml"));

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
     * Allows the Player to Display a Card
     * @param card card to be displayed
     */
    public void displayCard(Card card) {
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);

        FXMLLoader displayCardLoader = new FXMLLoader(getClass().getResource("/gui/modals/DisplayCard/DisplayCard.fxml"));
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
     * Allows the Player to choose a player from other Players
     * @param otherPlayers other players to choose from
     * @return             chosen Player
     */
    public Player choosePlayer(Player[] otherPlayers) {
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);

        FXMLLoader choosePlayerLoader = new FXMLLoader(getClass().getResource("/gui/modals/ChoosePlayer/ChoosePlayer.fxml"));
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
     * @return      chosen Card
     */
    public Card chooseCard(Card card1, Card card2) {
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);

        FXMLLoader chooseCardLoader = new FXMLLoader(getClass().getResource("/gui/modals/ChooseCard/ChooseCard.fxml"));
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
        try {
            Scene scene = new Scene(houseCardLoader.load());
            ((ChooseHouseController) houseCardLoader.getController()).setValues(houseDeck);
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }

        stage.showAndWait();
        return ((ChooseHouseController) houseCardLoader.getController()).getChosenHouse();
    }
}
