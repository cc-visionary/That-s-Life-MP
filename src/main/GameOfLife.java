package main;

import gui.stats.RoundStats.RoundStatsController;
import gui.stats.ScreenStats.ScreenStatsController;
import gui.choose.ChooseCard;
import gui.choose.ChooseMove;
import gui.choose.ChoosePath;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.cards.ActionCard.ActionCard;
import main.cards.BlueCard.BlueCard;
import main.cards.CareerCard.CareerCard;
import main.cards.HouseCard.HouseCard;
import main.cards.SalaryCard.SalaryCard;
import main.decks.Deck;
import main.decks.DeckWithUsed;
import main.paths.Path;
import main.players.Player;
import main.spaces.BlueSpace.BlueSpace;
import main.spaces.GreenSpace.PayDaySpace;
import main.spaces.GreenSpace.PayRaiseSpace;
import main.spaces.MagentaSpace.*;
import main.spaces.OrangeSpace.OrangeSpace;
import main.spaces.Space;
import main.utilities.InputUtil;

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
    public GameOfLife(ScreenStatsController screenStats) {
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

        // screen stats
        this.screenStats = screenStats;

        this.roundStats = new ArrayList<String>();
    }

    public GameOfLife() {
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
            ChoosePath choosePath = new ChoosePath();
            choosePath.choosePath(careerPath, collegePath);
            Path chosenPath = choosePath.getChosenPath();

            // @TODO only choose the career card which doesn't need a graduation since the player isn't graduated yet
            if(chosenPath.getName() == "Career Path") {
                getCurrentPlayer().setCareerCard((CareerCard) careerDeck.pickTopCard());
                getCurrentPlayer().setSalaryCard((SalaryCard) salaryDeck.pickTopCard());
            }
            getCurrentPlayer().setPath(chosenPath);
            getCurrentPlayer().getPath().getSpaces()[0].addPlayer(getCurrentPlayer());
        }
        this.screenStats.updateStats(getCurrentPlayer());
        ChooseMove chooseMove = new ChooseMove();
        chooseMove.chooseMove(this);

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
     * The amount of dice rolled by the Player
     * @param rolledDice dice rolled
     */
    public void movePlayer(int rolledDice) {
        Player currentPlayer = getCurrentPlayer();
        Space previousSpace = currentPlayer.getPath().getSpaces()[currentPlayer.getLocation()], spaceLanded = null;

        // remove player from that space
        if(previousSpace.getPlayers().contains(currentPlayer))
            previousSpace.removePlayer(currentPlayer);

        for(int i = 0; i < rolledDice; i++) {
            currentPlayer.addLocation();
            spaceLanded = currentPlayer.getPath().getSpaces()[currentPlayer.getLocation()];
            System.out.println(spaceLanded.getType() + " - " + spaceLanded.getName());
            if(spaceLanded.getType().equals(Constants.MAGENTA_SPACE) || spaceLanded.getType().equals(Constants.RETIREMENT_SPACE)) break; // if player reaches the Magenta Space stop
        }

        handleSpaceLanded(spaceLanded);

        // add player to that space
        spaceLanded.addPlayer(currentPlayer);
    }

    /**
     * Handles the Actions to be done on where the space the Player landed
     * @param space space where the currentPlayer landed
     */
    public void handleSpaceLanded(Space space) {
        System.out.println(String.format("You landed on %s - %s", space.getType(), space.getName()));
        Player currentPlayer = getCurrentPlayer();
        if(space != null) {
            if(space.getType().equals(Constants.BLUE_SPACE)) {
                // picks an blue card and activate it for all the Players
                BlueCard blueCard = ((BlueSpace) space).pickCard(getBlueDeck());
                GameOfLife.addRoundStat(String.format("%s drew %s(%s)", getCurrentPlayer().getName(), blueCard.getName(), blueCard.getType()));
                blueCard.setOwner(currentPlayer);
                blueCard.setOtherPlayers(getOtherPlayers());
                ChooseCard.displayCard(blueCard);
                blueCard.activate();

                // put the card back to the BlueDeck
                blueCard.setOwner(null);
                blueCard.setOtherPlayers(null);
                getBlueDeck().addCard(blueCard);
                getBlueDeck().shuffle();
            } else if(space.getType().equals(Constants.ORANGE_SPACE)) {
                // picks an action card and activate it for all the Players
                ActionCard actionCard = ((OrangeSpace) space).pickCard(getOrangeDeck());
                GameOfLife.addRoundStat(String.format("%s drew %s(%s)", getCurrentPlayer().getName(), actionCard.getName(), actionCard.getType()));
                actionCard.setOwner(currentPlayer);
                actionCard.setOtherPlayers(getOtherPlayers());
                ChooseCard.displayCard(actionCard);
                actionCard.activate();
            } else if(space.getType().equals(Constants.GREEN_SPACE)) {
                if(space.getName().equals(Constants.PAY_DAY)) {
                    // gives the salary to the player
                    ((PayDaySpace) space).giveSalary(currentPlayer);
                } else if(space.getName().equals(Constants.PAY_RAISE)) {
                    // raises the salary of the player
                    ((PayRaiseSpace) space).raiseSalary(currentPlayer);
                }
            } else if(space.getType().equals(Constants.MAGENTA_SPACE)) {
                if(space.getName().equals(Constants.BUY_A_HOUSE)) {
                    HouseCard houseCard = ((BuyAHouseSpace) space).pickCard(houseDeck);
                    if(currentPlayer.getBalance() >= houseCard.getCost()) {
                        // if balance is greater than the cost of the house,
                        // the Player will automatically purchase it.
                        System.out.println(String.format("%s successfully purchased %s for $%.2f", currentPlayer.getName(), houseCard.getName(), houseCard.getCost()));
                        houseCard.setOwner(getCurrentPlayer());
                        getCurrentPlayer().setHouseCard(houseCard);
                    } else {
                        // if not, then the player will be given an option to take a loan
                        int choice = InputUtil.scanInt("Do you want to make a loan? (1-Yes, 2-No)", 1, 2);
                        switch(choice) {
                            case 1:
                                // player makes a loan from the bank
                                currentPlayer.bankLoan((int) Math.ceil((houseCard.getCost() - currentPlayer.getBalance()) / 20000));
                                System.out.println(String.format("%s successfully purchased %s for $%.2f", currentPlayer.getName(), houseCard.getName(), houseCard.getCost()));
                                houseCard.setOwner(getCurrentPlayer());
                                getCurrentPlayer().setHouseCard(houseCard);
                                break;
                            case 2:
                                // if player won't make a loan, add the card back to the Deck
                                houseDeck.addCard(houseCard);
                                houseDeck.shuffle();
                                break;
                        }
                    }
                } else if(space.getName().equals(Constants.JOB_SEARCH)) {
                    CareerCard careerCard = ((JobSearchSpace) space).getCareerCard(getCareerDeck());
                    SalaryCard salaryCard = ((JobSearchSpace) space).getSalaryCard(getSalaryDeck());
                    if(careerCard != null) {
                        // returns the Player's Career Card to the Deck
                        if(currentPlayer.getCareerCard() != null) {
                            careerDeck.addCard(currentPlayer.getCareerCard());
                            careerDeck.shuffle();
                        }
                        // then set a new Career Card for the Player
                        currentPlayer.setCareerCard(careerCard);
                    }
                    if(salaryCard != null) {
                        // returns the Player's Salary Card to the Deck
                        if(currentPlayer.getSalaryCard() != null) {
                            salaryDeck.addCard(currentPlayer.getSalaryCard());
                            salaryDeck.shuffle();
                        }
                        // then set a new Salary Card for the Player
                        currentPlayer.setSalaryCard(salaryCard);
                    }
                } else if(space.getName().equals(Constants.COLLEGE_CARREER_CHOICE)) {
                    CareerCard careerCard = ((CollegeCareerChoiceSpace) space).chooseCareerCard(getCareerDeck());
                    SalaryCard salaryCard = ((CollegeCareerChoiceSpace) space).chooseSalaryCard(getSalaryDeck());

                    // returns the Player's Career Card to the Deck
                    if(currentPlayer.getCareerCard() != null) {
                        careerDeck.addCard(currentPlayer.getCareerCard());
                        careerDeck.shuffle();
                    }
                    // then set a new Career Card for the Player
                    currentPlayer.setCareerCard(careerCard);

                    // returns the Player's Salary Card to the Deck
                    if(currentPlayer.getSalaryCard() != null) {
                        salaryDeck.addCard(currentPlayer.getSalaryCard());
                        salaryDeck.shuffle();
                    }
                    // then set a new Salary Card for the Player
                    currentPlayer.setSalaryCard(salaryCard);
                } else if(space.getName().equals(Constants.GET_MARRIED)) {
                    // Player marries when landing in Get Married Space
                    ((GetMarriedSpace) space).getMarried(getCurrentPlayer(), getOtherPlayers());
                } else if(space.getName().equals("Have a Baby") || space.getName().equals("Have a Twin") || space.getName().equals("Have a Triplet")) {
                    // Player haves a baby when landing on Have a Baby Space
                    ((HaveBabySpace) space).haveABaby(getCurrentPlayer());
                } else if(space.getName().equals(Constants.WHICH_PATH)) {
                    // this is the junction where Players can choose the next Path
                    ((WhichPathSpace) currentPlayer.getPath().getJunction()).choosePath(currentPlayer);
                    ChooseMove chooseMove = new ChooseMove();
                    chooseMove.chooseMove(this);
                }
            } else if(space.getType().equals(Constants.RETIREMENT_SPACE)) {
                // Space where Player retires
                getCurrentPlayer().setIsRetired(true);
            }
        } else {
            System.out.println("Space is null...");
        }
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

//        displayStats();
    }

    public static void addRoundStat(String stat) {
        roundStats.add(stat);
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
