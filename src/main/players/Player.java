package main.players;
import main.cards.CareerCard.CareerCard;
import main.cards.HouseCard.HouseCard;
import main.cards.SalaryCard.SalaryCard;
import main.paths.Path;

import main.utilities.RandomUtil;

import java.util.ArrayList;

/**
 * Represents the Player
 */

final public class Player {
    private String name;
    private Path path;
    private SalaryCard salaryCard;
    private CareerCard careerCard;
    private ArrayList<HouseCard> houseCards;
    private int nthPlayer;
    private boolean canMove = false;
    private boolean isMarried = false;
    private int nBabies = 0;
    private double balance = 20000, debt;
    private static int playerCount;

    public Player(String name, Path path, CareerCard careerCard, SalaryCard salaryCard) {
        this.name = name;
        this.path = path;
        this.careerCard = careerCard;
        this.salaryCard = salaryCard;
        this.playerCount++;
        this.nthPlayer = this.playerCount;
        this.houseCards = new ArrayList<HouseCard>();
    }

    public Player(String name, CareerCard careerCard, SalaryCard salaryCard) {
        this.name = name;
        this.careerCard = careerCard;
        this.salaryCard = salaryCard;
        this.playerCount++;
        this.nthPlayer = this.playerCount;
        this.houseCards = new ArrayList<HouseCard>();
    }

    public Player(String name) {
        this.name = name;
        this.playerCount++;
        this.nthPlayer = this.playerCount;
        this.houseCards = new ArrayList<HouseCard>();
    }

    /**
     * Allows the use to roll and dice and generate a random number from 1-6
     * @return the rolled dice/randomly generated number (1-6)
     */
    public int rollDice() {
        return RandomUtil.chooseRandomNumber(1, 6);
    }

    /**
     * Changes the current path of the object the parameter path
     * @param path the path to be assigned to the player's path
     */
    public void changePath(Path path) {
        this.path = path;
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
            addDebt(amount - this.balance); // add to debt from the bank
            this.balance = 0;
        } else {
            this.balance -= amount;
        }
    }

    /**
     * Adds a parameter amount to the debt
     * @param amount amount to be added
     */
    public void addDebt(double amount) {
        this.debt += amount;
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
    public void addHouse(HouseCard houseCard) {
        this.houseCards.add(houseCard);
    }

    /**
     * Assigns a new Salary Card for the Player
     * @param salaryCard SalaryCard to be assigned for the player
     */
    public void setSalaryCard(SalaryCard salaryCard) {
        this.salaryCard = salaryCard;
    }

    /**
     * Assigns a new Career Card for the Player
     * @param careerCard CareerCard to be assigned for the player
     */
    public void setCareerCard(CareerCard careerCard) {
        this.careerCard = careerCard;
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
    public static int getPlayerCount() {
        return playerCount;
    }
    public int getNthPlayer() {
        return nthPlayer;
    }
    public int getNBabies() {
        return nBabies;
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
//            getSalaryCard().displayCard();
        } else {
            System.out.println("\tSalary Card: None");
        }

        if(getCareerCard() != null) {
            System.out.println("\tCareer Card:" + getCareerCard());
//            getCareerCard().displayCard();
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
