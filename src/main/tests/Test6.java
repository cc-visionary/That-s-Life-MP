package main.tests;

import main.Generator;
import main.cards.CareerCard.CareerCard;
import main.cards.SalaryCard.SalaryCard;
import main.decks.Deck;
import main.paths.Path;
import main.players.Player;
import main.utilities.InputUtil;

import java.util.ArrayList;

/**
 * <h1>Test Script #6</h1>
 * <p>This test script simulates a Player rolling a dice on the board, switching paths,
 * and activating spaces.</p>
 *
 * <h1>Sequence</h1>
 * <ol>
 *     <li>Generate CareerDeck and SalaryDeck</li>
 *     <li>Generate OrangeDeck (composed of ActionCards), BlueDeck (composed of BlueCard), and HouseDeck (composed of HouseCards)</li>
 *     <li>Generate Board, returning the 2 starting Paths Career Path and College Path</li>
 *     <li>Generate Players let them choose their starting Path</li>
 *     <li>Let Players take turns to roll a dice to cross the board</li>
 * </ol>
 *
 * <p>This proves that the Players can move throughout the board.</p>
 */
public class Test6 {
    public static void main(String[] args) {
        Deck careerDeck = Generator.generateCareerDeck();
        Deck salaryDeck = Generator.generateSalaryDeck();

        // asks the user for the number of players (makes sures that the input is only integers {1, 2, 3})
        int nPlayers = InputUtil.scanInt("Enter number of Players: ", 1, 3);

        Deck orangeDeck = Generator.generateOrangeDeck();
        System.out.println(orangeDeck);
        orangeDeck.displayDeck();

        Deck blueDeck = Generator.generateBlueDeck(nPlayers);
        System.out.println(blueDeck);
        blueDeck.displayDeck();

        Deck houseDeck = Generator.generateHouseDeck();
        System.out.println(houseDeck);
        houseDeck.displayDeck();

        Path startingPaths[] = Generator.generateBoard();
        Path careerPath = startingPaths[0];
        Path collegePath = startingPaths[1];

        ArrayList<Player> players = new ArrayList<Player>();
        for(int i = 0; i < nPlayers; i++) {
            System.out.println("Choose Starting Path for " + "P" + (i + 1) + ":");
            System.out.println("\t[1] Career Path");
            System.out.println("\t[2] College Path");
            int choice = InputUtil.scanInt("Choice: ", 1, 2);
            switch(choice) {
                case 1:
                    CareerCard careerCard = (CareerCard) careerDeck.pickTopCard();
                    SalaryCard salaryCard = (SalaryCard) salaryDeck.pickTopCard();
                    players.add(new Player("P" + (i + 1), careerPath, careerCard, salaryCard));
                    break;
                case 2:
                    players.add(new Player("P" + (i + 1), collegePath));
                    break;
            }
        }

        int turn = 0;
        boolean hasWinner = false;
        while(!hasWinner) {
            Player currentPlayer = players.get(turn);

            currentPlayer.chooseMove();

            for(Player player : players) {
                System.out.println(player);
                if(player.isRetired()) hasWinner = true;
            }
            turn++;
            if(turn == nPlayers) turn = 0;
        }
        for(Player player : players) {
            if (player.isRetired()) System.out.println(player.getName() + " won!");
        }
    }
}
