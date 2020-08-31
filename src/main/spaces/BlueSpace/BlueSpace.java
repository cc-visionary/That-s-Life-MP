package main.spaces.BlueSpace;

import main.cards.Card;
import main.decks.Deck;
import main.spaces.Space;

/**
 * Represents the Blue Space
 *      where when a Player lands in this space, he/she simply picks an Blue Card from the Blue Cards Deck
 */
final public class BlueSpace extends Space {
    public BlueSpace() {
        super("Blue Space");
    }

    /**
     * Picks the Top Card from the Blue Deck (composed of BlueCard)
     * @param blueDeck blue deck to be drawn from
     * @return         top card from the blue deck
     */
    public Card pickCard(Deck blueDeck) {
        Card pickedCard;
        if(blueDeck.getName() == "Blue Deck") {
            pickedCard = blueDeck.pickTopCard();
        } else {
            pickedCard = null;
            System.out.println("An incorrect deck(" + blueDeck.getName() + ") was passed.");
        }
        return pickedCard;
    }
}
