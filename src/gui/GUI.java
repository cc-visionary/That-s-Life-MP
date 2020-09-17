package gui;

import gui.board.Board;
import gui.board.ScreenStats;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import main.Constants;
import main.GameOfLife;

import java.lang.Math;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.shape.Polygon;

public class GUI extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Game of Life");

        GameOfLife gameOfLife = new GameOfLife();

        Board board = new Board(gameOfLife, Constants.PATH_SPACES * 7 * 2 * Constants.HEXAGON_SIZE, Screen.getPrimary().getBounds().getMaxY() - 150);

        ScreenStats screenStats = new ScreenStats(gameOfLife);

        ScrollPane scrollPane = new ScrollPane(board);
        StackPane root = new StackPane();
        root.getChildren().addAll(scrollPane, screenStats);
        StackPane.setMargin(screenStats, new Insets(0, 0, 50, 0));
        primaryStage.setMaximized(true);
        primaryStage.setScene(new Scene(root, primaryStage.getWidth(), primaryStage.getHeight()));
        primaryStage.show();
    }
}
