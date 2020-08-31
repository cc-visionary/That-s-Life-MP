package main.cards.BlueCard;

import main.players.Player;
import main.utilities.StringUtil;

/**
 * Represents the Blue Card - Lawsuit Card
 */

final public class LawsuitCard extends BlueCard {
    final private int multiplier;

    public LawsuitCard(String career, int multiplier) {
        super("Lawsuit", "The player pays the amount indicated on the card.", career);
        this.multiplier = multiplier;
    }

    /**
     * Returns the value to be paid by the player
     * @return multiplier * 10000
     */
    @Override
    public double getAmount() {
        return this.multiplier * 10000;
    }



    /**
     * This method displays the card into a 14(max height) x 25(width) unit layout
     */
    @Override
    public void  displayCard() {
        final int length = 23, descriptionHeight = 8;
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
        System.out.println("│" + StringUtil.centerString("Career: " + getCareer(), length) + "│");
        System.out.println("│" + StringUtil.centerString("Amount: $" + (this.multiplier * 10000), length) + "│");
        System.out.println("╰───────────────────────╯");
    }
}
