package main.cards.BlueCard;

import main.Constants;
import main.players.Player;
import main.utilities.RandomUtil;
import main.utilities.StringUtil;

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
    public double getAmount() {
        int generatedNumber = RandomUtil.chooseRandomNumber(1, 15);
        return generatedNumber * 1000;
    }
}
