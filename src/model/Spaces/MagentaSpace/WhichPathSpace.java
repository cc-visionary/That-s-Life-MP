package model.Spaces.MagentaSpace;

import gui.modals.Modal;
import model.Constants;
import model.Players.Player;

/**
 * Represents the Magenta Space - Which Path Space
 *      which is a junction where players can choose to continue the current path or switch to another one
 */

final public class WhichPathSpace extends MagentaSpace {
    public WhichPathSpace() {
        super(Constants.WHICH_PATH);
    }

    /**
     * Junction where the Player can choose between 2 Paths
     * @param player  player who landed on the junction
     */
    public void choosePath(Player player) {
        // if path 2 is not null, allow the user to choose between the paths
        if(player.getPath().getPath2() != null) {
            new Modal().choosePath(player, player.getPath().getPath1(), player.getPath().getPath2());
        } else { // if not, automatically return the Path 1;
            player.setPath(player.getPath().getPath1());
        }
    }
}
