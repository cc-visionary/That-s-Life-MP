package resources.utilities;

import resources.cards.Card;
import resources.players.Player;

import java.util.Random;

/**
 * Random Utilities Class
 *   useful tools/functions that can be used for
 *   generating/returning random values.
 *
 * @author        Christopher G. Lim
 * @version       1.0
 * @last_modified Aug 26, 2020
 * @since         Aug 18, 2020
 */

public class RandomUtil {
    /**
     * Randomly chooses a Card from the set of cards passed through the parameter
     * @param cards an array of Card objects to choose from
     * @return      the randomly chosen Card
     * @see         Card
     */
    public static Card chooseRandomCard(Card[] cards) {
        Random randomizer = new Random();
        int random_index = randomizer.nextInt(cards.length);
        return cards[random_index];
    }

    /**
     * Randomly chooses a Player from the set of players passed through the parameter
     * @param players an array of Player objects to choose from
     * @return        the randomly chosen Player
     * @see           Player
     */
    public static Player chooseRandomPlayer(Player[] players) {
        Random randomizer = new Random();
        int random_index = randomizer.nextInt(players.length);
        return players[random_index];
    }

    /**
     * Choose a random value between lowerBound - upperBound
     * @param lowerBound the lowest value than could be randomly chosen
     * @param upperBound the highest value than could be chosen
     * @return the randomly chosen integer
     */
    public static int chooseRandomNumber(int lowerBound, int upperBound) {
        Random randomizer = new Random();
        return randomizer.nextInt(upperBound) + lowerBound;
    }
}
