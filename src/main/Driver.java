package main;

import main.cards.ActionCard.ActionCard;
import main.cards.CareerCard.CareerCard;
import main.cards.SalaryCard.SalaryCard;
import main.decks.Deck;
import main.players.Player;
import main.utilities.InputUtil;

import java.util.ArrayList;

public class Driver {
    public static void main(String[] args) {
        Deck careerDeck = Generator.generateCareerDeck();
        Deck salaryDeck = Generator.generateSalaryDeck();

        // asks the user for the number of players (makes sures that the input is only integers {1, 2, 3})
        int nPlayers = InputUtil.scanInt("Enter number of Players: ", 1, 3);

        ArrayList<Player> players = new ArrayList<Player>();
        for(int i = 0; i < nPlayers; i++) {
            CareerCard careerCard = (CareerCard) careerDeck.pickTopCard();
            SalaryCard salaryCard = (SalaryCard) salaryDeck.pickTopCard();
            players.add(new Player("P" + (i + 1), careerCard, salaryCard));
        }

        Deck orangeDeck = Generator.generateOrangeDeck();
        int turn = 0;
//        Deck blueDeck = Generator.generateBlueDeck(nPlayers);
        while(orangeDeck.hasCard()) {
            Player currentPlayer = players.get(turn);
            ArrayList<Player> otherPlayers = new ArrayList<Player>();

            for(Player player : players) {
                if(!player.equals(currentPlayer)) {
                    otherPlayers.add(player);
                }
            }

            System.out.println(currentPlayer.getName() + "'s turn: ");

            ActionCard currentCard = (ActionCard) orangeDeck.pickTopCard();
            currentCard.displayCard();
            currentCard.activate(currentPlayer, otherPlayers.toArray(new Player[0]));
            currentPlayer.displayPlayerStats();

            InputUtil.waitForAnyKey();

            turn++;
            if(turn == players.size()) turn = 0;

        }
    }


}
