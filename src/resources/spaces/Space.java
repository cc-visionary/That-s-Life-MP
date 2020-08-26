package resources.spaces;

import resources.players.Player;
import resources.utilities.StringUtil;

public abstract class Space {
    private String type = "Space", name;
    private Player player;

    public Space(String name) {
        this.name = name;
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
        System.out.println("│" + StringUtil.centerString(getName().charAt(0), length) + "│");
        System.out.println("╰─────╯");
    }
}
