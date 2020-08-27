package main.paths;
import main.spaces.Space;

public class Path {
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
}