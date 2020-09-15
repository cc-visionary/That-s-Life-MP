package main.tests;

import main.Generator;
import main.decks.Deck;
import main.decks.DeckWithUsed;

/**
 * <h1>Test Script #2</h1>
 * <p>Generates the OrangeDeck, BlueDeck, CareerDeck, and SalaryDeck
 * then draws until it shows the scenario where all the decks are empty.</p>
 *
 * <h1>Sequence:</h1>
 * <ol>
 *     <li>Generates the OrangeDeck, BlueDeck, CareerDeck, and SalaryDeck</li>
 *     <li>Displays each decks</li>
 *     <li>Empty each deck by drawing a Card until the Deck is empty</li>
 *     <li>Draw a card 1 last time to show the deck is empty</li>
 * </ol>
 *
 * <p>This proves that generating a Deck and drawing a Card from the Deck until the Deck is empty works.</p>
 *
 * @see Deck
 * @see Generator
 *
 * @since 08/31/2020
 */
public class Test2 {
    public static void main(String[] args) {
        DeckWithUsed orangeDeck = Generator.generateOrangeDeck();
        Deck blueDeck = Generator.generateBlueDeck(2);
        Deck careerDeck = Generator.generateCareerDeck();
        Deck salaryDeck = Generator.generateSalaryDeck();

        Deck[] decks = {orangeDeck, blueDeck, careerDeck, salaryDeck};

        for(Deck deck : decks) {
            System.out.println("\n" + deck);
            deck.displayDeck();
        }

        for(Deck deck : decks) {
            while(deck.hasCard()) {
                deck.pickTopCard();
            }
            System.out.println();
            deck.pickTopCard();
            System.out.println("\n" + deck);
        }
    }
}
