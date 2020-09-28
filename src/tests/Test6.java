package tests;

import model.Constants;
import model.Generator;
import model.Cards.CareerCard.CareerCard;
import model.Cards.SalaryCard.SalaryCard;
import model.Decks.Deck;
import model.Paths.Path;
import model.Players.Player;
import utilities.InputUtil;

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
 *
 * @see Generator
 * @see Player
 *
 * @since 09/05/2020
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
            Player player = new Player(200000);
            player.setName("P" + (i + 1));
            switch(choice) {
                case 1:
                    CareerCard careerCard = (CareerCard) careerDeck.pickTopCard();
                    SalaryCard salaryCard = (SalaryCard) salaryDeck.pickTopCard();
                    player.setPath(careerPath);
                    player.setCareerCard(careerCard);
                    player.setSalaryCard(salaryCard);
                    players.add(player);
                    break;
                case 2:
                    player.setPath(collegePath);
                    player.bankLoan(2);
                    players.add(player);
                    break;
            }
        }

        int turn = 0;
        boolean hasWinner = false;
        while(!hasWinner) {
            Player currentPlayer = players.get(turn);
            int rolled = currentPlayer.rollDice();
            for(int i = 0; i < rolled; i++) {
                currentPlayer.addLocation();

                // if player passes through retirement space, that player is simply retired
                if(currentPlayer.getPath().getSpaces()[currentPlayer.getLocation()].getType().equals(Constants.RETIREMENT_SPACE)) {
                    currentPlayer.retire();
                    break;
                }

                // if player is at the end of the Path (at the last space of the Path)
                if(currentPlayer.getLocation() == currentPlayer.getPath().getNSpaces() - 1) {
                    Path playerPath = currentPlayer.getPath();
                    if(playerPath.getPath2() == null) {
                        // there's only 1 path (path1) so we automatically assign it to the player
                        currentPlayer.setPath(playerPath.getPath1());
                    } else {
                        // there's 2 paths so we let the player choose
                        System.out.println("Paths to choose from:");
                        System.out.println("\t1. " + playerPath.getPath1().getName());
                        System.out.println("\t2. " + playerPath.getPath2().getName());
                        int choice = InputUtil.scanInt("Choose Path: ", 1, 2);
                        if(choice == 1) currentPlayer.setPath(playerPath.getPath1());
                        else currentPlayer.setPath(playerPath.getPath2());
                    }
                }
            }
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
