package resources.cards.BlueCard;

final public class LawsuitCard extends BlueCard {
    final private int multiplier;

    public LawsuitCard(String career, int multiplier) {
        super("Lawsuit", "The player pays the amount indicated on the card.", career);
        this.multiplier = multiplier;
    }

    /**
     * Returns the value to be paid by the player
     * @return amount
     */
    @Override
    public double getAmount() {
        return this.multiplier * 10000;
    }
}
