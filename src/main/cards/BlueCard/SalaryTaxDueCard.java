package main.cards.BlueCard;

import main.Constants;
import main.players.Player;
import main.utilities.StringUtil;

/**
 * Represents the Blue Card - Salary Tax Due Card
 */

final public class SalaryTaxDueCard extends BlueCard {
     public SalaryTaxDueCard() {
         super("Salary Tax Due", "The player pays the tax due for his current salary.", Constants.ACCOUNTANT);
     }

    /**
     * Returns the salary tax due of the player
     * @return amount to be paid by the player
     */
    @Override
    public double getAmount() {
         return getOwner().getSalaryCard().getTax();
    }
}
