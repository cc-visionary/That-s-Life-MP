package gui.modals.ChooseMove;

import model.GameOfLife;
import gui.GameScreen.GameScreenController;
import gui.modals.PayDebt.PayDebtController;
import gui.modals.Modal;
import gui.stats.PlayerStats.PlayerStatsController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
import model.Spaces.Space;
import utilities.InputUtil;

public class ChooseMoveController {
    @FXML
    private Button rollDice, viewPlayerStats, payDebt;

    /**
     * Set the values for the Choose Move options
     * @param gameOfLife main model
     * @param gameScreenController controller used to update screen stats
     */
    public void setGameOfLife(GameOfLife gameOfLife, GameScreenController gameScreenController) {
        // let's the player roll a dice
        rollDice.setOnAction(e -> {
            Player currentPlayer = gameOfLife.getCurrentPlayer();
            int rolledDice = currentPlayer.rollDice();
            Space previousSpace = currentPlayer.getPath().getSpaces()[currentPlayer.getLocation()], spaceLanded = null;

            System.out.println(currentPlayer.getLocation() + " + " + rolledDice);

            // remove player from that space
            if(previousSpace.getPlayers().contains(currentPlayer))
                previousSpace.removePlayer(currentPlayer);

            for(int i = 0; i < rolledDice; i++) {
                currentPlayer.addLocation();
                spaceLanded = currentPlayer.getPath().getSpaces()[currentPlayer.getLocation()];
                if(spaceLanded.getType().equals(Constants.MAGENTA_SPACE) || spaceLanded.getType().equals(Constants.RETIREMENT_SPACE)) break; // if player reaches the Magenta Space stop
            }

            spaceLanded.addPlayer(currentPlayer);
            gameScreenController.refreshGameScreen(gameOfLife.getCollegePath(), gameOfLife.getCareerPath(), gameOfLife.getCurrentPlayer());

            handleSpaceLanded(gameOfLife, spaceLanded, gameScreenController);
            gameScreenController.refreshGameScreen(gameOfLife.getCollegePath(), gameOfLife.getCareerPath(), gameOfLife.getCurrentPlayer());

            ((Stage)((Node) e.getSource()).getScene().getWindow()).close();
        });

        // lets the player view his own stat
        viewPlayerStats.setOnAction(e -> {
            Stage playerStatsStage = new Stage();
            playerStatsStage.initStyle(StageStyle.UTILITY);
            playerStatsStage.initModality(Modality.APPLICATION_MODAL);

            FXMLLoader playerStatsLoader = new FXMLLoader(getClass().getResource("/gui/stats/PlayerStats/PlayerStats.fxml"));

            try {
                Parent root = playerStatsLoader.load();
                ((PlayerStatsController) playerStatsLoader.getController()).setStats(gameOfLife.getCurrentPlayer());
                playerStatsStage.setScene(new Scene(root));
            } catch(Exception exception) {
                exception.printStackTrace();
            }

            playerStatsStage.show();
        });

        // lets the player pay he's debt
        payDebt.setOnAction(e -> {
            Stage payDebtStage = new Stage();
            payDebtStage.initStyle(StageStyle.UTILITY);
            payDebtStage.initModality(Modality.APPLICATION_MODAL);

            FXMLLoader payDebtLoader = new FXMLLoader(getClass().getResource("/gui/modals/PayDebt/PayDebt.fxml"));
            try {
                Parent root = payDebtLoader.load();
                ((PayDebtController) payDebtLoader.getController()).setPlayer(gameOfLife.getCurrentPlayer());
                payDebtStage.setScene(new Scene(root));
            } catch(Exception exception) {
                exception.printStackTrace();
            }

            payDebtStage.showAndWait();
            gameScreenController.refreshGameScreen(gameOfLife.getCollegePath(), gameOfLife.getCareerPath(), gameOfLife.getCurrentPlayer());
        });

        if(gameOfLife.getCurrentPlayer().getDebt() <= 0) payDebt.setDisable(true);
    }

