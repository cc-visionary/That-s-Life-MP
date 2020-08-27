package main;

import main.cards.ActionCard.*;
import main.cards.BlueCard.*;
import main.cards.Card;
import main.cards.CareerCard.CareerCard;
import main.cards.SalaryCard.SalaryCard;
import main.decks.Deck;
import main.utilities.RandomUtil;

/**
 * Generator Class
 *   consists of Generators for Decks, Paths, ...
 *
 * @author        Christopher G. Lim
 * @version       1.0
 * @last_modified Aug 27, 2020
 * @since         Aug 27, 2020
 */

public class Generator {
    /**
     * Generates a Deck for Orange Space composed of ActionCards
     * @return the generated and shuffled Deck for Orange Space
     * @see Deck
     * @see ActionCard
     */
    public static Deck generateOrangeDeck() {
        // Collect from the Bank (40% - 20 cards)
        Card[] collectBankCards = {
                new CollectBankCard("Tax Refund", "You got a tax refund! Collect from the bank!", 100),
                new CollectBankCard("Sell an Item", "You sold an item! Collect from the bank!", 150),
                new CollectBankCard("Bonus Payday", "You had a bonus payday! Collect from the bank!", 400),
                new CollectBankCard("Setup School", "The school you setup was successful! Collect from the bank!", 1000),
                new CollectBankCard("Write a Book", "The book you wrote became a best-seller! Collect from  the Bank!", 1000)
        };

        // Pay the Bank (40% - 20 cards)
        Card[] payBankCards = {
                new PayBankCard("Buy an Item", "You bought an item! You need to pay the bank!", 400),
                new PayBankCard("Visit a Place", "You visited a place! You need to pay the bank!", 200),
                new PayBankCard("Hiking", "You went hiking! You enjoyed it, but now it's time to pay the bank!", 100),
                new PayBankCard("Watch a Show", "You watched a show! You need to pay the bank!", 125),
                new PayBankCard("Traffic Violation", "You crossed the while it was red on the traffic light. You need to pay the bank!", 100)
        };

        // Pay the Player (10% - 5 cards)
        Card[] payPlayerCards = {
                new PayPlayerCard("Lawsuit", "A Lawsuit has been filed on you! Pay the other player!", 200),
                new PayAllCard("Christmas Bonus", "It's Christmas time! Time for you to pay all the other players!", 100)
        };

        // Collect from Players (10% - 5 cards)
        Card[] collectPlayerCards = {
                new CollectPlayerCard("File a Lawsuit", "You're lawsuit was successful! You'll receive money from the other player!", 400),
                new CollectAllCard("It's Your Birthday", "Happy Birthday! All the other players gave you money as a gift!", 200)
        };

        Deck orangeDeck = new Deck("Orange Deck");
        // randomly choose from any of Collect from Bank Cards
        for(int i = 0; i < 20; i++) orangeDeck.addCard(RandomUtil.chooseRandomCard(collectBankCards));
        // randomly choose from any of Pay to Bank Cards
        for(int i = 0; i < 20; i++) orangeDeck.addCard(RandomUtil.chooseRandomCard(payBankCards));
        // randomly choose from any of Pay to Player Bank Cards
        for(int i = 0; i < 5; i++) orangeDeck.addCard(RandomUtil.chooseRandomCard(payPlayerCards));
        // randomly choose from any of Collect from Player Bank Cards
        for(int i = 0; i < 5; i++) orangeDeck.addCard(RandomUtil.chooseRandomCard(collectPlayerCards));

        orangeDeck.shuffle();

        return orangeDeck;
    }

    /**
     * Generates a Deck composed of CareerCard to let the users
     * draw a random career from a Deck
     * @return the generated and shuffled CareerDeck which consists of Career Cards
     * @see Deck
     * @see CareerCard
     */
    public static Deck generateCareerDeck() {
        Card[] careers = {
                new CareerCard("Lawyer", "A lawyer who makes law to create order in the world.", 5, 8, true),
                new CareerCard("Accountant", "", 4, 7, true),
                new CareerCard("Comp. Consultant", "", 3, 7, true),
                new CareerCard("Doctor", "", 5, 8, true),
                new CareerCard("Server", "", 1, 4, false),
                new CareerCard("Racecar Driver", "Broom Broom!", 2, 8, false),
                new CareerCard("Athlete", "You're a Sporty person huh?", 1, 6, false)
        };

        Deck careerDeck = new Deck("Career Deck");
        for(Card career : careers) {
            careerDeck.addCard(career);
        }

        careerDeck.shuffle();

        return careerDeck;
    }

    /**
     * Generates Salary Deck which consists of Salary Cards
     * with different salary value which is a multiple of 10000
     * @return the generated and shuffled Deck which consists of SalaryCard
     * @see Deck
     * @see SalaryCard
     */
    public static Deck generateSalaryDeck() {
        Card[] salaryCards = {
                new SalaryCard(10000),
                new SalaryCard(5000),
                new SalaryCard(2500),
                new SalaryCard(2000),
                new SalaryCard(1250),
                new SalaryCard(1000),
                new SalaryCard(625),
                new SalaryCard(500),
                new SalaryCard(400),
                new SalaryCard(250),
        };

        Deck salaryDeck = new Deck("Salary Deck");

        for(Card salaryCard : salaryCards) {
            salaryDeck.addCard(salaryCard);
        }

        salaryDeck.shuffle();

        return salaryDeck;
    }

    /**
     * Generates a Deck to be used when picking a Blue Card whenever you land on Blue Space
     * @param numberOfPlayers the number of players currently in the game (to be passed unto WorldCupCard)
     * @return the generated and shuffled Deck for Blue Space
     * @see Deck
     * @see BlueCard
     */
    public static Deck generateBlueDeck(int numberOfPlayers) {
        String[] careers =  {
                "Lawyer", "Accountant", "Comp. Consultant", "Doctor", "Server", "Racecar Driver", "Athlete"
        };

        Deck blueDeck = new Deck("Blue Deck");

        for(int i = 0; i < 50; i++) {
            int randomNumber = RandomUtil.chooseRandomNumber(1, 7);
            String career = careers[i % 7];
            switch(randomNumber) {
                case 1:
                    blueDeck.addCard(new LawsuitCard(career, RandomUtil.chooseRandomNumber(1, 4)));
                    break;
                case 2:
                    blueDeck.addCard(new SalaryTaxDueCard(career));
                    break;
                case 3:
                    blueDeck.addCard(new TipTheServerCard(career));
                    break;
                case 4:
                    blueDeck.addCard(new SkiAccidentCard(career));
                    break;
                case 5:
                    blueDeck.addCard(new ComputerRepairCard(career));
                    break;
                case 6:
                    blueDeck.addCard(new WorldCupCard(career, numberOfPlayers));
                    break;
                case 7:
                    blueDeck.addCard(new F1RaceCard(career));
                    break;
                default:
                    // if value wasn't in the intended values (which is improbable)
                    // just continue/proceed to next loop without incrementing i
                    continue;
            }
        }

        blueDeck.shuffle();

        return blueDeck;
    }
}
