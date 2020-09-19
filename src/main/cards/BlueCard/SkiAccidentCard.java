package main.cards.BlueCard;

import main.Constants;
import main.players.Player;
import main.utilities.StringUtil;

/**
 * Represents the Blue Card - SkiAccident Card
 */

final public class SkiAccidentCard extends BlueCard {
    public SkiAccidentCard() {
        super("Ski Accident", "The player pays $10000.", Constants.DOCTOR);
    }

    /**
     * Returns the value to be paid by the player
     * @return 10000
     */
    @Override
    public double getAmount() {
        return 10000;
    }
}