    /**
     * Handles the Actions to be done on where the space the Player landed
     * @param gameOfLife
     * @param space space where the currentPlayer landed
     */
    public void handleSpaceLanded(GameOfLife gameOfLife, Space space, GameScreenController gameScreenController) {
//        System.out.println(String.format("You landed on %s - %s", space.getType(), space.getName()));
        Player currentPlayer = gameOfLife.getCurrentPlayer();
        if(space != null) {
            if(space.getType().equals(Constants.BLUE_SPACE)) {
                // picks an blue card and activate it for all the Players
                BlueCard blueCard = ((BlueSpace) space).pickCard(gameOfLife.getBlueDeck());
                GameOfLife.addRoundStat(String.format("%s drew %s(%s)", gameOfLife.getCurrentPlayer().getName(), blueCard.getName(), blueCard.getType()));
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
                GameOfLife.addRoundStat(String.format("%s drew %s(%s)", gameOfLife.getCurrentPlayer().getName(), actionCard.getName(), actionCard.getType()));
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
                        System.out.println(String.format("%s successfully purchased %s for $%.2f", currentPlayer.getName(), houseCard.getName(), houseCard.getCost()));
                        houseCard.setOwner(gameOfLife.getCurrentPlayer());
                        gameOfLife.getCurrentPlayer().setHouseCard(houseCard);
                    } else {
                        // if not, then the player will be given an option to take a loan
                        int choice = InputUtil.scanInt("Do you want to make a loan? (1-Yes, 2-No)", 1, 2);
                        switch(choice) {
                            case 1:
                                // player makes a loan from the bank
                                currentPlayer.bankLoan((int) Math.ceil((houseCard.getCost() - currentPlayer.getBalance()) / 20000));
                                System.out.println(String.format("%s successfully purchased %s for $%.2f", currentPlayer.getName(), houseCard.getName(), houseCard.getCost()));
                                houseCard.setOwner(gameOfLife.getCurrentPlayer());
                                gameOfLife.getCurrentPlayer().setHouseCard(houseCard);
                                break;
                            case 2:
                                // if player won't make a loan, add the card back to the Deck
                                gameOfLife.getHouseDeck().addCard(houseCard);
                                gameOfLife.getHouseDeck().shuffle();
                                break;
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
                    if(currentPlayer.getPath().getName().equals("College Path")) currentPlayer.setHasGraduated(true);

                    CareerCard careerCard = ((CollegeCareerChoiceSpace) space).chooseCareerCard(gameOfLife.getCareerDeck());
                    SalaryCard salaryCard = ((CollegeCareerChoiceSpace) space).chooseSalaryCard(gameOfLife.getSalaryDeck());

                    // returns the Player's Career Card to the Deck
                    if(currentPlayer.getCareerCard() != null) {
                        gameOfLife.getCareerDeck().addCard(currentPlayer.getCareerCard());
                        gameOfLife.getCareerDeck().shuffle();
                    }
                    // then set a new Career Card for the Player
                    currentPlayer.setCareerCard(careerCard);

                    // returns the Player's Salary Card to the Deck
                    if(currentPlayer.getSalaryCard() != null) {
                        gameOfLife.getSalaryDeck().addCard(currentPlayer.getSalaryCard());
                        gameOfLife.getSalaryDeck().shuffle();
                    }
                    // then set a new Salary Card for the Player
                    currentPlayer.setSalaryCard(salaryCard);
                } else if(space.getName().equals(Constants.GET_MARRIED)) {
                    // Player marries when landing in Get Married Space
                    ((GetMarriedSpace) space).getMarried(gameOfLife.getCurrentPlayer(), gameOfLife.getOtherPlayers());
                } else if(space.getName().equals("Have a Baby") || space.getName().equals("Have a Twin") || space.getName().equals("Have a Triplet")) {
                    // Player haves a baby when landing on Have a Baby Space
                    ((HaveBabySpace) space).haveABaby(gameOfLife.getCurrentPlayer());
                } else if(space.getName().equals(Constants.WHICH_PATH)) {
                    // this is the junction where Players can choose the next Path
                    ((WhichPathSpace) currentPlayer.getPath().getJunction()).choosePath(currentPlayer);
                    // refresh screen
                    gameScreenController.refreshGameScreen(gameOfLife.getCollegePath(), gameOfLife.getCareerPath(), gameOfLife.getCurrentPlayer());
                    // let's the player take another turn
                    gameOfLife.setTurn(gameOfLife.getTurn() - 1);
                }
            } else if(space.getType().equals(Constants.RETIREMENT_SPACE)) {
                // Space where Player retires
                gameOfLife.getCurrentPlayer().setIsRetired(true);
            }
        } else {
            System.out.println("Space is null...");
        }
    }


}
