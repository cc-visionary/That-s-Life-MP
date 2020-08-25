package resources.cards.BlueCard;

final public class ComputerRepairCard extends BlueCard {
    public ComputerRepairCard(String career) {
        super("Computer Repair", "The player presses for a random number. He pays $5000 if the number is even and $10000 if the number is odd.", career);
    }

    /**
     * Returns the value to be paid by the player
     * @return amount
     */
    @Override
    public double getAmount() {
        int generatedNumber = 1;
        if(generatedNumber % 2 == 0) {
            return 5000;
        } else {
            return 10000;
        }
    }
}
