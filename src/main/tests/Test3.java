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
 * <h1>Test Script #3 (Requirement #2)</h1>
 * <p>This test script is for simulating a user defined number of players to draw the cards from a Deck
 * consisting of Action Cards(Orange Deck) where the first Player to reach a total asset of $40000
 * ([balance - debt] greater than or equal to 40000) wins.</p>
 *
 * <h1>Sequence:</h1>
 * <ol>
 *     <li>Generate CareerDeck and SalaryDeck</li>
 *     <li>Generate Players and assign the top CareerCard and SalaryCard of both CareerDeck and SalaryDeck</li>
 *     <li>Generate OrangeDeck (composed of ActionCards)</li>
 *     <li>Let Players take turns to draw a Card until a Player reaches the Goal (40000)</li>
 * </ol>
 *
 * <p>Note: Each turn waits for a Enter key to proceed to the next turn.</p>
 *
 * <p>This shows #2 requirement for the MP Phase 1 submission</p>
 *
 * @see Player
 * @see ActionCard
 *
 * @since 08/28/2020
 */
public class Test3 {
    public static void main(String[] args) {
        final int GOAL = 40000;
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
        int turn = 0;
//        Deck blueDeck = Generator.generateBlueDeck(nPlayers);
        while(orangeDeck.hasCard() && !hasWinner) {
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
                if(player.getBalance() - player.getDebt() >= GOAL) {
                    hasWinner = true;
                }
            }
            System.out.println(orangeDeck);
            if(hasWinner) {
                System.out.println();
                // if winner was found, display the winner(s)
                for(Player player : players) {
                    if(player.getBalance() - player.getDebt() >= GOAL) {
                        System.out.println(player.getName() + " won! ($" + player.getBalance() + ")");
                    }
                }
            } else {
                // if not wait for the Player to press the Enter key
                InputUtil.waitForEnterKey();

                turn++;
                // reset the turn to 0
                if(turn == players.size()) turn = 0;
            }
        }
    }
}
