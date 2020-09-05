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
 *     <li>Generate Players and assign the top CareerCard and SalaryCard of both CareerDeck and SalaryDeck</li>
 *     <li>Generate OrangeDeck (composed of ActionCards), BlueDeck (composed of BlueCard), and HouseDeck (composed of HouseCards)</li>
 *     <li>Generate Board</li>
 *     <li>Let Players take turns to roll a dice to cross the board</li>
 * </ol>
 *
 * <p>This proves that the Players can move throughout the board and activate spaces they land on.</p>
 */
public class Test6 {
    public static void main(String[] args) {
        Deck careerDeck = Generator.generateCareerDeck();
        Deck salaryDeck = Generator.generateSalaryDeck();

        // asks the user for the number of players (makes sures that the input is only integers {1, 2, 3})
        int nPlayers = InputUtil.scanInt("Enter number of Players: ", 1, 3);
        boolean hasWinner = false;

        ArrayList<Player> players = new ArrayList<Player>();
        for(int i = 0; i < nPlayers; i++) {
            CareerCard careerCard = (CareerCard) careerDeck.pickTopCard();
            SalaryCard salaryCard = (SalaryCard) salaryDeck.pickTopCard();
            players.add(new Player("P" + (i + 1), careerCard, salaryCard));
        }

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


    }
}
