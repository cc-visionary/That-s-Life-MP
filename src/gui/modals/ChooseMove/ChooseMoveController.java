package gui.modals.ChooseMove;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import model.GameOfLife;
import gui.Game.GameController;
import gui.modals.Modal;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.Constants;
import model.Cards.ActionCard.ActionCard;
import model.Cards.BlueCard.BlueCard;
import model.Cards.CareerCard.CareerCard;
import model.Cards.HouseCard.HouseCard;
import model.Cards.SalaryCard.SalaryCard;
import model.Players.Player;
import model.Spaces.BlueSpace.BlueSpace;
import model.Spaces.GreenSpace.PayDaySpace;
import model.Spaces.GreenSpace.PayRaiseSpace;
import model.Spaces.MagentaSpace.*;
import model.Spaces.OrangeSpace.OrangeSpace;
import model.Spaces.RetirementSpace.RetirementSpace;
import model.Spaces.Space;

/**
 * Controls the Choose Move UI (ChooseMove.fxml)
 */

public class ChooseMoveController {
    @FXML
    private Button rollDice, viewPlayerStats, payDebt;

    /**
     * Set the values for the Choose Move options
     * @param gameOfLife main model
     * @param gameScreenController controller used to update screen stats
     */
    public void setGameOfLife(GameOfLife gameOfLife, GameController gameScreenController) {
        Player currentPlayer = gameOfLife.getCurrentPlayer();

        // let's the player roll a dice
        rollDice.setOnAction(e -> {
            MediaPlayer mediaPlayer = new MediaPlayer(new Media(getClass().getResource("/audio/click.wav").toString()));
            mediaPlayer.play();

            int rolledDice = currentPlayer.rollDice();
            Space previousSpace = currentPlayer.getPath().getSpaces()[Math.max(currentPlayer.getLocation(), 0)], spaceLanded = null;

            // remove player from that space
            if(previousSpace.getPlayers().contains(currentPlayer))
                previousSpace.removePlayer(currentPlayer);

            for(int i = 0; i < rolledDice; i++) {
                currentPlayer.addLocation();
                spaceLanded = currentPlayer.getPath().getSpaces()[currentPlayer.getLocation()];
                // the not equal to retirement space simply means that if the space landed is at retirement space,
                // we don't set the path anymore even if its at the last index of the Path
                if(currentPlayer.getPath().getNSpaces() - 1 == currentPlayer.getLocation() && !spaceLanded.getType().equals(Constants.RETIREMENT_SPACE)) {
                    if(currentPlayer.getPath().getPath2() == null) {
                        spaceLanded.removePlayer(currentPlayer);
                        currentPlayer.setPath(currentPlayer.getPath().getPath1());

                        Space newSpaceLanded = currentPlayer.getPath().getSpaces()[currentPlayer.getLocation()];
                        newSpaceLanded.removePlayer(currentPlayer);
                        currentPlayer.setLocation(-1);
                    }
                }
                if(spaceLanded.getType().equals(Constants.MAGENTA_SPACE) || spaceLanded.getType().equals(Constants.RETIREMENT_SPACE)) break; // if player reaches the Magenta Space stop
            }

            GameOfLife.addRoundStat(String.format("%s landed on %s", currentPlayer.getName(), String.format("%s%s", spaceLanded.getType(), spaceLanded.getName() != null ? "(" + spaceLanded.getName() + ")" : "")));

            spaceLanded.addPlayer(currentPlayer);
            gameScreenController.refreshGameScreen();

            handleSpaceLanded(gameOfLife, spaceLanded, gameScreenController);
            if(gameOfLife.getNActivePlayers() != 0) gameScreenController.refreshGameScreen();

            ((Stage)((Node) e.getSource()).getScene().getWindow()).close();
        });

        // lets the player view his own stat
        viewPlayerStats.setOnAction(e -> {
            MediaPlayer mediaPlayer = new MediaPlayer(new Media(getClass().getResource("/audio/click.wav").toString()));
            mediaPlayer.play();

            new Modal().showPlayerStats(gameOfLife.getCurrentPlayer());
        });

        // lets the player pay he's debt
        payDebt.setOnAction(e -> {
            AudioClip audioPlayer = new AudioClip(new Media(getClass().getResource("/audio/click.wav").toString()).getSource());
            audioPlayer.play();

            new Modal().payDebt(currentPlayer);
            gameScreenController.refreshGameScreen();
            if(currentPlayer.getDebt() <= 0) payDebt.setDisable(true);
        });

        if(currentPlayer.getDebt() <= 0) payDebt.setDisable(true);
    }

