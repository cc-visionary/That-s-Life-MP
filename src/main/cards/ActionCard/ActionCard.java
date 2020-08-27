package main.cards.ActionCard;

import main.cards.Card;
import main.players.Player;
import main.utilities.StringUtil;

/**
 * Action Card Class
 *
 * @author      Christopher G. Lim
 * @version     1.0
 * @last-edited Aug 18, 2020
 * @since       Aug 18, 2020
 */

public abstract class ActionCard extends Card {
    private double amount;
    public ActionCard(String name, String description, double amount) {
        super("Action Card", name, description);
        this.amount = amount;
    }

    /**
     * Returns the value of the amount
     * @return amount
     */
    public double getAmount() {
        return amount;
    }

    public abstract void activate(Player player, Player[] otherPlayers);

    /**
     * This method displays the card into a 14(max height) x 25(width) unit layout
     */
    @Override
    public void  displayCard() {
        final int length = 23, descriptionHeight = 6;
        String[] splittedString = StringUtil.splitStringLength(getDescription(), length).toArray(new String[0]);

        System.out.println("╭───────────────────────╮");
        System.out.println("│" + StringUtil.centerString(getType(), length)                  + "│");
        System.out.println("│" + StringUtil.centerString("(" + getName() + ")", length) + "│");
        System.out.println("├───────────────────────┤");
        for(int i = 0; i < descriptionHeight; i++) {
            if(i < splittedString.length) {
                System.out.println("│" + StringUtil.centerString(splittedString[i], length)  + "│");
            } else {
                System.out.println("│" + StringUtil.centerString("", length)            + "│");
            }
        }
        System.out.println("├───────────────────────┤");
        System.out.println("│" + StringUtil.centerString("Amount: " + getAmount(), length)            + "│");
        System.out.println("╰───────────────────────╯");
    }
}
