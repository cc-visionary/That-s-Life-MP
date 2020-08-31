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
 * <h1>Test Script #3</h1>
 * <p>This test script is showing that ActionCard.activate() does the correct action to the
 * Player just as expected</p>
 *
 * <h1>Sequence:</h1>
 * <ol>
 *     <li>Generate CareerDeck and SalaryDeck</li>
 *     <li>Generate Players and assign the top CareerCard and SalaryCard of both CareerDeck and SalaryDeck</li>
 *     <li>Generate OrangeDeck (composed of ActionCards)</li>
 *     <li>Draw the top card from the Deck</li>
 *     <li>Activate the Card while comparing the values in the Player</li>
 * </ol>
 *
 * <p>This proves that the ActionCard.activate() does the expected action to the player</p>
 *
 * @see Player
 * @see ActionCard
 *
 * @since 08/30/2020
 */
public class Test3 {
    public static void main(String[] args) {
        Deck careerDeck = Generator.generateCareerDeck();
        Deck salaryDeck = Generator.generateSalaryDeck();

        // sets 3 as the number of players
        int nPlayers = 3;

        ArrayList<Player> players = new ArrayList<Player>();
        for(int i = 0; i < nPlayers; i++) {
            CareerCard careerCard = (CareerCard) careerDeck.pickTopCard();
            SalaryCard salaryCard = (SalaryCard) salaryDeck.pickTopCard();
            players.add(new Player("P" + (i + 1), careerCard, salaryCard));
        }

        Deck orangeDeck = Generator.generateOrangeDeck();
        System.out.println(orangeDeck);
        orangeDeck.displayDeck();

        System.out.println();

        // draws the top card from the deck
        ActionCard actionCard = (ActionCard) orangeDeck.pickTopCard();
        // outputs the ActionCard info
        System.out.println(actionCard);

        // set the owner as the player at index 0
        actionCard.setOwner(players.get(0));

        // set the otherPlayers
        actionCard.setOtherPlayers(players.toArray(new Player[0]));

        // output the player before activating the card
        System.out.println(players.get(0));

        // activate the card
        actionCard.activate();

        // output the player after activating the card
        System.out.println(players.get(0));
    }
}
