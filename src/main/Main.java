package main;
import resources.players.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of players: ");
        int nPlayers = scanner.nextInt();

        ArrayList<Player> players = new ArrayList<Player>();
        String _ = scanner.nextLine();
        for(int i = 0; i < nPlayers; i++) {
            String name;
            System.out.print("Input the name of player " + (i + 1) + ": ");
            name = scanner.nextLine();
            players.add(new Player(name));
        }

        for(Player player : players) {
            System.out.println(player.getName());
        }
    }
}
