package main.spaces.MagentaSpace;

import main.paths.Path;
import main.players.Player;
import main.utilities.InputUtil;

/**
 * Represents the Magenta Space - Which Path Space
 *      which is a junction where players can choose to continue the current path or switch to another one
 */

final public class WhichPathSpace extends MagentaSpace {
    public WhichPathSpace() {
        super("Which Path");
    }

    /**
     * Junction where the Player can choose the current Path or a new Path
     * @param currPath  current path to be chosen
     * @param otherPath other path to be cosen
     * @return          path chosen by the Player
     */
    public Path choosePath(Path currPath, Path otherPath) {
        System.out.println("Paths to choose from:");
        System.out.println("\t 1:" + currPath);
        System.out.println("\t 2:" + otherPath);
        int choice = InputUtil.scanInt("Enter Path:", 1, 2);
        return choice == 1 ? currPath : otherPath;
    }
}