    /**
     * Handles the Actions to be done on where the space the Player landed
     * @param gameOfLife           main model
     * @param space                space where the currentPlayer landed
     * @param gameScreenController controller to control the screen
     */
    public void handleSpaceLanded(GameOfLife gameOfLife, Space space, GameController gameScreenController) {
//        System.out.println(String.format("You landed on %s - %s", space.getType(), space.getName()));
        Player currentPlayer = gameOfLife.getCurrentPlayer();
        if(space != null) {
            if(space.getType().equals(Constants.BLUE_SPACE)) {
                // picks an blue card and activate it for all the Players
                BlueCard blueCard = ((BlueSpace) space).pickCard(gameOfLife.getBlueDeck());
                GameOfLife.addRoundStat(String.format("%s drew %s(%s)", currentPlayer.getName(), blueCard.getName(), blueCard.getType()));
                blueCard.setOwner(currentPlayer);
                blueCard.setOtherPlayers(gameOfLife.getOtherPlayers());
                new Modal().displayCard(blueCard);
                blueCard.activate();

                // put the card back to the BlueDeck
                blueCard.setOwner(null);
                blueCard.setOtherPlayers(null);
                gameOfLife.getBlueDeck().addCard(blueCard);
                gameOfLife.getBlueDeck().shuffle();
            } else if(space.getType().equals(Constants.ORANGE_SPACE)) {
                // picks an action card and activate it for all the Players
                ActionCard actionCard = ((OrangeSpace) space).pickCard(gameOfLife.getOrangeDeck());
                GameOfLife.addRoundStat(String.format("%s drew %s(%s)", currentPlayer.getName(), actionCard.getName(), actionCard.getType()));
                actionCard.setOwner(currentPlayer);
                actionCard.setOtherPlayers(gameOfLife.getOtherPlayers());
                new Modal().displayCard(actionCard);
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
                    HouseCard houseCard = ((BuyAHouseSpace) space).pickCard(gameOfLife.getHouseDeck());
                    if(currentPlayer.getBalance() >= houseCard.getCost()) {
                        // if balance is greater than the cost of the house,
                        // the Player will automatically purchase it.
                        currentPlayer.payBalance(houseCard.getCost());
                        GameOfLife.addRoundStat(String.format("%s successfully purchased %s for $%d", currentPlayer.getName(), houseCard.getName(), houseCard.getCost()));
                        houseCard.setOwner(currentPlayer);
                        currentPlayer.setHouseCard(houseCard);
                    } else {
                        // if not, then the player will be given an option to take a loan
                        boolean choice = new Modal().askYesNo("Bank Loan", "Do you want to make a loan?");
                        if(choice) {
                            // player makes a loan from the bank
                            currentPlayer.bankLoan((int) Math.ceil((houseCard.getCost() - currentPlayer.getBalance()) / 20000));
                            System.out.println(String.format("%s successfully purchased %s for $%.2f", currentPlayer.getName(), houseCard.getName(), houseCard.getCost()));
                            houseCard.setOwner(currentPlayer);
                            currentPlayer.setHouseCard(houseCard);
                        } else {
                            // if player won't make a loan, add the card back to the Deck
                            gameOfLife.getHouseDeck().addCard(houseCard);
                            gameOfLife.getHouseDeck().shuffle();
                        }
                    }
                } else if(space.getName().equals(Constants.JOB_SEARCH)) {
                    CareerCard careerCard = ((JobSearchSpace) space).getCareerCard(gameOfLife.getCareerDeck());
                    SalaryCard salaryCard = ((JobSearchSpace) space).getSalaryCard(gameOfLife.getSalaryDeck());
                    if(careerCard != null) {
                        // returns the Player's Career Card to the Deck
                        if(currentPlayer.getCareerCard() != null) {
                            gameOfLife.getCareerDeck().addCard(currentPlayer.getCareerCard());
                            gameOfLife.getCareerDeck().shuffle();
                        }
                        // then set a new Career Card for the Player
                        currentPlayer.setCareerCard(careerCard);
                    }
                    if(salaryCard != null) {
                        // returns the Player's Salary Card to the Deck
                        if(currentPlayer.getSalaryCard() != null) {
                            gameOfLife.getSalaryDeck().addCard(currentPlayer.getSalaryCard());
                            gameOfLife.getSalaryDeck().shuffle();
                        }
                        // then set a new Salary Card for the Player
                        currentPlayer.setSalaryCard(salaryCard);
                    }
                } else if(space.getName().equals(Constants.COLLEGE_CARREER_CHOICE)) {
                    // if the player is in College Path, he/she graduates on the College Career Space in a College Path
                    if(currentPlayer.getPath().getName().equals("College Path")) currentPlayer.graduate();

                    CareerCard careerCard = ((CollegeCareerChoiceSpace) space).chooseCareerCard(gameOfLife.getCareerDeck(), currentPlayer.isGraduated());
                    SalaryCard salaryCard = ((CollegeCareerChoiceSpace) space).chooseSalaryCard(gameOfLife.getSalaryDeck());

                    if(careerCard != null) {
                        // returns the Player's Career Card to the Deck
                        if(currentPlayer.getCareerCard() != null) {
                            gameOfLife.getCareerDeck().addCard(currentPlayer.getCareerCard());
                            gameOfLife.getCareerDeck().shuffle();
                        }
                        // then set a new Career Card for the Player
                        currentPlayer.setCareerCard(careerCard);
                    }

                    if(salaryCard != null) {
                        // returns the Player's Salary Card to the Deck
                        if(currentPlayer.getSalaryCard() != null) {
                            gameOfLife.getSalaryDeck().addCard(currentPlayer.getSalaryCard());
                            gameOfLife.getSalaryDeck().shuffle();
                        }
                        // then set a new Salary Card for the Player
                        currentPlayer.setSalaryCard(salaryCard);
                    }
                } else if(space.getName().equals(Constants.GET_MARRIED)) {
                    // Player marries when landing in Get Married Space
                    ((GetMarriedSpace) space).getMarried(currentPlayer, gameOfLife.getOtherPlayers());
                } else if(space.getName().equals("Have a Baby") || space.getName().equals("Have a Twin") || space.getName().equals("Have a Triplet")) {
                    // Player haves a baby when landing on Have a Baby Space
                    ((HaveBabySpace) space).haveABaby(currentPlayer);
                } else if(space.getName().equals(Constants.WHICH_PATH)) {
                    // this is the junction where Players can choose the next Path
                    ((WhichPathSpace) currentPlayer.getPath().getJunction()).choosePath(currentPlayer);

                    // handle space landed if the space is magenta
                    Space newSpaceLanded = currentPlayer.getPath().getSpaces()[currentPlayer.getLocation()];
                    if(newSpaceLanded.getType().equals(Constants.MAGENTA_SPACE))
                        handleSpaceLanded(gameOfLife, newSpaceLanded, gameScreenController);
                    else
                        // if not, let's the player take another turn
                        gameOfLife.setTurn(gameOfLife.getTurn() - 1);
                    // refresh screen
                    gameScreenController.refreshGameScreen();
                }
            } else if(space.getType().equals(Constants.RETIREMENT_SPACE)) {
                // Space where Player retires

                // adds the Player's cards back to the Deck
                if(currentPlayer.getSalaryCard() != null) gameOfLife.getSalaryDeck().addCard(currentPlayer.getSalaryCard());
                if(currentPlayer.getCareerCard() != null) gameOfLife.getCareerDeck().addCard(currentPlayer.getCareerCard());
                if(currentPlayer.getHouseCard() != null) gameOfLife.getHouseDeck().addCard(currentPlayer.getHouseCard());

                // retire the player
                ((RetirementSpace) space).retire(currentPlayer);
                gameOfLife.retirePlayer(currentPlayer);
            }
        } else {
            System.out.println("Space is null...");
        }
    }
}
