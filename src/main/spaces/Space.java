package main.spaces;

import main.players.Player;
import main.utilities.StringUtil;

public abstract class Space {
    private String type, name;
    private Player player;

    public Space(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public Space(String type) {
        this.type = type;
    }

    /**
     * Set a player to the current space
     * @param player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Get the player currently in the space
     * @return player : Player
     */
    public Player getPlayer() {
        return player;
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
}
