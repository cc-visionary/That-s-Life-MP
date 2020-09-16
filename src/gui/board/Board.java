package gui.board;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import main.Constants;
import main.GameOfLife;
import main.paths.Path;
import main.spaces.Space;

import java.util.ArrayList;

public class Board extends Canvas {
    public Board(GameOfLife gameOfLife, double width, double height) {
        super(width, height);
        drawBoard(gameOfLife, getGraphicsContext2D());
    }

    private void drawBoard(GameOfLife gameOfLife, GraphicsContext gc) {
//        drawHexagon(100 + Constants.HEXAGON_SIZE, 100 - Constants.HEXAGON_SIZE - Constants.HEXAGON_SIZE / 2, Constants.HEXAGON_SIZE, gc); // top right
//        drawHexagon(100 + Constants.HEXAGON_SIZE, 100 + Constants.HEXAGON_SIZE + Constants.HEXAGON_SIZE / 2, Constants.HEXAGON_SIZE, gc); // lower right
        double prevXPos = 0, prevYPos = 100;

        System.out.println("Career Path");

        // cop2
        Path cop2 = gameOfLife.getCollegePath();
        drawPath(prevXPos, prevYPos, cop2, gc);

        prevXPos += Constants.HEXAGON_SIZE * cop2.getNSpaces() * 2;

        // cap8
        Path cap8 = cop2.getPath1();
        drawPath(prevXPos, prevYPos, cap8, gc);

        prevXPos += Constants.HEXAGON_SIZE * (cap8.getNSpaces() * 2 - 1);
        prevYPos += Constants.HEXAGON_SIZE + Constants.HEXAGON_SIZE / 2;

        // cap6
        Path cap6 = cap8.getPath1();
        drawPath(prevXPos, prevYPos, cap6, gc);

        prevYPos -= Constants.HEXAGON_SIZE * 3;

        // ccp4
        Path ccp4 = cap8.getPath2();
        drawPath(prevXPos, prevYPos, ccp4, gc);

        prevXPos += Constants.HEXAGON_SIZE * (ccp4.getNSpaces() * 2 - 1);
        prevYPos += Constants.HEXAGON_SIZE * 2 - Constants.HEXAGON_SIZE / 2;

        // cap4
        Path cap4 = cap6.getPath2();
        drawPath(prevXPos, prevYPos, cap4, gc);

        prevYPos += Constants.HEXAGON_SIZE * 3;

        // ccp2
        Path ccp2 = cap6.getPath1();
        drawPath(prevXPos, prevYPos, ccp2, gc);

        prevXPos += Constants.HEXAGON_SIZE * (ccp2.getNSpaces() * 2 - 1);
        prevYPos -= Constants.HEXAGON_SIZE * 2 - Constants.HEXAGON_SIZE / 2;

        // cap2
        Path cap2 = ccp2.getPath2();
        drawPath(prevXPos, prevYPos, cap2, gc);

        prevYPos += Constants.HEXAGON_SIZE * 3;

        // ccp1
        Path ccp1 = cap4.getPath2();
        drawPath(prevXPos, prevYPos, ccp1, gc);

        prevXPos += Constants.HEXAGON_SIZE * (ccp1.getNSpaces() * 2 - 1);
        prevYPos -= Constants.HEXAGON_SIZE * 2 - Constants.HEXAGON_SIZE / 2;

        // safp2
        Path safp2 = cap2.getPath1();
        drawPath(prevXPos, prevYPos, safp2, gc);

        System.out.println(cop2.getUniqueName());
        System.out.println(cap8.getUniqueName());
        System.out.println(cap6.getUniqueName());
        System.out.println(ccp4.getUniqueName());
        System.out.println(ccp2.getUniqueName());
        System.out.println(cap4.getUniqueName());
        System.out.println(cap2.getUniqueName());
        System.out.println(ccp1.getUniqueName());
        System.out.println(safp2.getUniqueName());


        Path cap9 = gameOfLife.getCareerPath();
        Path cap7 = gameOfLife.getCareerPath().getPath1();
        Path cop1 = gameOfLife.getCareerPath().getPath2();
        Path ccp3 = cap7.getPath1();
        Path cap5 = cap7.getPath2();
        Path cap3 = ccp3.getPath1();
        Path safp1 = cap3.getPath1();
        Path cap1 = safp1.getPath1();
        Path rp = cap1.getPath1();
        System.out.println(cap9.getUniqueName());
        System.out.println(cap7.getUniqueName());
        System.out.println(cop1.getUniqueName());
        System.out.println(ccp3.getUniqueName());
        System.out.println(cap5.getUniqueName());
        System.out.println(cap3.getUniqueName());
        System.out.println(safp1.getUniqueName());
        System.out.println(cap1.getUniqueName());
        System.out.println(rp.getUniqueName());
    }

    private void drawPath(double startX, double startY, Path path, GraphicsContext gc) {
        for(Space space : path.getSpaces()) {
            startX += Constants.HEXAGON_SIZE * 2;
            drawHexagon(startX, startY, Constants.HEXAGON_SIZE, gc); // right
        }
    }

    private void drawHexagon(double xPos, double yPos, double size, Color fill, Color stroke, GraphicsContext gc) {
        final double RADIUS = size / 2;
        gc.setFill(fill);
        gc.setStroke(stroke);
        gc.strokePolygon(new double[]{
                xPos - RADIUS * 2, // top left
                xPos,              // top center
                xPos + RADIUS * 2, // top right
                xPos + RADIUS * 2, // bottom right
                xPos,              // bottom center
                xPos - RADIUS * 2  // bottom left
        }, new double[]{
                yPos - RADIUS, // top left
                yPos - size,   // top center
                yPos - RADIUS, // top right
                yPos + RADIUS, // bottom right
                yPos + size,   // bottom center
                yPos + RADIUS  // bottom left
        }, 6);
    }

    private void drawHexagon(double xPos, double yPos, double size, Color stroke, GraphicsContext gc) {
        final double RADIUS = size / 2;
        gc.setStroke(stroke);
        gc.strokePolygon(new double[]{
                xPos - RADIUS * 2, // top left
                xPos,              // top center
                xPos + RADIUS * 2, // top right
                xPos + RADIUS * 2, // bottom right
                xPos,              // bottom center
                xPos - RADIUS * 2  // bottom left
        }, new double[]{
                yPos - RADIUS, // top left
                yPos - size,   // top center
                yPos - RADIUS, // top right
                yPos + RADIUS, // bottom right
                yPos + size,   // bottom center
                yPos + RADIUS  // bottom left
        }, 6);
    }

    private static void drawHexagon(double xPos, double yPos, double size, GraphicsContext gc) {
        final double RADIUS = size / 2;
        gc.strokePolygon(new double[]{
                xPos - RADIUS * 2, // top left
                xPos,              // top center
                xPos + RADIUS * 2, // top right
                xPos + RADIUS * 2, // bottom right
                xPos,              // bottom center
                xPos - RADIUS * 2  // bottom left
        }, new double[]{
                yPos - RADIUS, // top left
                yPos - size,   // top center
                yPos - RADIUS, // top right
                yPos + RADIUS, // bottom right
                yPos + size,   // bottom center
                yPos + RADIUS  // bottom left
        }, 6);
    }


}
