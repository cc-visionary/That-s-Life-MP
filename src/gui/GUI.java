package gui;

import gui.board.Board;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import javafx.scene.paint.Color;
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

        Board board = new Board(gameOfLife, Constants.PATH_SPACES * 7 * 2 * Constants.HEXAGON_SIZE, 720);

        StackPane root = new StackPane();
        ScrollPane scrollPane = new ScrollPane(root);
        root.getChildren().add(board);
        primaryStage.setMaximized(true);
        primaryStage.setScene(new Scene(scrollPane, primaryStage.getWidth(), primaryStage.getHeight()));
        primaryStage.show();
    }
}
