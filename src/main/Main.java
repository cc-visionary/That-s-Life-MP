package main;

import resources.players.Player;
import resources.decks.Deck;
import resources.cards.Card;
import resources.cards.ActionCard.*;
import resources.cards.CareerCard.CareerCard;
import resources.cards.SalaryCard.SalaryCard;
import resources.cards.BlueCard.*;
import resources.paths.Path;
import resources.spaces.Space;
import resources.spaces.OrangeSpace.OrangeSpace;
import resources.spaces.BlueSpace.BlueSpace;
import resources.spaces.GreenSpace.*;
import resources.spaces.MagentaSpace.*;

public class Main {
    public static void main(String[] args) {

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

    public Deck generateOrangeDeck() {
        // Collect from the Bank
        Card taxRefund = new CollectBankCard("Tax Refund", "You got a tax refund! Collect from the bank!", 00);
        Card sellAnItem = new CollectBankCard("Sell an Item", "You sold an item! Collect from the bank!", 150);
        Card bonusPayday = new CollectBankCard("Bonus Payday", "You had a bonus payday! Collect from the bank!", 400);
        Card setupSchool = new CollectBankCard("Setup School", "The school you setup was successful! Collect from the bank!", 1000);
        Card writeABook = new CollectBankCard("Write a Book", "The book you wrote became a best-seller! Collect from  the Bank!", 1000);

        // Pay the Bank
        Card buyAnItem = new PayBankCard("Buy an Item", "You bought an item! You need to pay the bank!", 400);
        Card visitAPlace = new PayBankCard("Visit a Place", "You visited a place! You need to pay the bank!", 200);
        Card hiking = new PayBankCard("Hiking", "You went hiking! You enjoyed it, but now it's time to pay the bank!", 100);
        Card watchAShow = new PayBankCard("Watch a Show", "You watched a show! You need to pay the bank!", 125);
        Card trafficViolation = new PayBankCard("Traffic Violation", "You crossed the while it was red on the traffic light. You need to pay the bank!", 100);

        // Pay the Player
        Card lawsuit = new PayPlayerCard("Lawsuit", "A Lawsuit has been filed on you! Pay the other player!", 200);
        Card christmasBonus = new PayAllCard("Christmas Bonus", "It's Christmas time! Time for you to pay all the other players!", 100);

        // Collect from Players
        Card fileALawsuit = new CollectPlayerCard("File a Lawsuit", "You're lawsuit was successful! You'll receive money from the other player!", 400);
        Card itsYourBirthday = new CollectAllCard("It's Your Birthday", "Happy Birthday! All the other players gave you money as a gift!", 200);

        Deck orangeDeck = new Deck("Orange Deck");

        Card[] actionCards = {};

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
