package main.tests;

import main.Generator;
import main.cards.ActionCard.ActionCard;
import main.cards.CareerCard.CareerCard;
import main.cards.SalaryCard.SalaryCard;
import main.decks.Deck;
import main.players.Player;
import main.utilities.InputUtil;

import java.util.ArrayList;

/**
 * Players drawing the Cards Simulation
 * This test script is for simulating a user defined number of players to draw the cards from a Deck
 * consisting of Action Cards where the first Player to reach a total asset of $22000 (balance - debt >= 22000) wins.
 *
 * Tested in this Test Script:
 *      Generator.generateCareerDeck()
 *      Generator.generateSalaryDeck()
 *      Generator.generateOrangeDeck()
 *      Deck.displayDeck()
 *      Deck.pickTopCrd()
 *      Card.setOwner()
 *      Card.setOtherPlayers()
 *      Card.activate()
 *      Player.setSalaryCard()
 *      Player.setCareerCard()
 *      Player.getBalance()
 *      Player.getDebt()
 *      Player.payBalance()
 *      Player.addBalance()
 */
public class Test1 {
    public static void main(String[] args) {
        Deck careerDeck = Generator.generateCareerDeck();
        Deck salaryDeck = Generator.generateSalaryDeck();

        // asks the user for the number of players (makes sures that the input is only integers {1, 2, 3})
        int nPlayers = InputUtil.scanInt("Enter number of Players: ", 1, 3);

        ArrayList<Player> players = new ArrayList<Player>();
        for(int i = 0; i < nPlayers; i++) {
            CareerCard careerCard = (CareerCard) careerDeck.pickTopCard();
            SalaryCard salaryCard = (SalaryCard) salaryDeck.pickTopCard();
            players.add(new Player("P" + (i + 1), careerCard, salaryCard));
        }

        Deck orangeDeck = Generator.generateOrangeDeck();
        System.out.println(orangeDeck);
        orangeDeck.displayDeck();
        int turn = 0;
//        Deck blueDeck = Generator.generateBlueDeck(nPlayers);
        while(orangeDeck.hasCard()) {
            Player currentPlayer = players.get(turn);

            System.out.println("\n" + currentPlayer.getName() + "'s turn: ");

            // picks the top card and assigns it to the currentPlayer
            ActionCard currentCard = (ActionCard) orangeDeck.pickTopCard();
            System.out.println(currentPlayer.getName() + " drew -> " + currentCard);
//            currentCard.displayCard();

            // sets the cards owner and tracks the other players
            currentCard.setOwner(currentPlayer);
            currentCard.setOtherPlayers(players.toArray(new Player[0]));
            // then activates the card
            currentCard.activate();

            // show player info and at the same time check if there's a winner
            System.out.println("Player Stats:");
            for(Player player : players) {
                System.out.println("\t" + player);
                if(player.getBalance() - player.getDebt() >= 22000) {
                    System.out.println(player.getName() + " won!");
                    System.out.println("He reached a total asset of $" + player.getBalance());
                    break;
                }
            }
            System.out.println(orangeDeck);

            InputUtil.waitForAnyKey();

            turn++;
            // reset the turn to 0
            if(turn == players.size()) turn = 0;
        }
    }


}
