package main.cards.BlueCard;

import main.players.Player;
import main.utilities.StringUtil;

final public class SkiAccidentCard extends BlueCard {
    public SkiAccidentCard(String career) {
        super("Ski Accident", "The player pays $10000.", career);
    }

    /**
     * Returns the value to be paid by the player
     * @param player the player who drew the card
     * @return 10000
     */
    @Override
    public double getAmount(Player player) {
        return 10000;
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
        System.out.println("│" + StringUtil.centerString("Amount: $10000", length) + "│");
        System.out.println("╰───────────────────────╯");
    }
}
