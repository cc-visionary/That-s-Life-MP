package main;

import resources.decks.Deck;
import resources.cards.Card;
import resources.cards.ActionCard.*;
import resources.cards.CareerCard.CareerCard;
import resources.cards.SalaryCard.SalaryCard;
import resources.utilities.RandomUtil;

public class Main {
    public static void main(String[] args) {
        Deck orangeDeck = generateOrangeDeck();

//        final String[] careers = {"Lawyer", "Accountant", "Computer Consultant", "Doctor", "Server", "Racecar Driver", "Athlete"};

//        Player p1 = new Player();
//        Scanner scanner = new Scanner(System.in);
//        // asks the user for the number of players (makes sures that the input is only integers {1, 2, 3})
//        int nPlayers = 0;
//        do {
//            System.out.print("Enter the number of players: ");
//            if (scanner.hasNextInt())
//                nPlayers = scanner.nextInt();
//            else {
//                System.out.println("Invalid input. Please enter a number from 1-3...\n");
//                scanner.next();
//                continue;
//            }
//        } while(nPlayers < 1 || nPlayers > 3);
//
//        ArrayList<Player> players = new ArrayList<Player>();
//        String garbage = scanner.nextLine();
//        for(int i = 0; i < nPlayers; i++) {
//            String name;
//            System.out.print("Input the name of player " + (i + 1) + ": ");
//            name = scanner.nextLine();
//            players.add(new Player(name));
//        }
//
//        for(Player player : players) {
//            System.out.println(player.getName());
//        }
//
//        System.out.println(Player.getPlayerCount());
    }

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

        orangeDeck.displayDeck();
        orangeDeck.shuffle();
        orangeDeck.displayDeck();

        return orangeDeck;
    }

    public void generateCareerCards() {
        Card lawyer = new CareerCard("Lawyer", "A lawyer who makes law to create order in the world.", 5, 8, true);
        lawyer.displayCard();
    }

    public void generateSalaryCards() {
        Card salaryCard1 = new SalaryCard(10000, 1000);
        salaryCard1.displayCard();
    }

    public void generateBlueCards() {

    }
}
