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
        // Collect from the Bank
        Card taxRefund = new CollectBankCard("Tax Refund", "You got a tax refund! Collect from the bank!", 00);
        Card sellAnItem = new CollectBankCard("Sell an Item", "You sold an item! Collect from the bank!", 150);
        Card bonusPayday = new CollectBankCard("Bonus Payday", "You had a bonus payday! Collect from the bank!", 400);
        Card setupSchool = new CollectBankCard("Setup School", "The school you setup was successful! Collect from the bank!", 1000);
        Card writeABook = new CollectBankCard("Write a Book", "The book you wrote became a best-seller! Collect from  the Bank!", 1000);
        Card[] collectBankCards = {taxRefund, sellAnItem, bonusPayday, setupSchool, writeABook}; // 40% - 20 cards

        // Pay the Bank
        Card buyAnItem = new PayBankCard("Buy an Item", "You bought an item! You need to pay the bank!", 400);
        Card visitAPlace = new PayBankCard("Visit a Place", "You visited a place! You need to pay the bank!", 200);
        Card hiking = new PayBankCard("Hiking", "You went hiking! You enjoyed it, but now it's time to pay the bank!", 100);
        Card watchAShow = new PayBankCard("Watch a Show", "You watched a show! You need to pay the bank!", 125);
        Card trafficViolation = new PayBankCard("Traffic Violation", "You crossed the while it was red on the traffic light. You need to pay the bank!", 100);
        Card[] payBankCards = {buyAnItem, visitAPlace, hiking, watchAShow, trafficViolation}; // 40% - 20 cards

        // Pay the Player
        Card lawsuit = new PayPlayerCard("Lawsuit", "A Lawsuit has been filed on you! Pay the other player!", 200);
        Card christmasBonus = new PayAllCard("Christmas Bonus", "It's Christmas time! Time for you to pay all the other players!", 100);
        Card[] payPlayerCards = {lawsuit, christmasBonus}; // 10% - 5 cards

        // Collect from Players
        Card fileALawsuit = new CollectPlayerCard("File a Lawsuit", "You're lawsuit was successful! You'll receive money from the other player!", 400);
        Card itsYourBirthday = new CollectAllCard("It's Your Birthday", "Happy Birthday! All the other players gave you money as a gift!", 200);
        Card[] collectPlayerCards = {fileALawsuit, itsYourBirthday}; // 10% - 5 cards

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
        Card lawyer = new CareerCard("Lawyer", "A lawyer who makes law to create order in the world.", 5, 8, true);
        Card accountant = new CareerCard("Accountant", "", 4, 7, true);
        Card computerConsultant = new CareerCard("Computer Consultant", "", 3, 7, true);
        Card doctor = new CareerCard("Doctor", "", 5, 8, true);
        Card server = new CareerCard("Server", "", 1, 4, false);
        Card racecarDriver = new CareerCard("Racecar Driver", "Broom Broom!", 2, 8, false);
        Card athlete = new CareerCard("Athlete", "You're a Sporty person huh?", 1, 6, false);

        Deck careerDeck = new Deck("Career Deck");
        careerDeck.addCard(lawyer);
        careerDeck.addCard(accountant);
        careerDeck.addCard(computerConsultant);
        careerDeck.addCard(doctor);
        careerDeck.addCard(server);
        careerDeck.addCard(racecarDriver);
        careerDeck.addCard(athlete);

        careerDeck.displayDeck();
        careerDeck.shuffle();
        careerDeck.displayDeck();

        return careerDeck;
    }

    public static Deck generateSalaryDeck() {
        Card salaryCard1 = new SalaryCard(10000, 1000);
        salaryCard1.displayCard();

        Deck salaryDeck = new Deck("Salary Deck");

        return salaryDeck;
    }

    public static Deck generateBlueDeck() {

        Deck blueDeck = new Deck("Blue Deck");

        return blueDeck;
    }
}
