package main;

import main.cards.ActionCard.*;
import main.cards.Card;
import main.cards.CareerCard.CareerCard;
import main.cards.SalaryCard.SalaryCard;
import main.decks.Deck;
import main.utilities.RandomUtil;

public class Generator {
    /**
     * Generates the Deck for Orange Space
     * @return the generated Deck for Orange Space
     */
    public static Deck generateOrangeDeck() {
        // Collect from the Bank (40% - 20 cards)
        Card[] collectBankCards = {
                new CollectBankCard("Tax Refund", "You got a tax refund! Collect from the bank!", 00),
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

//        orangeDeck.displayDeck();
        orangeDeck.shuffle();
//        orangeDeck.displayDeck();

        return orangeDeck;
    }

    public static Deck generateCareerDeck() {
        Card[] careers = {
                new CareerCard("Lawyer", "A lawyer who makes law to create order in the world.", 5, 8, true),
                new CareerCard("Accountant", "", 4, 7, true),
                new CareerCard("Computer Consultant", "", 3, 7, true),
                new CareerCard("Doctor", "", 5, 8, true),
                new CareerCard("Server", "", 1, 4, false),
                new CareerCard("Racecar Driver", "Broom Broom!", 2, 8, false),
                new CareerCard("Athlete", "You're a Sporty person huh?", 1, 6, false)
        };

        Deck careerDeck = new Deck("Career Deck");
        for(Card career : careers) {
            careerDeck.addCard(career);
        }

//        careerDeck.displayDeck();
        careerDeck.shuffle();
//        careerDeck.displayDeck();

        return careerDeck;
    }

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

//        salaryDeck.displayDeck();
        salaryDeck.shuffle();
//        salaryDeck.displayDeck();

        return salaryDeck;
    }

    public static Deck generateBlueDeck() {

        Deck blueDeck = new Deck("Blue Deck");

        return blueDeck;
    }
}
