package tests;

import model.Cards.CareerCard.CareerCard;
import model.Cards.SalaryCard.SalaryCard;
import model.Constants;
import model.GameOfLife;
import model.Paths.Path;
import model.Players.Player;
import utilities.InputUtil;

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
 * <p>note: Make sure to uncomment model.GameOfLife.addTurn()'s new Modal().showRoundStats</p>
 * <p>note: Make sure to uncomment model.GameOfLife.addRoundStats()'s new Modal().showUpdates</p>
 *
 * @since 09/05/2020
 */
public class Test7 {
    public static void main(String[] args) {
        GameOfLife gameOfLife = new GameOfLife(2, 200000);
        while(!gameOfLife.hasEnded()) {
            if(gameOfLife.getCurrentPlayer().getName() == null) gameOfLife.getCurrentPlayer().setName("P" + (gameOfLife.getTurn() + 1));
            if(gameOfLife.getCurrentPlayer().getPath() == null) {
                System.out.println("Choose Starting Path for " + gameOfLife.getCurrentPlayer().getName() + ":");
                System.out.println("\t[1] Career Path");
                System.out.println("\t[2] College Path");
                int choice = InputUtil.scanInt("Choice: ", 1, 2);
                switch(choice) {
                    case 1:
                        CareerCard careerCard = (CareerCard) gameOfLife.getCareerDeck().pickTopCard();
                        SalaryCard salaryCard = (SalaryCard) gameOfLife.getSalaryDeck().pickTopCard();
                        gameOfLife.getCurrentPlayer().setPath(gameOfLife.getCareerPath());
                        gameOfLife.getCurrentPlayer().setCareerCard(careerCard);
                        gameOfLife.getCurrentPlayer().setSalaryCard(salaryCard);
                        break;
                    case 2:
                        gameOfLife.getCurrentPlayer().setPath(gameOfLife.getCollegePath());
                        gameOfLife.getCurrentPlayer().bankLoan(2);
                        break;
                }
            }
            System.out.println(String.format("%s's turn", gameOfLife.getCurrentPlayer().getName()));
            int rolled = gameOfLife.getCurrentPlayer().rollDice();
            for(int i = 0; i < rolled; i++) {
                gameOfLife.getCurrentPlayer().addLocation();

                // if player passes through retirement space, that player is simply retired
                if(gameOfLife.getCurrentPlayer().getPath().getSpaces()[gameOfLife.getCurrentPlayer().getLocation()].getType().equals(Constants.RETIREMENT_SPACE)) {
                    gameOfLife.getCurrentPlayer().retire();
                    break;
                }

                // if player is at the end of the Path (at the last space of the Path)
                if(gameOfLife.getCurrentPlayer().getLocation() == gameOfLife.getCurrentPlayer().getPath().getNSpaces() - 1) {
                    Path playerPath = gameOfLife.getCurrentPlayer().getPath();
                    if(playerPath.getPath2() == null) {
                        // there's only 1 path (path1) so we automatically assign it to the player
                        gameOfLife.getCurrentPlayer().setPath(playerPath.getPath1());
                    } else {
                        // there's 2 paths so we let the player choose
                        System.out.println("Paths to choose from:");
                        System.out.println("\t1. " + playerPath.getPath1().getName());
                        System.out.println("\t2. " + playerPath.getPath2().getName());
                        int choice = InputUtil.scanInt("Choose Path: ", 1, 2);
                        if(choice == 1) gameOfLife.getCurrentPlayer().setPath(playerPath.getPath1());
                        else gameOfLife.getCurrentPlayer().setPath(playerPath.getPath2());
                    }
                }
            }
            gameOfLife.addTurn();
        }
        for (Player player : gameOfLife.getAllRetiredPlayers()) {
            System.out.println(player);
        }
    }
}
