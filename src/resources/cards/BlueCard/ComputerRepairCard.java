package resources.cards.BlueCard;

import resources.players.Player;
import resources.utilities.RandomUtil;

final public class ComputerRepairCard extends BlueCard {
    public ComputerRepairCard(String career) {
        super("Computer Repair", "The player presses for a random number. He pays $5000 if the number is even and $10000 if the number is odd.", career);
    }

    /**
     * Returns the 5000 if the generatedNumber is even and 10000 if odd
     * @param player the player who drew the card
     * @return amount to be paid by the player
     */
    @Override
    public double getAmount(Player player) {
        int generatedNumber = RandomUtil.chooseRandomNumber(1, 10);
        if(generatedNumber % 2 == 0) {
            return 5000;
        } else {
            return 10000;
        }
    }
}
