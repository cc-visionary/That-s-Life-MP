package model.spaces.GreenSpace;

import model.Constants;
import model.players.Player;

/**
 * Represents the Green Space - Pay Day Space
 *      which allows the Player to increase his/her salary
 */

final public class PayRaiseSpace extends GreenSpace {
    private int salaryRaise;
    public PayRaiseSpace(double salaryRaise) {
        super(Constants.PAY_RAISE);
    }

    /**
     * Increases the salary of the parameter Player
     * @param player Player whose salary will be raised
     */
    public void raiseSalary(Player player) {
        player.getSalaryCard().increaseSalary(salaryRaise);
    }
}
