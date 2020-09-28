package model.Cards.BlueCard;

import model.Constants;
import utilities.RandomUtil;

/**
 * Represents the Blue Card - Tip the Server Card
 */

final public class TipTheServerCard extends BlueCard {
    public TipTheServerCard() {
        super("Tip the Server", "The player presses a random number then pays the generated number x $1000", Constants.SERVER);
    }

    /**
     * Returns the random generated number * 1000
     * @return the generated number * 1000
     */
    @Override
    public int getAmount() {
        int generatedNumber = RandomUtil.chooseRandomNumber(1, 15);
        return generatedNumber * 1000;
    }
}
