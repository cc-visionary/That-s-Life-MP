package model.spaces.RetirementSpace;

import model.Constants;
import model.players.Player;
import model.spaces.Space;

/**
 * Represents the RetirementSpace where reaching this will determine that a Player has won.
 */
public class RetirementSpace extends Space {
    public RetirementSpace() {
        super(Constants.RETIREMENT_SPACE);
    }

    /**
     * Sets the Player's retirement value to true
     * @param player player to be retired
     */
    public void retire(Player player) {
        player.setIsRetired(true);
    }
}
