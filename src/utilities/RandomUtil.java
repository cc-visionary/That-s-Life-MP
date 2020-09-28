package utilities;

import model.Cards.Card;
import model.Spaces.Space;

import java.util.Random;

/**
 * useful tools/functions that can be used for generating/returning random values, choosing from an array, etc.
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
     * Choose a random value between lowerBound - upperBound
     * @param lowerBound the lowest value than could be randomly chosen
     * @param upperBound the highest value than could be chosen
     * @return the randomly chosen integer
     */
    public static int chooseRandomNumber(int lowerBound, int upperBound) {
        Random randomizer = new Random();
        return randomizer.nextInt(upperBound - lowerBound + 1) + lowerBound;
    }
}
