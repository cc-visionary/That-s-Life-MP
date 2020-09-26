package model.Cards.BlueCard;

import model.Constants;

/**
 * Represents the Blue Card - Lawsuit Card
 */

final public class LawsuitCard extends BlueCard {
    final private int multiplier;

    public LawsuitCard(int multiplier) {
        super("Lawsuit", "The player pays the amount indicated on the card.", Constants.LAWYER);
        this.multiplier = multiplier;
    }

    /**
     * Returns the value to be paid by the player
     * @return multiplier * 10000
     */
    @Override
    public int getAmount() {
        return this.multiplier * 10000;
    }
}
