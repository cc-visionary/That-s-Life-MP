package main.spaces;

import main.players.Player;
import main.utilities.StringUtil;

/**
 * Represents the Space in the Board.
 *      This will be inherited by different types of Spaces which performs certain sets of operations to the Player.
 */
public abstract class Space {
    private String type, name;

    public Space(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public Space(String type) {
        this.type = type;
    }

    /**
     * Returns the value of the type
     * @return type : String
     */
    public String getType() {
        return type;
    }

    /**
     * Returns the value of the name
     * @return name : String
     */
    public String getName() {
        return name;
    }

    /**
     * Display the space into a 3(height) x 6(width) unit
     */
    public void displaySpace() {
        final int length = 5;
        System.out.println("╭─────╮");
        System.out.println("│" + StringUtil.centerString(getType().charAt(0)  + "", length) + "│");
        System.out.println("╰─────╯");
    }

    @Override
    public String toString() {
        return String.format("Space{type=%s,name=%s}", type, name);
    }
}
