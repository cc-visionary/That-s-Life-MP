package model;

import gui.modals.Modal;
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
    private ArrayList<Player> activePlayers;
    private ArrayList<Player> retiredPlayers;
    private int nPlayers, turn;
    private static int round;
    private static ArrayList<String> roundStats;

    /**
     * All of the Game pieces will be Generated in the Board such as the
     * Deck(containing Card), Paths/Board, and Players.
     */
    public GameOfLife(int nPlayers, int startingMoney) {
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
        activePlayers = Generator.generatePlayers(nPlayers, startingMoney);
        retiredPlayers = new ArrayList<Player>();
        this.nPlayers = nPlayers;

        turn = 0;
        round = 1;

        this.roundStats = new ArrayList<String>();
    }

    /**
     * Adds an Update for the Round Stats
     * @param stat string update to be added
     */
    public static void addRoundStat(String stat) {
        new Modal().showUpdate(stat);
        roundStats.add(stat);
    }

    /**
     * Transfers a Player from active to retired
     * @param player
     */
    public void retirePlayer(Player player) {
        activePlayers.remove(player);
        retiredPlayers.add(player);
    }

    public int getNActivePlayers() {
        return activePlayers.size();
    }

    public int getNRetiredPlayers() {
        return retiredPlayers.size();
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
        return activePlayers.get(Math.max(getTurn(), 0));
    }

    /**
     * The other players besides the current Player
     * @return the Array of Other Players
     */
    public Player[] getOtherPlayers() {
        ArrayList<Player> otherPlayers = new ArrayList<Player>();

        for(Player player : activePlayers) {
            if(!player.equals(getCurrentPlayer())) otherPlayers.add(player);
        }

        return otherPlayers.toArray(new Player[0]);
    }

    /**
     * All the Player in the BoardGame
     * @return all the player
     */
    public Player[] getAllActivePlayers() {
        return activePlayers.toArray(new Player[0]);
    }

    public Player[] getAllRetiredPlayers() {
        return retiredPlayers.toArray(new Player[0]);
    }

    public int getNPlayers() {
        return nPlayers;
    }

    public int getTurn() {
        return turn;
    }

    /**
     * Ends the game only if all the Players aren't active anymore
     * meaning that all the Players are already retired.
     *
     * @return boolean value true or false to determine whether the game has ended or not
     */
    public boolean hasEnded() {
        return getNActivePlayers() == 0;
    }
}
