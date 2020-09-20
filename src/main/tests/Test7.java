package main.tests;

import main.GameOfLife;

/**
 * <h1>Test Script #7</h1>
 * <p>This test script simulates a Player rolling a dice on the board, switching paths,
 * and activating spaces.</p>
 *
 * <h1>Sequence</h1>
 * <ol>
 *     <li>Make an instance of Game of Life board where everything will be generated.</li>
 *     <li>Let Players take turns to roll a dice to cross the board</li>
 * </ol>
 *
 * <p>This proves that the GameOfLife class works where Players can move throughout the board and activate spaces they land on.</p>
 *
 * @see GameOfLife
 *
 * @since 09/05/2020
 */
public class Test7 {
    public static void main(String[] args) {
        GameOfLife gameOfLife = new GameOfLife();
        while(!gameOfLife.hasWinner()) {
            gameOfLife.nextPlayer();
        }
        gameOfLife.endGame();
    }
}
