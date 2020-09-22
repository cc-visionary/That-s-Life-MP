package gui;

import gui.choose.ChooseMove.ChooseMoveController;
import gui.choose.ChoosePath.ChoosePathController;
import gui.game.GameScreen.GameScreenController;
import javafx.fxml.FXMLLoader;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Generator;
import model.paths.Path;
import model.players.Player;

import java.util.ArrayList;

/**
 * Main App
 */

public class GUI extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Game of Life");

        primaryStage.show();

        runGame(primaryStage);
    }

    public void runGame(Stage primaryStage) {
        GameOfLife gameOfLife = new GameOfLife();
        gameOfLife.startGame();

        FXMLLoader gameScreenLoader = new FXMLLoader(getClass().getResource("/gui/game/GameScreen/GameScreen.fxml"));

        try {
            StackPane root = gameScreenLoader.load();
            Scene scene = new Scene(root);

            primaryStage.setScene(scene);
            primaryStage.setMaximized(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        GameScreenController gameScreenController = gameScreenLoader.getController();

        while (!gameOfLife.hasWinner()) {
            gameScreenController.refreshGameScreen(gameOfLife.getCollegePath(), gameOfLife.getCareerPath(), gameOfLife.getCurrentPlayer());

            if(gameOfLife.getCurrentPlayer().getPath() == null) choosePath(gameOfLife.getCurrentPlayer(), gameOfLife.getCareerPath(), gameOfLife.getCollegePath(), gameScreenController);
            displayChooseMove(gameOfLife, gameScreenController, this);
        }
    }

    public void displayChooseMove(GameOfLife gameOfLife, GameScreenController gameScreenController, GUI gui) {
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

    public void choosePath(Player player, Path path1, Path path2, GameScreenController gameScreenController) {
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);

        FXMLLoader choosePathLoader = new FXMLLoader(getClass().getResource("/gui/choose/ChoosePath/ChoosePath.fxml"));

        try {
            Scene scene = new Scene(choosePathLoader.load());
            stage.setScene(scene);
        } catch(Exception e) {
            e.printStackTrace();
        }

        ChoosePathController choosePathController = choosePathLoader.getController();
        choosePathController.setPaths(player, path1, path2);

        stage.showAndWait();
        gameScreenController.refreshGameScreen(path2, path1, player);
    }
}
