package main.cards.BlueCard;

import main.players.Player;

final public class LawsuitCard extends BlueCard {
    final private int multiplier;

    public LawsuitCard(String career, int multiplier) {
        super("Lawsuit", "The player pays the amount indicated on the card.", career);
        this.multiplier = multiplier;
    }

    /**
     * Returns the value to be paid by the player
     * @param player the player who drew the card
     * @return multiplier * 10000
     */
    @Override
    public double getAmount(Player player) {
        return this.multiplier * 10000;
    }
}
