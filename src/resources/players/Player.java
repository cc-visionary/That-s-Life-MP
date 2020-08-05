package resources.players;
import resources.paths.Path;
import resources.spaces.Space;
import resources.cards.Card;

import java.util.ArrayList;

public class Player {
    private String name;
    private Path path;
    private ArrayList<Card> cards = new ArrayList<Card>();
    private double money = 200000;
    private double salary;
    private double debt;
    private boolean canMove;
    private static int playerCount = 0;

    public Player(String name, Path path, Card card) {
        this.name = name;
        this.path = path;
        this.cards.add(card);
        this.playerCount++;
    }

    public Player(String name) {
        this.name = name;
        this.playerCount++;
    }

    public String getName() {
        return name;
    }

    public void changeName(String name) {
        this.name = name;
    }

//    public int rollDice() {
//        return
//    }

    public void changePath(Path path) {
        this.path = path;
    }

    public void addMoney(double money) {
        this.money += money;
    }

    public void payMoney(double money) {
        if(money > this.money) {
            System.out.printf("The money to be paid is greater than %s's current money.\n", name);
            this.money = 0;
        } else {
            this.money -= money;
        }
    }

    public double getMoney() {
        return money;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

//    public void useCard(int index) {
//        cards[index].activate();
//        cards.remove(index)
//    }

    public boolean hasCard() {
        return cards.size() != 0;
    }

    public boolean hasReachedRetirement() {
        return false;
    }

    public static int getPlayerCount() {
        return playerCount;
    }
}
