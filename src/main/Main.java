package main;
import resources.players.*;
import resources.cards.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Card lawsuit = new BlueCard("Lawsuit", "PAY", 10000);
        lawsuit.displayCard();

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
}
