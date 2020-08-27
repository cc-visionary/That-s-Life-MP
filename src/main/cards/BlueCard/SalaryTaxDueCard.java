package main.cards.BlueCard;

import main.players.Player;
import main.utilities.StringUtil;

final public class SalaryTaxDueCard extends BlueCard {
     public SalaryTaxDueCard(String career) {
         super("Salary Tax Due", "The player pays the tax due for his current salary.", career);
     }

    /**
     * Returns the salary tax due of the player
     * @param player the player who drew the card
     * @return amount to be paid by the player
     */
    @Override
    public double getAmount(Player player) {
         return player.getSalaryCard().getTax();
    }
}
