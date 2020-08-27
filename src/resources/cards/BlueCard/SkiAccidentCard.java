package resources.cards.BlueCard;

import resources.players.Player;

final public class SkiAccidentCard extends BlueCard {
    public SkiAccidentCard(String career) {
        super("Ski Accident", "The player pays $10000.", career);
    }

    /**
     * Returns the value to be paid by the player
     * @param player the player who drew the card
     * @return 10000
     */
    @Override
    public double getAmount(Player player) {
        return 10000;
    }
}
