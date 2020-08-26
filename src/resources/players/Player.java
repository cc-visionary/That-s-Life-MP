package resources.players;
import resources.cards.CareerCard.CareerCard;
import resources.cards.SalaryCard.SalaryCard;
import resources.paths.Path;

import java.util.Random;

final public class Player {
    private String name;
    private Path path;
    private SalaryCard salaryCard;
    private CareerCard careerCard;
    private boolean canMove = false;
    private double money = 200000, debt;
    private static int playerCount;

    public Player(String name, Path path, CareerCard careerCard, SalaryCard salaryCard) {
        this.name = name;
        this.path = path;
        this.careerCard = careerCard;
        this.salaryCard = salaryCard;
        this.playerCount++;
    }

    public Player(String name, Path path) {
        this.name = name;
        this.path = path;
        this.playerCount++;
    }

    /**
     * Allows the use to roll and dice and generate a random number from 1-6
     * @return rolledDice : int
     */
    public int rollDice() {
        Random dice = new Random();
        int rolledDice = dice.nextInt(6) + 1;
        return rolledDice;
    }

    /**
     * Changes the current path of the object the parameter path
     * @param path
     */
    public void changePath(Path path) {
        this.path = path;
    }

    /**
     * Adds a parameter amount to the money
     * @param amount
     */
    public void addMoney(double amount) {
        this.money += amount;
    }

    /**
     * Deducts a parameter amount from the money. If parameter amount is greater
     * than the money, you get a debt of amount - money
     * @param amount
     */
    public void payMoney(double amount) {
        if(amount > this.money) {
            System.out.printf("The money to be paid is greater than %s's current money.\n", name);
            addDebt(amount - this.money); // add to debt from the bank
            this.money = 0;
        } else {
            this.money -= amount;
        }
    }

    /**
     * Adds a parameter amount to the debt
     * @param amount
     */
    public void addDebt(double amount) {
        this.debt += amount;
    }

    /**
     * Assigns a new Salary Card for the Player
     * @param salaryCard : SalaryCard
     */
    public void setSalaryCard(SalaryCard salaryCard) {
        this.salaryCard = salaryCard;
    }

    /**
     * Assigns a new Career Card for the Player
     * @param careerCard : CareerCard
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

    /**
     * Returns the name of the Player
     * @return name : String
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the current Path of the Player
     * @return path : Path
     */
    public Path getPath() {
        return path;
    }

    /**
     * Returns the current amount of the money of the Player
     * @return money : double
     */
    public double getMoney() {
        return money;
    }

    /**
     * Returns the current amount of the debt of the Player
     * @return debt : double
     */
    public double getDebt() {
        return debt;
    }

    /**
     * Gets the current Salary Card of the Player
     * @return salaryCard : SalaryCard
     */
    public SalaryCard getSalaryCard() {
        return this.salaryCard;
    }

    /**
     * Gets the current Career Card of the Player
     * @return careerCard : CareerCard
     */
    public CareerCard getCareerCard() {
        return this.careerCard;
    }

    /**
     * Checks whether the player has reached retirement or not
     * @return true/false : boolean
     */
    public boolean hasReachedRetirement() {
        return false;
    }

    /**
     * Get the head count of the Player
     * @return playerCount : int
     */
    public static int getPlayerCount() {
        return playerCount;
    }
}
