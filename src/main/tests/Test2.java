package main.tests;

import main.Generator;
import main.decks.Deck;

/**
 * Generates the Decks
 * Generates OrangeDeck, BlueDeck, CareerDeck, and SalaryDeck
 *
 * @see Deck
 * @see Generator
 */
public class Test2 {
    public static void main(String[] args) {
        Deck orangeDeck = Generator.generateOrangeDeck();
        Deck blueDeck = Generator.generateBlueDeck(2);
        Deck careerDeck = Generator.generateCareerDeck();
        Deck salaryDeck = Generator.generateSalaryDeck();

        System.out.println("\n" + orangeDeck);
        orangeDeck.displayDeck();
        System.out.println("\n" + blueDeck);
        blueDeck.displayDeck();
        System.out.println("\n" + careerDeck);
        careerDeck.displayDeck();
        System.out.println("\n" + salaryDeck);
        salaryDeck.displayDeck();
    }
}
