package main.spaces.OrangeSpace;

import main.cards.Card;
import main.decks.Deck;
import main.spaces.Space;

/**
 * Represents the Orange Space
 *      where when a Player lands in this space, he/she simply picks an Action Card from the Action Cards Deck.
 */
final public class OrangeSpace extends Space {
    public OrangeSpace() {
        super("Orange Space");
    }

    /**
     * Picks the Top Card from the Orange Deck (composed of ActionCard)
     * @param orangeDeck orange deck to be drawn from
     * @return           top card from the orange deck
     */
    public Card pickCard(Deck orangeDeck) {
        Card pickedCard;
        if(orangeDeck.getName() == "Orange Deck") {
            pickedCard = orangeDeck.pickTopCard();
        } else {
            pickedCard = null;
            System.out.println("An incorrect deck(" + orangeDeck.getName() + ") was passed.");
        }
        return pickedCard;
    }
}
