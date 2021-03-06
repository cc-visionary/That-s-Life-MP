package model.Spaces.GreenSpace;

import model.Constants;
import model.GameOfLife;
import model.Players.Player;

/**
 * Represents the Green Space - Pay Day Space
 *      which allows the Player receive his/her current salary
 */

final public class PayDaySpace extends GreenSpace {
    public PayDaySpace() {
        super(Constants.PAY_DAY);
    }

    /**
     * Gives the Player's salary to his/her balance by through his/her SalaryCard's salary
     * @param player Player to give salary to
     */
    public void giveSalary(Player player) {
        GameOfLife.addRoundStat(String.format("%s received his salary ($%d)", player.getName(), player.getSalaryCard().getSalary()));
        player.addBalance(player.getSalaryCard().getSalary());
    }
}
