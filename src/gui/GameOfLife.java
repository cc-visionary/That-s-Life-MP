package gui;

import gui.stats.RoundStats.RoundStatsController;
import gui.game.ScreenStats.ScreenStatsController;
import gui.choose.ChooseCard.ChooseCard;
import gui.stats.UpdateStats.UpdateStats;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Constants;
import model.Generator;
import model.cards.ActionCard.ActionCard;
import model.cards.BlueCard.BlueCard;
import model.cards.CareerCard.CareerCard;
import model.cards.HouseCard.HouseCard;
import model.cards.SalaryCard.SalaryCard;
import model.decks.Deck;
import model.decks.DeckWithUsed;
import model.paths.Path;
import model.players.Player;
import model.spaces.BlueSpace.BlueSpace;
import model.spaces.GreenSpace.PayDaySpace;
import model.spaces.GreenSpace.PayRaiseSpace;
import model.spaces.MagentaSpace.*;
import model.spaces.OrangeSpace.OrangeSpace;
import model.spaces.Space;
import utilities.InputUtil;

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
    private int nPlayers, turn, round;
    private ScreenStatsController screenStats;
    private static ArrayList<String> roundStats;

    /**
     * All of the Game pieces will be Generated in the Board such as the
     * Deck(containing Card), Paths/Board, and Players.
     */
    public GameOfLife() {
        // screen stats
//        this.screenStats = screenStats;

        this.roundStats = new ArrayList<String>();
    }

    public void startGame() {
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
        players = Generator.generatePlayers(careerDeck, salaryDeck, careerPath, collegePath);
        this.nPlayers = players.length;

        turn = 0;
        round = 1;

        this.roundStats = new ArrayList<String>();
    }

    /**
     * If invoked, will let the next Player choose a move and
     * automatically does everything needed to be done such as
     * doing Space actions, changing of Paths, etc.
     */
    public void nextPlayer() {
        // if player has no path, let's him choose one through GUI
        if(getCurrentPlayer().getPath() == null) {
//            ChoosePath choosePath = new ChoosePath();
//            choosePath.choosePath(careerPath, collegePath);
//            Path chosenPath = choosePath.getChosenPath();

            Path chosenPath = collegePath;

            if(chosenPath.getName() == "Career Path") {

            }

        }
//        this.screenStats.updateStats(getCurrentPlayer());
//        ChooseMove chooseMove = new ChooseMove();
//        chooseMove.chooseMove(this);

        turn++;

        if(turn == nPlayers) {
            turn = 0;
            // view round stats
            openRoundStats();
            round++;
            roundStats.clear();
        }
    }

    public void openRoundStats() {
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Round Stats");

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/stats/RoundStats/RoundStats.fxml"));

        try {
            ScrollPane pane = fxmlLoader.load();
            pane.setStyle("-fx-background-color: aliceblue; -fx-font-size: 16; -fx-border-width: 1; -fx-border-color: gray");
            ((RoundStatsController) fxmlLoader.getController()).setList(getRoundStats());
            stage.setScene(new Scene(pane));
        } catch(Exception e) {
            e.printStackTrace();
        }

        stage.showAndWait();
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
            player.payDebt(player.getNBankLoan());
        }
    }

    public static void addRoundStat(String stat) {
        UpdateStats.showUpdate(stat);
        roundStats.add(stat);
    }

    public ScreenStatsController getScreenStats() {
        return screenStats;
    }

    public static String[] getRoundStats() {
        return roundStats.toArray(new String[0]);
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
        return players[getTurn()];
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

    public int getRound() {
        return round;
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

//    public void displayStats() {
//        System.out.println("Round #" + getRound() + ":");
//        for(Player player : getAllPlayers()) player.displayPlayerStats();
//    }
//
//    public void displayWinner() {
//        for(Player player : players) {
//            if(player.isRetired()) {
//                System.out.println(player.getName() + " is the winner!");
//                player.displayPlayerStats();
//            }
//        }
//    }
}
