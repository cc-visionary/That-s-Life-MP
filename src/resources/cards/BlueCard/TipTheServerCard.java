package resources.cards.BlueCard;

import resources.players.Player;
import resources.utilities.RandomUtil;

final public class TipTheServerCard extends BlueCard {
    public TipTheServerCard(String career) {
        super("Tip the Server", "The player presses a random number then pays the generated number x $1000", career);
    }

    /**
     * Returns the random generated number * 1000
     * @param player the player who drew the card
     * @return the generated number * 1000
     */
    @Override
    public double getAmount(Player player) {
        int generatedNumber = RandomUtil.chooseRandomNumber(1, 15);
        return generatedNumber * 1000;
    }
}
