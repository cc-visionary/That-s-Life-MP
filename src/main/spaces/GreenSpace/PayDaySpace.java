package main.spaces.GreenSpace;

import main.players.Player;

/**
 * PayDaySpace Class
 *      space which allows the Player receive his/her current salary
 */

public class PayDaySpace extends GreenSpace {
    public PayDaySpace() {
        super("Pay Day");
    }

    /**
     * Gives the Player's salary to his/her balance by through his/her SalaryCard's salary
     * @param player
     */
    public void giveSalary(Player player) {
        player.addBalance(player.getSalaryCard().getSalary());
    }
}
