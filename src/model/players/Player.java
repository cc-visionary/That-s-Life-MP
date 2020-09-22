package model.players;
import model.Constants;
import gui.GameOfLife;
import model.cards.CareerCard.CareerCard;
import model.cards.HouseCard.HouseCard;
import model.cards.SalaryCard.SalaryCard;
import model.paths.Path;

import utilities.RandomUtil;

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
    private double balance = Constants.STARTING_MONEY, debt;
    private int nBankLoan = 0;

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

    /**
     * Allows the use to roll and dice and generate a random number from 1-6
     * Moves the player  by the number of spaces
     * @return the rolled dice/randomly generated number (1-10)
     */
    public int rollDice() {
        int rolledDice = RandomUtil.chooseRandomNumber(1, 10);
        GameOfLife.addRoundStat(String.format("%s rolled %d!", getName(), rolledDice));
        return rolledDice;
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
            bankLoan((int) Math.ceil((amount - getBalance()) / 20000)); // add to debt from the bank
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
     * Allows the Player to make a loan from the bank
     * @param times the multiplier/number of times for the bank loan
     */
    public void bankLoan(int times) {
        GameOfLife.addRoundStat(String.format("%s loaned $%d + %d interest = $%d from the bank", getName(), 20000 * times, 5000 * times, 25000 * times));
        this.debt += 25000 * times;
        this.nBankLoan += times;
    }

    /**
     * Allows the Player to pay all of his/her debt
     * @param times multiplier for the amount of times to be paid
     */
    public void payDebt(int times) {
        GameOfLife.addRoundStat(String.format("%s paid %d to the bank to pay off the debt", getName(), 25000 * times));
        payBalance(2500 * times);
        this.debt -= 2500 * times;
        this.nBankLoan -= times;
    }

    /**
     * Adds a parameter amount to the nBaby attribute
     * @param amount amount of babies to be added 1-3(single, twin, triplet)
     */
    public void addBaby(int amount) {
        GameOfLife.addRoundStat(String.format("%s had %d new children", getName(), amount));
        this.nBabies += amount;
    }

    /**
     * Adds the parameter houseCard to the ArrayList(HouseCard) attribute of the Player
     * @param houseCard newly bought house by the player
     */
    public void setHouseCard(HouseCard houseCard) {
        GameOfLife.addRoundStat(String.format("%s's house is now set to %s", getName(), houseCard.getName()));
        this.houseCard = houseCard;
    }

    /**
     * Changes the current path of the object the parameter path
     * @param path the path to be assigned to the player's path
     */
    public void setPath(Path path) {
        GameOfLife.addRoundStat(String.format("%s's path is now set to %s", getName(), path.getName()));
        if(this.path != null) {
            this.path.getJunction().removePlayer(this);
        }
        this.path = path;
        if(path.getName() == "College Path") bankLoan(2);
        this.location = 0;
    }

    /**
     * Assigns a new Salary Card for the Player, and assign owner to SalaryCard
     * @param salaryCard SalaryCard to be assigned for the player
     */
    public void setSalaryCard(SalaryCard salaryCard) {
        GameOfLife.addRoundStat(String.format("%s's salary card is now set to %s", getName(), salaryCard));
        this.salaryCard = salaryCard;
        this.salaryCard.setOwner(this);
    }

    /**
     * Assigns a new Career Card for the Player, and assign owner to CareerCard
     * @param careerCard CareerCard to be assigned for the player
     */
    public void setCareerCard(CareerCard careerCard) {
        GameOfLife.addRoundStat(String.format("%s's career card is now set to %s", getName(), careerCard));
        this.careerCard = careerCard;
        this.careerCard.setOwner(this);
    }

    /**
     * Changes the value of whether the Player is married or not
     * @param isMarried determines whether a Player is married or not
     */
    public void setIsMarried(boolean isMarried) {
        GameOfLife.addRoundStat(String.format("%s is now %s", getName(), isMarried ? "married" : "not married"));
        this.isMarried = isMarried;
    }

    /**
     * Changes the value of whether the Player has graduated or not
     * @param hasGraduated determines whether a Player is graduated or not
     */
    public void setHasGraduated(boolean hasGraduated) {
        GameOfLife.addRoundStat(String.format("%s is now %s", getName(), hasGraduated ? "graduated" : "not graduated"));
        this.hasGraduated = hasGraduated;
    }

    /**
     * Changes the value of whether the Player is retired or not
     * @param isRetired value to be set to isRetired
     */
    public void setIsRetired(boolean isRetired) {
        GameOfLife.addRoundStat(String.format("%s is now %s", getName(), isRetired ? "retired" : "not retired"));
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

    @Override
    public String toString() {
        return String.format("Player{name=%s,balance=%.2f,debt=%.2f}", name, balance, debt);
    }
}