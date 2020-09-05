package main.paths;
import main.players.Player;
import main.spaces.Space;

import java.util.ArrayList;

/**
 * Represents the Path
 *      which stores List of Spaces
 * @see Space
 */

final public class Path {
    private static int pathCount = 0;
    private String type = "Path", name, uniqueName;
    private Space spaces[];
    private ArrayList<Player> players;
    private Path path1, path2;

    public Path(String name, Space[] spaces, Path path1, Path path2) {
        this.uniqueName = name + " - " + pathCount;
        this.name = name;
        this.spaces = spaces;
        this.path1 = path1;
        this.path2 = path2;
        this.players = new ArrayList<Player>();
    }

    public Path(String name, Space[] spaces) {
        this.uniqueName = name + " - " + pathCount;
        this.name = name;
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

    public int getPathCount() {
        return pathCount;
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
        return String.format("Path{name=%s,nSpaces=%d,path1=%s,path2=%s}", name, spaces.length, path1, path2);
    }
}
