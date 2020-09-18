package gui;

import gui.board.Board;
import gui.board.ScreenStatsController;
import gui.choosePath.ChoosePath;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import main.Constants;
import main.GameOfLife;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class GUI extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Game of Life");

        FXMLLoader screenStatsLoader = new FXMLLoader(getClass().getResource("/gui/board/ScreenStats.fxml"));
        Pane screenStats = (Pane)screenStatsLoader.load();

        GameOfLife gameOfLife = new GameOfLife(screenStatsLoader.getController());

        Board board = new Board(gameOfLife, Constants.PATH_SPACES * 7 * 2 * Constants.HEXAGON_SIZE, Screen.getPrimary().getBounds().getMaxY() - 150);

        ChoosePath choosePath = new ChoosePath(gameOfLife.getCurrentPlayer().getPath().getPath1(), gameOfLife.getCurrentPlayer().getPath().getPath2());

        ScrollPane scrollPane = new ScrollPane(board);
        StackPane root = new StackPane();
        root.getChildren().addAll(scrollPane, screenStats, choosePath);
        StackPane.setMargin(screenStats, new Insets(0, 0, 50, 0));
        primaryStage.setMaximized(true);
        primaryStage.setScene(new Scene(root, primaryStage.getWidth(), primaryStage.getHeight()));
        primaryStage.show();
    }
}
