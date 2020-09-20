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
 * <h1>Test Script #1</h1>
 * <p>Generates CareerDeck and SalaryDeck then creates 3 Players which will have the
 * top card of each CareerDeck and SalaryDeck as CareerCard and SalaryCard.</p>
 *
 * <h1>Sequence:</h1>
 * <ol>
 *     <li>Generate CareerDeck and SalaryDeck</li>
 *     <li>Output the CareerDeck and SalaryDeck</li>
 *     <li>Create 3 Players and assign the top most CareerCard and SalaryCard from both the CareerDeck and SalaryDeck</li>
 *     <li>Output the CareerDeck and SalaryDeck</li>
 * </ol>
 *
 * <p>This proves that generating CareerDeck and SalaryDeck works and picking the top most card
 * of each deck works since it is successfully assign to the Player</p>
 *
 * @see Player
 * @see CareerCard
 * @see SalaryCard
 * @see Generator
 *
 * @since 08/31/2020
 */

public class Test1 {
    public static void main(String[] args) {
        Deck careerDeck = Generator.generateCareerDeck();
        Deck salaryDeck = Generator.generateSalaryDeck();
        System.out.println("\n" + careerDeck);
        careerDeck.displayDeck();
        System.out.println("\n" + salaryDeck);
        salaryDeck.displayDeck();

        // sets 3 as the number of players
        int nPlayers = 3;

        ArrayList<Player> players = new ArrayList<Player>();
        for(int i = 0; i < nPlayers; i++) {
            CareerCard careerCard = (CareerCard) careerDeck.pickTopCard();
            SalaryCard salaryCard = (SalaryCard) salaryDeck.pickTopCard();
            players.add(new Player("P" + (i + 1), careerCard, salaryCard));
        }

        System.out.println("\n" + careerDeck);
        careerDeck.displayDeck();
        System.out.println("\n" + salaryDeck);
        salaryDeck.displayDeck();
    }
}
