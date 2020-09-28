package model.Players;
import model.GameOfLife;
import model.Cards.CareerCard.CareerCard;
import model.Cards.HouseCard.HouseCard;
import model.Cards.SalaryCard.SalaryCard;
import model.Paths.Path;

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
    private int balance, debt;
    private int nBankLoan = 0;
    private boolean isCurrentPlayer = false;

    public Player(String name, int startingMoney) {
        this.name = name;
        this.playerCount++;
        this.nthPlayer = this.playerCount;
        this.balance = startingMoney;
    }

    public Player(int startingMoney) {
        this.playerCount++;
        this.nthPlayer = this.playerCount;
        this.balance = startingMoney;
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
    public void addBalance(int amount) {
        GameOfLife.addRoundStat(String.format("%s's balance was added by $%d", getName(), amount));
        this.balance += amount;
    }

    /**
     * Deducts a parameter amount from the balance. If parameter amount is greater
     * than the balance, you get a debt of amount - balance
     * @param amount amount to be deducted
     */
    public void payBalance(int amount) {
        if(amount > this.balance) {
            System.out.printf("The balance to be paid is greater than %s's current balance.\n", name);
            bankLoan((int) Math.ceil((amount - getBalance()) / 20000)); // add to debt from the bank
            this.balance += (int) Math.ceil((amount - getBalance()) / 20000) * 20000;
            GameOfLife.addRoundStat(String.format("%s automatically loaned $%d to the bank", getName(), ((int) Math.ceil((amount - getBalance()) / 20000) * 20000)));
        }
        GameOfLife.addRoundStat(String.format("%s's balance was reduced by $%d", getName(), amount));
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
        payBalance(25000 * times);
        this.debt -= 25000 * times;
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
     * Set the name of the Player
     * @param name name to be set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Adds the parameter houseCard to the ArrayList(HouseCard) attribute of the Player
     * @param houseCard newly bought house by the player
     */
    public void setHouseCard(HouseCard houseCard) {
        GameOfLife.addRoundStat(String.format("%s's house is now set to %s", getName(), houseCard != null ? houseCard.getName() : null));
        removeHouseCard();
        this.houseCard = houseCard;
        if(this.houseCard != null) this.houseCard.setOwner(this);
    }

    /**
     * Changes the current path of the object the parameter path
     * @param path the path to be assigned to the player's path
     */
    public void setPath(Path path) {
        GameOfLife.addRoundStat(String.format("%s's path is now set to %s", getName(), path == null ? path : path.getName()));
        if(this.path != null) {
            this.path.getSpaces()[this.path.getNSpaces() - 1].removePlayer(this);
            this.path.getJunction().removePlayer(this);
        }
        this.path = path;
        if(path != null && path.getName() == "College Path") bankLoan(2);
        this.location = 0;
        if(this.path != null) this.path.getSpaces()[0].addPlayer(this);
    }

    /**
     * Assigns a new Salary Card for the Player, and assign owner to SalaryCard
     * @param salaryCard SalaryCard to be assigned for the player
     */
    public void setSalaryCard(SalaryCard salaryCard) {
        GameOfLife.addRoundStat(String.format("%s's salary card is now set to %s", getName(), salaryCard));
        removeSalaryCard();
        this.salaryCard = salaryCard;
        if(this.salaryCard != null) this.salaryCard.setOwner(this);
    }

    /**
     * Assigns a new Career Card for the Player, and assign owner to CareerCard
     * @param careerCard CareerCard to be assigned for the player
     */
    public void setCareerCard(CareerCard careerCard) {
        GameOfLife.addRoundStat(String.format("%s's career card is now set to %s", getName(), careerCard));
        removeCareerCard();
        this.careerCard = careerCard;
        if(this.careerCard != null) this.careerCard.setOwner(this);
    }

    public void setLocation(int location) {
        this.location = location;
    }

    /**
     * Marries the player
     */
    public void marry() {
        GameOfLife.addRoundStat(String.format("%s is now Married", getName()));
        isMarried = true;
     }

    /**
     * Graduates the Player
     */
    public void graduate() {
        GameOfLife.addRoundStat(String.format("%s is now Graduated", getName()));
        hasGraduated = true;
    }

    /**
     * Retires the player and does all actions when player is retired
     */
    public void retire() {
        GameOfLife.addRoundStat(String.format("%s is now retired", getName()));
        this.isRetired = true;
        // collect $10000 from the bank for each child
        addBalance(getNBabies() * 10000);
        GameOfLife.addRoundStat(String.format("%s earned $%d from %d babies", getName(), getNBabies() * 10000, getNBabies()));

        // sell house to the bank
        removeHouseCard();
        removeCareerCard();
        removeSalaryCard();

        // repay all loans to the bank
        if(getDebt() > 0) payDebt(getNBankLoan());
    }

    /**
     * Removes the House Card from the Player by assigning a nul
     * value to the current House Card of the Player
     */
    public void removeHouseCard() {
        if(houseCard != null) {
            addBalance(getHouseCard().getCost());
            houseCard.setOwner(null);
            houseCard = null;
        }
    }

    /**
     * Removes the Salary Card from the Player by assigning a null
     * value to the current Salary Card of the Player
     */
    public void removeSalaryCard() {
        if(salaryCard != null) {
            salaryCard.setOwner(null);
            salaryCard = null;
        }
    }

    /**
     * Removes the Career Card from the Player by assigning a null
     * value to the current Career Card of the Player
     */
    public void removeCareerCard() {
        if(careerCard != null) {
            careerCard.setOwner(null);
            careerCard = null;
        }
    }

    /**
     * Sets the player to be the current player
     */
    public void setCurrentPlayer(boolean isCurrentPlayer) {
        this.isCurrentPlayer = isCurrentPlayer;
    }

    public boolean isCurrentPlayer() {
        return isCurrentPlayer;
    }

    public String getName() {
        return name;
    }
    public int getNthPlayer() {
        return nthPlayer;
    }
    public int getBalance() {
        return balance;
    }
    public int getDebt() {
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

    /**
     * Checks whether the player has reached retirement or not
     * @return boolean values (true/false)
     */
    public boolean isRetired() {
        return isRetired;
    }

    @Override
    public String toString() {
        return String.format("Player{name=%s, path=%s,balance=%d,debt=%d}", name, path.getName(), balance, debt);
    }
}
