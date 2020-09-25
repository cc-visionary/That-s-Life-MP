package gui.GameScreen;

import gui.ScreenStats.ScreenStatsController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import model.Constants;
import model.Paths.Path;
import model.Players.Player;
import model.Spaces.Space;

public class GameScreenController {
    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Canvas board;

    @FXML
    private StackPane screenStats;

    /**
     * Refreshes the Game Screen where it will clear all the children of Screen Stats,
     * and reassign the updated values then re-print the Board in the Canvas.
     * @param collegePath starting college path
     * @param careerPath  starting career path
     * @param player      current player taking the turn
     */
    public void refreshGameScreen(Path collegePath, Path careerPath, Player player) {
        System.out.println((player.getLocation() * (7 - getHeight(player.getPath()))) / (Constants.PATH_SPACES * 7.0));
        scrollPane.setHvalue((player.getLocation() * (7 - getHeight(player.getPath()))) / (Constants.PATH_SPACES * 5.0));
        board.setWidth(Constants.PATH_SPACES * 7 * 2 * Constants.HEXAGON_SIZE);
        board.setHeight(Constants.HEXAGON_SIZE * 30);
        screenStats.getChildren().clear();
        board.getGraphicsContext2D().clearRect(0, 0, board.getWidth(), board.getHeight());
        drawBoard(collegePath, careerPath, board.getGraphicsContext2D());
        try {
            FXMLLoader screenStatsLoader = new FXMLLoader(getClass().getResource("/gui/ScreenStats/ScreenStats.fxml"));
            screenStats.getChildren().add(screenStatsLoader.load());
            ((ScreenStatsController) screenStatsLoader.getController()).updateStats(player);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Return the height of a Path
     * @param path path whose height will be returned
     * @return     height of the path
     */
    private int getHeight(Path path) {
        if(path == null) return 0;
        return Math.max(getHeight(path.getPath1()), getHeight(path.getPath2())) + 1;
    }

    /**
     * Draws the board where it combines all the paths together
     * @param collegePath starting college path
     * @param careerPath  starting career path
     * @param gc          canvas' graphics context 2d
     */
    private void drawBoard(Path collegePath, Path careerPath, GraphicsContext gc) {
        double prevXPos = 0, prevYPos = 350;

        // COLLEGE PATH
        // cop2
        Path cop2 = collegePath;
        drawPath(prevXPos, prevYPos, cop2, gc);

        prevXPos += Constants.HEXAGON_SIZE * (Constants.PATH_SPACES * 2 - 1);
        prevYPos -= Constants.HEXAGON_SIZE * 2 - Constants.HEXAGON_SIZE / 2;

        // ccp4
        Path ccp4 = cop2.getPath2();
        drawPath(prevXPos, prevYPos, ccp4, gc);

        prevYPos += Constants.HEXAGON_SIZE * 3;

        // cap8
        Path cap8 = cop2.getPath1();
        drawPath(prevXPos, prevYPos, cap8, gc);

        prevXPos += Constants.PATH_SPACES * Constants.HEXAGON_SIZE * 2;

        // ccp3
        Path ccp3 = cap8.getPath1();
        drawPath(prevXPos, prevYPos, ccp3, gc);

        prevYPos -= Constants.HEXAGON_SIZE * 3;

        // cap6
        Path cap6 = ccp4.getPath1();
        drawPath(prevXPos, prevYPos, cap6, gc);

        prevXPos += Constants.HEXAGON_SIZE * (Constants.PATH_SPACES * 2 - 1);
        prevYPos += Constants.HEXAGON_SIZE * 2 - Constants.HEXAGON_SIZE / 2;

        // cap4
        Path cap4 = ccp3.getPath2();
        drawPath(prevXPos, prevYPos, cap4, gc);

        prevYPos += Constants.HEXAGON_SIZE * 3;

        // ccp1
        Path ccp1 = ccp3.getPath1();
        drawPath(prevXPos, prevYPos, ccp1, gc);

        prevXPos += Constants.HEXAGON_SIZE * (Constants.PATH_SPACES * 2 - 1);
        prevYPos -= Constants.HEXAGON_SIZE * 2 - Constants.HEXAGON_SIZE / 2;

        // cap2
        Path cap2 = cap4.getPath1();
        drawPath(prevXPos, prevYPos, cap2, gc);

        prevXPos += Constants.HEXAGON_SIZE * Constants.PATH_SPACES * 2;

        // safp1
        Path safp1 = cap2.getPath1();
        drawPath(prevXPos, prevYPos, safp1, gc);

        prevXPos = 0;
        prevYPos = 350 + Constants.HEXAGON_SIZE * 6;

        // CAREER PATH
        Path cap9 = careerPath;
        drawPath(prevXPos, prevYPos, cap9, gc);

        prevXPos += Constants.HEXAGON_SIZE * (Constants.PATH_SPACES * 2 - 1);
        prevYPos -= Constants.HEXAGON_SIZE * 2 - Constants.HEXAGON_SIZE / 2;

        // cop1
        Path cop1 = cap9.getPath2();
        drawPath(prevXPos, prevYPos, cop1, gc);

        prevYPos += Constants.HEXAGON_SIZE * 3;

        // cap7
        Path cap7 = cap9.getPath1();
        drawPath(prevXPos, prevYPos, cap7, gc);

        prevXPos += Constants.HEXAGON_SIZE * Constants.PATH_SPACES * 2;

        // ccp2
        Path ccp2 = cap7.getPath1();
        drawPath(prevXPos, prevYPos, ccp2, gc);

        prevYPos -= Constants.HEXAGON_SIZE * 3;

        // cap5
        Path cap5 = cop1.getPath1();
        drawPath(prevXPos, prevYPos, cap5, gc);

        prevXPos += Constants.HEXAGON_SIZE * (Constants.PATH_SPACES * 2 - 1);
        prevYPos += Constants.HEXAGON_SIZE * 2 - Constants.HEXAGON_SIZE / 2;

        // cap3
        Path cap3 = ccp2.getPath1();
        drawPath(prevXPos, prevYPos, cap3, gc);

        prevXPos += Constants.HEXAGON_SIZE * (Constants.PATH_SPACES * 2 - 1);
        prevYPos -= Constants.HEXAGON_SIZE * 2 - Constants.HEXAGON_SIZE / 2;

        // safp2
        Path safp2 = cap3.getPath1();
        drawPath(prevXPos, prevYPos, safp2, gc);

        prevXPos += Constants.HEXAGON_SIZE * Constants.PATH_SPACES * 2;

        // cap1
        Path cap1 = safp2.getPath1();
        drawPath(prevXPos, prevYPos, cap1, gc);

        prevXPos += Constants.HEXAGON_SIZE * (Constants.PATH_SPACES * 2 - 1);
        prevYPos -= Constants.HEXAGON_SIZE * 2 - Constants.HEXAGON_SIZE / 2;

        // rp
        Path rp = cap1.getPath1();
        drawPath(prevXPos, prevYPos, rp, gc);
    }

    /**
     * Draws the Path which contains spaces and connects the spaces to one another
     * @param startX starting X position of the path
     * @param startY starting Y position of the path
     * @param path   Path class to be drawn
     * @param gc     canvas' graphic context 2d
     */
    private void drawPath(double startX, double startY, Path path, GraphicsContext gc) {
        gc.setStroke(Color.BLACK);
        gc.fillText(path.getName(), startX + path.getNSpaces() * Constants.HEXAGON_SIZE, startY - Constants.HEXAGON_SIZE - 5);
        for(Space space : path.getSpaces()) {
            Color color = Color.BLACK;
            startX += Constants.HEXAGON_SIZE * 2;
            if(space.getType() == Constants.BLUE_SPACE) color = Color.BLUE;
            else if(space.getType() == Constants.ORANGE_SPACE) color = Color.ORANGE;
            else if(space.getType() == Constants.GREEN_SPACE) color = Color.GREEN;
            else if(space.getType() == Constants.MAGENTA_SPACE) color = Color.MAGENTA;
            drawHexagon(space.getPlayers().toArray(new Player[0]), startX, startY, Constants.HEXAGON_SIZE, color, gc); // right
        }
    }

    /**
     * Draw a Hexagon
     * @param players players in that space
     * @param xPos    center x position of the hexagon
     * @param yPos    center y position of the hexagon
     * @param size    size of the hexagon
     * @param color  color of the hexagon
     * @param gc      canvas' graphics context 2D
     */
    private void drawHexagon(Player[] players, double xPos, double yPos, double size, Color color, GraphicsContext gc) {
        final double RADIUS = size / 2;
        gc.setStroke(color);
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
//        gc.setFill(color);
//        gc.fillPolygon(new double[]{
//                xPos - RADIUS * 2, // top left
//                xPos,              // top center
//                xPos + RADIUS * 2, // top right
//                xPos + RADIUS * 2, // bottom right
//                xPos,              // bottom center
//                xPos - RADIUS * 2  // bottom left
//        }, new double[]{
//                yPos - RADIUS, // top left
//                yPos - size,   // top center
//                yPos - RADIUS, // top right
//                yPos + RADIUS, // bottom right
//                yPos + size,   // bottom center
//                yPos + RADIUS  // bottom left
//        }, 6);
        xPos -= (size / 2) * (players.length - 1) / 2;
        for(Player player : players) {
            gc.drawImage(new Image("/images/icons/character.png", size, size, false, true), xPos - size / 2, yPos - size / 2);
            xPos += size / 2;
        }
    }
}
