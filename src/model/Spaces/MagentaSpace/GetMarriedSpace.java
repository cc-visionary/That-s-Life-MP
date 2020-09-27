package model.Spaces.MagentaSpace;

import model.Constants;
import model.GameOfLife;
import model.Players.Player;
import utilities.RandomUtil;

/**
 * Represents the Magenta Space - Get Married Space
 *      where a player marries and depending on the Player's random generated number, he/she pays a certain
 *      amount of money for the wedding gift.
 */

final public class GetMarriedSpace extends MagentaSpace {
    public GetMarriedSpace() {
        super(Constants.GET_MARRIED);
    }

    /**
     * If player is unmarried, let the Player marry and let the Player receive a wedding gift from all the other Players.
     * @param player Player who landed in the Space, and will receive the wedding gift from all the other players.
     * @param otherPlayers other players who will pay the player for the wedding gift.
     */
    public void getMarried(Player player, Player[] otherPlayers) {
        if(!player.isMarried()) {
            int generatedNumber = RandomUtil.chooseRandomNumber(1, 100), amount;
            if(generatedNumber % 2 == 0) {
                amount = 10000;
                GameOfLife.addRoundStat("The generated number is " + generatedNumber + "(even) each Player will pay " + player.getName() + " $10000 for the wedding gift. ($" + otherPlayers.length * 10000 + ")");
            } else {
                amount = 5000;
                GameOfLife.addRoundStat("The generated number is " + generatedNumber + "(odd) each Player will pay " + player.getName() + " $5000 for the wedding gift. ($" + otherPlayers.length * 5000 + ")");
            }
            // reduces the amount from the balance of the other players
            for(Player otherPlayer : otherPlayers) {
                otherPlayer.payBalance(amount);
            }

            // adds those amount to the balance of the Player who landed on the space
            player.addBalance(amount * otherPlayers.length);
            player.marry();
        } else {
            GameOfLife.addRoundStat(player.getName() + " is already married! Nothing happens.");
        }
    }
}
