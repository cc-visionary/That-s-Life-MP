package model.cards.BlueCard;

import model.Constants;
import utilities.RandomUtil;

/**
 * Represents the Blue Card - Computer Repair Card
 */

final public class ComputerRepairCard extends BlueCard {
    public ComputerRepairCard() {
        super("Computer Repair", "The player presses for a random number. He pays $5000 if the number is even and $10000 if the number is odd.", Constants.COMPUTER_CONSULTANT);
    }

    /**
     * Returns the 5000 if the generatedNumber is even and 10000 if odd
     * @return amount to be paid by the player
     */
    @Override
    public double getAmount() {
        int generatedNumber = RandomUtil.chooseRandomNumber(1, 10);
        if(generatedNumber % 2 == 0) {
            return 5000;
        } else {
            return 10000;
        }
    }
}
