package main.spaces.MagentaSpace;

import main.cards.Card;
import main.cards.HouseCard.HouseCard;
import main.decks.Deck;
import main.utilities.InputUtil;

/**
 * Represents the Magenta Space - Buy a House Space
 *      allows the Player to have an option whether to buy a house by choosing from the
 *      house Deck or not
 */

final public class BuyAHouseSpace extends MagentaSpace {
    public BuyAHouseSpace() {
        super("Buy a House");
    }

    /**
     * Allows a Player to buy a HouseCard from the House Deck
     * @param houseDeck house deck which Player can choose from
     * @return          HouseCard chosen by the Player
     */
    public HouseCard pickCard(Deck houseDeck) {
        HouseCard pickedCard;
        int n = 1;
        if(houseDeck.getName() == "House Deck") {
            System.out.println("List of Houses:");
            for(Card card : houseDeck.getCards()) {
                System.out.println("\t" + n + ":" + (HouseCard) card);
                n++;
            }
            int choice = InputUtil.scanInt("Enter House:", 1, houseDeck.getCards().length);
            pickedCard = (HouseCard) houseDeck.getCards()[choice - 1];
        } else {
            pickedCard = null;
            System.out.println("An incorrect deck was passed.");
        }
        return pickedCard;
    }
}
