package resources.cards.BlueCard;

public class SkiAccidentCard extends BlueCard {
    public SkiAccidentCard(String career) {
        super("Ski Accident", "The player pays $10000.", career);
    }

    /**
     * Returns the value to be paid by the player
     * @return amount
     */
    @Override
    public double getAmount() {
        return 10000;
    }
}
