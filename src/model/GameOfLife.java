package model;

import gui.stats.UpdateStats.UpdateStats;
import model.Decks.Deck;
import model.Decks.DeckWithUsed;
import model.Paths.Path;
import model.Players.Player;

import java.util.ArrayList;

/**
 * <h1>Game of Life Class</h1>
 * <p>Represents the Game of Life itself. Handles everything has functions and
 * attributes needed for the game such as Decks, Paths, Players, etc.</p>
 */
public class GameOfLife {
    private Deck careerDeck, salaryDeck, blueDeck, houseDeck;
    private DeckWithUsed orangeDeck;
    private Path careerPath, collegePath;
    private Player players[];
    private int nPlayers, turn;
    private static int round;
    private static ArrayList<String> roundStats;

    /**
     * All of the Game pieces will be Generated in the Board such as the
     * Deck(containing Card), Paths/Board, and Players.
     */
    public GameOfLife() {
        this.roundStats = new ArrayList<String>();
    }

    public void startGame(int nPlayers, int startingMoney) {
        // generate Decks
        careerDeck = Generator.generateCareerDeck();
        salaryDeck = Generator.generateSalaryDeck();
        orangeDeck = Generator.generateOrangeDeck();
        blueDeck = Generator.generateBlueDeck(nPlayers);
        houseDeck = Generator.generateHouseDeck();

        // generate Starting Paths
        Path paths[] = Generator.generateBoard();
        careerPath = paths[0];
        collegePath = paths[1];

        // generate Players
        players = Generator.generatePlayers(nPlayers, startingMoney, careerDeck, salaryDeck, careerPath, collegePath);
        this.nPlayers = nPlayers;

        turn = 0;
        round = 1;

        this.roundStats = new ArrayList<String>();
    }

    /**
     * Does all the things needed to be done to each Player when Game ends.
     * Then display each Player's Stats
     */
    public void endGame() {
        for(Player player : getAllPlayers()) {
            // collect $10000 from the bank for each child
            player.addBalance(player.getNBabies() * 10000);

            // sell house to the bank
            if(player.getHouseCard() != null) {
                player.addBalance(player.getHouseCard().getCost());
                player.setHouseCard(null);
            }

            // repay all loans to the bank
            if(player.getDebt() > 0) player.payDebt(player.getNBankLoan());
        }
    }

    public static void addRoundStat(String stat) {
        UpdateStats.showUpdate(stat);
        roundStats.add(stat);
    }

    public static int getRound() {
        return round;
    }

    public static ArrayList<String> getRoundStats() {
        return roundStats;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public Deck getSalaryDeck() {
        return salaryDeck;
    }

    public Deck getCareerDeck() {
        return careerDeck;
    }

    public Deck getOrangeDeck() {
        return orangeDeck;
    }

    public Deck getBlueDeck() {
        return blueDeck;
    }

    public Deck getHouseDeck() {
        return houseDeck;
    }

    public Path getCareerPath() {
        return careerPath;
    }

    public Path getCollegePath() {
        return collegePath;
    }

    /**
     * Returns the current Player based on the Player's turn
     * @return the Player object who has the current turn
     */
    public Player getCurrentPlayer() {
        return players[Math.max(getTurn(), 0)];
    }

    /**
     * The other players besides the current Player
     * @return the Array of Other Players
     */
    public Player[] getOtherPlayers() {
        ArrayList<Player> otherPlayers = new ArrayList<Player>();

        for(Player player : players) {
            if(!player.equals(getCurrentPlayer())) otherPlayers.add(player);
        }

        return otherPlayers.toArray(new Player[0]);
    }

    /**
     * All the Player in the BoardGame
     * @return all the player
     */
    public Player[] getAllPlayers() {
        return players;
    }

    public int getNPlayers() {
        return nPlayers;
    }

    public int getTurn() {
        return turn;
    }

    /**
     * Determines whether or not there is already a winner in between all of the players
     * @return boolean value true or false to determine if there is a winner or not
     */
    public boolean hasWinner() {
        for(Player player : players) {
            if(player.isRetired()) {
                return true;
            }
        }
        return false;
    }
}
