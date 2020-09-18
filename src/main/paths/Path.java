package main.paths;
import main.players.Player;
import main.spaces.Space;

import java.util.ArrayList;

/**
 * Represents the Path which stores List of Spaces and its connecting Paths path1(left node), and path2(right node)
 * @see Space
 */

final public class Path {
    private String type = "Path", name, uniqueName, description;
    private Space spaces[];
    private Path path1, path2;

    public Path(String name, String uniqueName, Space[] spaces, Path path1, Path path2) {
        this.name = name;
        this.uniqueName = uniqueName;
        this.spaces = spaces;
        this.path1 = path1;
        this.path2 = path2;
    }

    public Path(String name, String uniqueName, Space[] spaces, Path path1, Path path2, String description) {
        this.name = name;
        this.uniqueName = uniqueName;
        this.spaces = spaces;
        this.path1 = path1;
        this.path2 = path2;
        this.description = description;
    }

    public Path(String name, String uniqueName, Space[] spaces) {
        this.name = name;
        this.uniqueName = uniqueName;
        this.spaces = spaces;
    }

    public Path(String name, String uniqueName, Space[] spaces, String description) {
        this.name = name;
        this.uniqueName = uniqueName;
        this.spaces = spaces;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    /**
     * Gets the Junction located at the last space
     * @return the Which Path space
     */
    public Space getJunction() {
        return spaces[getNSpaces() - 1];
    }

    @Override
    public String toString() {
        return String.format("Path{name=%s,uniqueName=%s,nSpaces=%d}", getName(), getUniqueName(), getNSpaces());
    }
}
