package model;

public class GameSettings {
    private int player = 2;
    private int money = 200000;

    public int getMoney() {
        return money;
    }

    public int getPlayer() {
        return player;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setPlayer(int player) {
        this.player = player;
    }
}
