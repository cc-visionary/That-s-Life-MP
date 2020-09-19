package gui;

import gui.board.Board;
import gui.choose.ChooseCard;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.StageStyle;
import main.Constants;
import main.GameOfLife;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import main.cards.CareerCard.CareerCard;
import main.cards.SalaryCard.SalaryCard;
import main.decks.Deck;
import main.paths.Path;
import main.players.Player;
import main.spaces.Space;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class GUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Game of Life");
        StackPane root = new StackPane();

        // screen stats
        FXMLLoader screenStatsLoader = new FXMLLoader(getClass().getResource("/gui/board/ScreenStats.fxml"));
        Pane screenStats = (Pane)screenStatsLoader.load();

        // game of life instance
        GameOfLife gameOfLife = new GameOfLife(screenStatsLoader.getController());


        primaryStage.setMaximized(true);
        primaryStage.setScene(new Scene(root, primaryStage.getWidth(), primaryStage.getHeight()));
        primaryStage.show();

        while(!gameOfLife.hasWinner()) {
            // board
            Board board = new Board(gameOfLife, Constants.PATH_SPACES * 7 * 2 * Constants.HEXAGON_SIZE, Screen.getPrimary().getBounds().getMaxY() - 150);
            ScrollPane scrollPane = new ScrollPane(board);
            root.getChildren().clear();
            root.getChildren().addAll(scrollPane, screenStats);
            StackPane.setMargin(screenStats, new Insets(0, 0, 50, 0));
            gameOfLife.nextPlayer();
        }
    }
}
