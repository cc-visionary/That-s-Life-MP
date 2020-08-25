package main;
import resources.cards.*;
import resources.cards.ActionCard.ActionCard;
import resources.cards.CareerCard.CareerCard;
import resources.cards.SalaryCard.SalaryCard;

public class Main {
    public static void main(String[] args) {
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

    public void generateActionCards() {
        // Collect from the Bank
        Card taxRefund = new ActionCard("Tax Refund", "You got a tax refund! Collect from the bank!", "RECEIVE", "BANK", 200);
        Card sellAnItem = new ActionCard("Sell an Item", "You sold an item! Collect from the bank!", "RECEIVE", "BANK", 150);
        Card bonusPayday = new ActionCard("Bonus Payday", "You had a bonus payday! Collect from the bank!", "RECEIVE", "BANK", 400);
        Card setupSchool = new ActionCard("Setup School", "Collect from the bank!", "RECEIVE", "BANK", 1000);

        // Pay the Bank
        Card buyAnItem = new ActionCard("Buy an Item", "You bought an item! You need to pay the bank!", "PAY","BANK", 400);

        // Pay the Player
        Card lawsuit = new ActionCard("Lawsuit", "A Lawsuit has been filed on you! Pay the other player!", "PAY", "SINGLE", 200);
        Card christmasBonus = new ActionCard("Christmas Bonus", "It's Christmas time! Time for you to pay all the other players!", "PAY", "ALL", 100);

        // Collect from Player
        Card fileALawsuit = new ActionCard("File a Lawsuit", "You're lawsuit was successful! You'll receive money from the other player!", "RECEIVE", "SINGLE", 400);
        Card itsYourBirthday = new ActionCard("It's Your Birthday", "Happy Birthday! All the other players gave you money as a gift!", "RECEIVE", "ALL", 200);
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
