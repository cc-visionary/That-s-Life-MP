package main.players;
import main.cards.CareerCard.CareerCard;
import main.cards.HouseCard.HouseCard;
import main.cards.SalaryCard.SalaryCard;
import main.paths.Path;

import main.spaces.Space;
import main.utilities.InputUtil;
import main.utilities.RandomUtil;

import java.util.ArrayList;

/**
 * Represents the Player
 */

final public class Player {
    private static int playerCount;
    private int nthPlayer;
    private String name;
    private Path path;
    private int location = 0;
    private SalaryCard salaryCard;
    private CareerCard careerCard;
    private HouseCard houseCard;
    private boolean isMarried = false, hasGraduated = false, isRetired = false;
    private int nBabies = 0;
    private double balance = 20000, debt;
    private int nBankLoan = 0;

    public Player(String name, Path path, CareerCard careerCard, SalaryCard salaryCard) {
        this.name = name;
        this.path = path;
        this.careerCard = careerCard;
        this.salaryCard = salaryCard;
        this.playerCount++;
        this.nthPlayer = this.playerCount;
    }

    public Player(String name, CareerCard careerCard, SalaryCard salaryCard) {
        this.name = name;
        this.careerCard = careerCard;
        this.salaryCard = salaryCard;
        this.playerCount++;
        this.nthPlayer = this.playerCount;
    }

    public Player(String name) {
        this.name = name;
        this.playerCount++;
        this.nthPlayer = this.playerCount;
    }

    public void chooseMove() {
        int maxChoice = 2;
        System.out.println("Choose your move:");
        System.out.println("\t[1] Roll Dice");
        System.out.println("\t[2] View Stats");
        if(this.debt > 0) {
            System.out.println("\t[3] Pay Debt (" + this.debt + ")");
            maxChoice++;
        }
        int choice = InputUtil.scanInt("Move:", 1, maxChoice);
        switch(choice) {
            case 1:
                rollDice();
                break;
            case 2:
                displayPlayerStats();
                break;
            case 3:
                // Each payment is a multiple of 25000
                break;
            default:
                System.out.println("Invalid move... (" + choice + ")");
        }
    }

    /**
     * Allows the use to roll and dice and generate a random number from 1-6
     * Moves the player  by the number of spaces
     * @return the rolled dice/randomly generated number (1-6)
     */
    public void rollDice() {
        int dice = RandomUtil.chooseRandomNumber(1, 6);

        for(int i = 0; i < dice; i++) {
            addLocation();
            Space currSpace = getPath().getSpaces()[location];
            currSpace.displaySpace();
        }
    }

    /**
     * Adds a parameter amount to the balance
     * @param amount amount to be added
     */
    public void addBalance(double amount) {
        this.balance += amount;
    }

    /**
     * Deducts a parameter amount from the balance. If parameter amount is greater
     * than the balance, you get a debt of amount - balance
     * @param amount amount to be deducted
     */
    public void payBalance(double amount) {
        if(amount > this.balance) {
            System.out.printf("The balance to be paid is greater than %s's current balance.\n", name);
            bankLoan(); // add to debt from the bank
        }
        this.balance -= amount;
    }

    /**
     * Increments the location of the player
     * This determines the Player's location on the Path
     */
    public void addLocation() {
        this.location++;
    }

    /**
     * Lets the user loan from the bank
     */
    public void bankLoan() {
        System.out.println("Player loaned $20000 from the bank!");
        this.debt += 20000;
        this.nBankLoan++;
    }

    /**
     * Adds a parameter amount to the nBaby attribute
     * @param amount amount of babies to be added 1-3(single, twin, triplet)
     */
    public void addBaby(int amount) {
        this.nBabies += amount;
    }

    /**
     * Adds the parameter houseCard to the ArrayList(HouseCard) attribute of the Player
     * @param houseCard newly bought house by the player
     */
    public void setHouseCard(HouseCard houseCard) {
        this.houseCard = houseCard;
    }

