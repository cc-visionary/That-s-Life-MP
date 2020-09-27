package model.Spaces.GreenSpace;

import model.Constants;
import model.GameOfLife;
import model.Players.Player;

/**
 * Represents the Green Space - Pay Day Space
 *      which allows the Player to increase his/her salary
 */

final public class PayRaiseSpace extends GreenSpace {
    private int salaryRaise;
    public PayRaiseSpace(int salaryRaise) {
        super(Constants.PAY_RAISE);
        this.salaryRaise = salaryRaise;
    }

    /**
     * Increases the salary of the parameter Player
     * @param player Player whose salary will be raised
     */
    public void raiseSalary(Player player) {
        GameOfLife.addRoundStat(String.format("%s's salary increased from %d to %d", player.getName(), player.getSalaryCard().getSalary(), player.getSalaryCard().getSalary() + salaryRaise));
        player.getSalaryCard().increaseSalary(salaryRaise);
    }
}
