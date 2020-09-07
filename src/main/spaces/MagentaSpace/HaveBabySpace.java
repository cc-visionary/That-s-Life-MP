package main.spaces.MagentaSpace;

import main.players.Player;

/**
 * Represents the Magenta Space - Have a Baby or Twin Space
 *      where when a Married Player lands here, he/she collects $5000 for each child from each players.
 *      On the other hand, if an Unmarried Player lands in this space, nothing happens
 */

final public class HaveBabySpace extends MagentaSpace {
    final private static String[] TERMS = {"Baby", "Twin", "Triplet"};
    private int nBabies;

    public HaveBabySpace(int nBabies) {
        super("Have a " + TERMS[nBabies - 1]);
        this.nBabies = nBabies;
    }

    /**
     * Gives a n number of babies to the Player
     * @param player player who landed on the space
     */
    public void haveABaby(Player player) {
        if(player.isMarried()) {
            player.addBaby(nBabies);
        } else {
            System.out.println(player.getName() + " is not yet married! Nothing happens.");
        }
    }
}
