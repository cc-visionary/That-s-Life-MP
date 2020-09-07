package main.paths;
import main.players.Player;
import main.spaces.Space;

import java.util.ArrayList;

/**
 * Represents the Path which stores List of Spaces and its connecting Paths path1(left node), and path2(right node)
 * @see Space
 */

final public class Path {
    private String type = "Path", name, uniqueName;
    private Space spaces[];
    private ArrayList<Player> players;
    private Path path1, path2;

    public Path(String name, String uniqueName, Space[] spaces, Path path1, Path path2) {
        this.name = name;
        this.uniqueName = uniqueName;
        this.spaces = spaces;
        this.path1 = path1;
        this.path2 = path2;
        this.players = new ArrayList<Player>();
    }

    public Path(String name, String uniqueName, Space[] spaces) {
        this.name = name;
        this.uniqueName = uniqueName;
        this.spaces = spaces;
        this.players = new ArrayList<Player>();
    }

    public String getUniqueName() {
        return uniqueName;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Space[] getSpaces() {
        return spaces;
    }

    public Path getPath1() {
        return path1;
    }

    public Path getPath2() {
        return path2;
    }

    public int getNSpaces() {
        return spaces.length;
    }

    /**
     * Gets the Junction located at the last space
     * @return the Which Path space
     */
    public Space getJunction() {
        return spaces[getNSpaces() - 1];
    }

    /**
     * Add a player to the list of the players of the Path
     * @param player the player to be added
     */
    public void addPlayer(Player player) {
        this.players.add(player);
    }

    /**
     * Removes a player from the list of the players of the Path
     * @param player the player to be removed
     */
    public void removePlayer(Player player) {
        this.players.remove(player);
    }

    public void displayPath() {

    }

    @Override
    public String toString() {
        return String.format("Path{name=%s,uniqueName=%s,nSpaces=%d}", getName(), getUniqueName(), getNSpaces());
    }
}
