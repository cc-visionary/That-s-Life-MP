package main.cards.BlueCard;

import main.players.Player;
import main.utilities.StringUtil;

/**
 * Represents the Blue Card - World Cup Card
 */

final public class WorldCupCard extends BlueCard {
    final private int numOfPlayers;

    public WorldCupCard(String career, int numOfPlayers) {
        super("World Cup", "The player pays number of player x $5000", career);

        this.numOfPlayers = numOfPlayers;
    }

    /**
     * Returns the value to be paid by the player
     * @return number of players * 5000
     */
    @Override
    public double getAmount() {
        return this.numOfPlayers * 5000;
    }
}
