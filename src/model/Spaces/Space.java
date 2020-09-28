package model.Spaces;

import model.Players.Player;
import utilities.StringUtil;

import java.util.ArrayList;

/**
 * Represents the Space in the Board.
 *      This will be inherited by different types of Spaces which performs certain sets of operations to the Player.
 */
public abstract class Space {
    private String type, name;
    private ArrayList<Player> players;

    public Space(String type, String name) {
        this.type = type;
        this.name = name;
        this.players = new ArrayList<Player>();
    }

    public Space(String type) {
        this.type = type;
        this.players = new ArrayList<Player>();
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * Adds a player to the space
     * @param player which will be added
     */
    public void addPlayer(Player player) {
        players.add(player);
    }

    /**
     * Removes a player to the space
     * @param player which will be removed
     */
    public void removePlayer(Player player) {
        players.remove(player);
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

    @Override
    public String toString() {
        return String.format("Space{type=%s,name=%s}", type, name);
    }
}
