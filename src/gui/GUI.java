package gui;

import gui.board.Board;
import gui.stats.ScreenStats.ScreenStatsController;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import model.Constants;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main App
 */

public class GUI extends Application {
    private StackPane root;
    private GameOfLife gameOfLife;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Game of Life");
        root = new StackPane();



        primaryStage.setMaximized(true);
        primaryStage.setScene(new Scene(root, primaryStage.getWidth(), primaryStage.getHeight()));
        primaryStage.show();
    }

    public void whenMenu() {

    }

    public void whenGame() throws IOException {
        while(!gameOfLife.hasWinner()) {
            // refreshes the board
            Board board = new Board(gameOfLife, Constants.PATH_SPACES * 7 * 2 * Constants.HEXAGON_SIZE, Screen.getPrimary().getBounds().getMaxY() - 150);
            ScrollPane scrollPane = new ScrollPane(board);

            // screen stats
            FXMLLoader screenStatsLoader = new FXMLLoader(getClass().getResource("/gui/stats/ScreenStats/ScreenStats.fxml"));
            Pane screenStats = (Pane)screenStatsLoader.load();
            ScreenStatsController screenStatsController = screenStatsLoader.getController();

            // game of life instance
            gameOfLife = new GameOfLife(screenStatsController);
            root.getChildren().clear();
            root.getChildren().addAll(scrollPane, screenStats);
            StackPane.setMargin(screenStats, new Insets(0, 0, 50, 0));
            gameOfLife.nextPlayer();
        }
    }

    public void whenChoosingMove() {

    }

    public void whenChoosingPath() {

    }

    public void whenChoosingCard() {

    }

    public void whenChoosingPlayer() {

    }

    public void whenViewingPlayerStats() {

    }

    public void whenViewingRoundStats() {

    }

    public void notify(String message) {

    }
}