    /**
     * Changes the current path of the object the parameter path
     * @param path the path to be assigned to the player's path
     */
    public void setPath(Path path) {
        if(this.path != null) {
            this.path.removePlayer(this);
        }
        this.path = path;
        this.path.addPlayer(this);
        this.location = 0;
    }

    /**
     * Assigns a new Salary Card for the Player, and assign owner to SalaryCard
     * @param salaryCard SalaryCard to be assigned for the player
     */
    public void setSalaryCard(SalaryCard salaryCard) {
        this.salaryCard = salaryCard;
        this.salaryCard.setOwner(this);
    }

    /**
     * Assigns a new Career Card for the Player, and assign owner to CareerCard
     * @param careerCard CareerCard to be assigned for the player
     */
    public void setCareerCard(CareerCard careerCard) {
        this.careerCard = careerCard;
        this.careerCard.setOwner(this);
    }

    /**
     * Changes the value of whether the Player is married or not
     * @param isMarried determines whether a Player is married or not
     */
    public void setIsMarried(boolean isMarried) {
        this.isMarried = isMarried;
    }

    /**
     * Changes the value of whether the Player has graduated or not
     * @param hasGraduated determines whether a Player is graduated or not
     */
    public void setHasGraduated(boolean hasGraduated) {
        this.hasGraduated = hasGraduated;
    }

    /**
     * Changes the value of whether the Player is retired or not
     * @param isRetired value to be set to isRetired
     */
    public void setIsRetired(boolean isRetired) {
        this.isRetired = isRetired;
    }

    /**
     * Assigns a null value to the current Salary Card of the Player
     */
    public void removeSalaryCard() {
        this.salaryCard = null;
    }

    /**
     * Assigns a null value to the current Career Card of the Player
     */
    public void removeCareerCard() {
        this.careerCard = null;
    }


    public String getName() {
        return name;
    }
    public double getBalance() {
        return balance;
    }
    public double getDebt() {
        return debt;
    }
    public Path getPath() {
        return path;
    }
    public SalaryCard getSalaryCard() {
        return this.salaryCard;
    }
    public CareerCard getCareerCard() {
        return this.careerCard;
    }
    public HouseCard getHouseCard() {
        return houseCard;
    }
    public static int getPlayerCount() {
        return playerCount;
    }
    public int getNthPlayer() {
        return nthPlayer;
    }
    public int getNBabies() {
        return nBabies;
    }
    public int getLocation() {
        return location;
    }

    public int getNBankLoan() {
        return nBankLoan;
    }

    public boolean isGraduated() {
        return hasGraduated;
    }

    public boolean isMarried() {
        return isMarried;
    }

    public boolean isRetired() {
        return isRetired;
    }

    /**
     * Checks whether the player has reached retirement or not
     * @return boolean values (true/false)
     */
    public boolean hasReachedRetirement() {
        return false;
    }

    /**
     * Displays the Player Stats such as
     *  - Balance
     *  - Debt
     *  - SalaryCard
     *  - CareerCard
     *  - Current Path
     */
    public void displayPlayerStats() {
        System.out.println("--------------------------------------------------");
        System.out.println("\t Player " + getNthPlayer() + "'s [ " + getName() + " ] Stats");
        System.out.println("--------------------------------------------------");
        System.out.println("\tBalance: " + getBalance());
        System.out.println("\tDebt   : " + getDebt());
        if(getSalaryCard() != null) {
            System.out.println("\tSalary Card:" + getSalaryCard());
        } else {
            System.out.println("\tSalary Card: None");
        }
        if(getCareerCard() != null) {
            System.out.println("\tCareer Card:" + getCareerCard());
        } else {
            System.out.println("\tCareer Card: None");
        }
        if(getHouseCard() != null) {
            System.out.println("\tCareer Card:" + getCareerCard());
        } else {
            System.out.println("\tCareer Card: None");
        }
        System.out.println("\tCurrent Path: " + (getPath() == null ? "None" : getPath().getName()));
        System.out.println("--------------------------------------------------");
    }

    @Override
    public String toString() {
        return String.format("Player{name=%s,balance=%.2f,debt=%.2f}", name, balance, debt);
    }
}
