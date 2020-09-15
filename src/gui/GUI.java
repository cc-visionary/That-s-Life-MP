package gui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
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
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });

        Canvas canvas = new Canvas(600, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();


//        drawHexagon(100 + Constants.HEXAGON_SIZE, 100 - Constants.HEXAGON_SIZE - Constants.HEXAGON_SIZE / 2, Constants.HEXAGON_SIZE, gc); // top right
//        drawHexagon(100 + Constants.HEXAGON_SIZE, 100 + Constants.HEXAGON_SIZE + Constants.HEXAGON_SIZE / 2, Constants.HEXAGON_SIZE, gc); // lower right
        double prevXPos = 100, prevYPos = 100;
        for(int i = 0; i < 10; i++) {
            drawHexagon(prevXPos + Constants.HEXAGON_SIZE * 2, prevYPos, Constants.HEXAGON_SIZE, gc); // right
            prevXPos = prevXPos + Constants.HEXAGON_SIZE * 2;
        }
        drawHexagon(prevXPos + Constants.HEXAGON_SIZE, prevYPos - Constants.HEXAGON_SIZE - Constants.HEXAGON_SIZE / 2, Constants.HEXAGON_SIZE, gc);

        StackPane root = new StackPane();
        root.getChildren().add(canvas);
        primaryStage.setMaximized(true);
        primaryStage.setScene(new Scene(root, primaryStage.getWidth(), primaryStage.getHeight()));
        primaryStage.show();
    }

    public static void drawHexagon(double xPos, double yPos, double size, Color fill, Color stroke, GraphicsContext gc) {
        final double RADIUS = size / 2;
        gc.setFill(fill);
        gc.setStroke(stroke);
        gc.strokePolygon(new double[]{
                xPos - RADIUS * 2,
                xPos,
                xPos + RADIUS * 2,
                xPos + RADIUS * 2,
                xPos,
                xPos - RADIUS * 2
        }, new double[]{
                yPos - RADIUS,
                yPos - size,
                yPos - RADIUS,
                yPos + RADIUS,
                yPos + size,
                yPos + RADIUS
        }, 6);
    }

    public static void drawHexagon(double xPos, double yPos, double size, Color stroke, GraphicsContext gc) {
        final double RADIUS = size / 2;
        gc.setStroke(stroke);
        gc.strokePolygon(new double[]{
                xPos - RADIUS * 2,
                xPos,
                xPos + RADIUS * 2,
                xPos + RADIUS * 2,
                xPos,
                xPos - RADIUS * 2
        }, new double[]{
                yPos - RADIUS,
                yPos - size,
                yPos - RADIUS,
                yPos + RADIUS,
                yPos + size,
                yPos + RADIUS
        }, 6);
    }

    public static void drawHexagon(double xPos, double yPos, double size, GraphicsContext gc) {
        final double RADIUS = size / 2;
        gc.strokePolygon(new double[]{
                xPos - RADIUS * 2,
                xPos,
                xPos + RADIUS * 2,
                xPos + RADIUS * 2,
                xPos,
                xPos - RADIUS * 2
        }, new double[]{
                yPos - RADIUS,
                yPos - size,
                yPos - RADIUS,
                yPos + RADIUS,
                yPos + size,
                yPos + RADIUS
        }, 6);
    }
}
