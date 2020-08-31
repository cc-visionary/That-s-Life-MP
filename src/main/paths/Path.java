package main.paths;
import main.spaces.Space;

/**
 * Represents the Path
 *      which stores List of Spaces
 * @see Space
 */

final public class Path {
    private String type = "Path", name;
    private Space spaces[];

    public Path(String name, Space[] spaces) {
        this.name = name;
        this.spaces = spaces;
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

    public boolean hasReachedJunction() {
        return true;
    }

    public void displayPath() {

    }

    @Override
    public String toString() {
        return String.format("Path{name=%s,nSpaces=%d}", name, spaces.length);
    }
}
