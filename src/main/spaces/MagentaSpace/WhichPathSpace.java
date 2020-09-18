package main.spaces.MagentaSpace;

import gui.GUI;
import gui.choose.ChoosePath;
import main.Constants;
import main.paths.Path;
import main.players.Player;
import main.utilities.InputUtil;

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
     * @return        path chosen by the Player
     */
    public void choosePath(Player player) {
        // if path 2 is not null, allow the user to choose between the paths
        if(player.getPath().getPath2() != null) {
            ChoosePath.choosePath(player);
        } else { // if not, automatically return the Path 1;
            player.setPath(player.getPath().getPath1());
        }
    }
}
