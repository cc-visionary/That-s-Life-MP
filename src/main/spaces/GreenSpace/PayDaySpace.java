package main.spaces.GreenSpace;

import main.players.Player;

public class PayDaySpace extends GreenSpace {
    public PayDaySpace() {
        super("Pay Day");
    }

    /**
     * Add the players money by his SalaryCard's salary
     * @param player
     */
    public void giveSalary(Player player) {
        player.addBalance(player.getSalaryCard().getSalary());
    }
}
