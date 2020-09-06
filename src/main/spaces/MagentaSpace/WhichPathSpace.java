package main.spaces.MagentaSpace;

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
     * @param currPath  current path which is connected to 2 other path and choose from 1 of those
     * @return          path chosen by the Player
     */
    public Path choosePath(Path currPath) {
        // if path 2 is not null, allow the user to choose between the paths
        if(currPath.getPath2() != null) {
            System.out.println("Paths to choose from:");
            System.out.println("\t 1:" + currPath.getPath1());
            System.out.println("\t 2:" + currPath.getPath2());
            int choice = InputUtil.scanInt("Enter Path:", 1, 2);
            return choice == 1 ? currPath.getPath1() : currPath.getPath2();
        }
        // if not, automatically return the Path 1;
        return currPath.getPath1();
    }
}
