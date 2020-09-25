package model;

public class GameSettings {
    private int nPlayers = 2;
    private int startingMoney = 200000;

    public int getStartingMoney() {
        return startingMoney;
    }

    public int getNPlayers() {
        return nPlayers;
    }

    public void setMoney(int startingMoney) {
        this.startingMoney = startingMoney;
    }

    public void setNPlayers(int nPlayers) {
        this.nPlayers = nPlayers;
    }
}
