package main.spaces.MagentaSpace;

import main.players.Player;
import main.utilities.RandomUtil;

/**
 * Represents the Magenta Space - Get Married Space
 *      where a player marries and depending on the Player's random generated number, he/she pays a certain
 *      amount of money for the wedding gift.
 */

final public class GetMarriedSpace extends MagentaSpace {
    public GetMarriedSpace() {
        super("Get Married");
    }

    /**
     * If player is unmarried, let the Player marry and let the Player pay the wedding gift.
     * @param player Player who landed in the Space
     */
    public void getMarried(Player player) {
        if(!player.isMarried()) {
            int generatedNumber = RandomUtil.chooseRandomNumber(1, 100);
            if(generatedNumber % 2 == 0) {
                player.payBalance(10000);
                System.out.println("The generated number is " + generatedNumber + "(even) you pay $10000 for the wedding gift.");
            } else {
                player.payBalance(5000);
                System.out.println("The generated number is " + generatedNumber + "(odd) you pay $5000 for the wedding gift.");
            }
            player.setMarried(true);
        } else {
            System.out.println(player.getName() + " is already married! Nothing happens.");
        }
    }
}
