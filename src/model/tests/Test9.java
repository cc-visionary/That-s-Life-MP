package model.tests;

import gui.GameOfLife;
import model.Generator;
import model.paths.Path;
import model.players.Player;

/**
 * <h1>Test Script #9</h1>
 * <p>This test script simulates different Players passing through the Start a Family path
 * then go to Retirement.</p>
 *
 * <h1>Sequence</h1>
 * <ol>
 *     <li>Make an instance of Game of Life board where everything will be generated.</li>
 *     <li>Let Players take turns to roll a dice to cross the Start a Family path</li>
 *     <li>Then Switches to Retirement Path after the Start a Family Path</li>
 * </ol>
 *
 * <p>This proves that the GameOfLife class works where Players can move throughout the board and activate spaces they land on.</p>
 *
 * @see GameOfLife
 *
 * @since 09/05/2020
 */
public class Test9 {
    public static void main(String[] args) {
        GameOfLife gameOfLife = new GameOfLife();
        Path startAFamilyPath = Generator.generateStartAFamilyPath(Generator.generateRetirementPath(), null);
        for(Player player : gameOfLife.getAllPlayers()) {
            player.setPath(startAFamilyPath);
        }

        // lets player cross the Path
        while(!gameOfLife.hasWinner()) {
            gameOfLife.nextPlayer();
        }

        gameOfLife.endGame();
    }
}
