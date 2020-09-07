package main.spaces.RetirementSpace;

import main.Constants;
import main.players.Player;
import main.spaces.Space;

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
