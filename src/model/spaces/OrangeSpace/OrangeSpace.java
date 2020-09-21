package model.spaces.OrangeSpace;

import model.Constants;
import model.cards.ActionCard.ActionCard;
import model.decks.Deck;
import model.spaces.Space;

/**
 * Represents the Orange Space
 *      where when a Player lands in this space, he/she simply picks an Action Card from the Action Cards Deck.
 */
final public class OrangeSpace extends Space {
    public OrangeSpace() {
        super(Constants.ORANGE_SPACE);
    }

    /**
     * Picks the Top Card from the Orange Deck (composed of ActionCard)
     * @param orangeDeck orange deck to be drawn from
     * @return           top card from the orange deck
     */
    public ActionCard pickCard(Deck orangeDeck) {
        ActionCard pickedCard;
        if(orangeDeck.getName() == "Orange Deck") {
            pickedCard = (ActionCard) orangeDeck.pickTopCard();
        } else {
            pickedCard = null;
            System.out.println("An incorrect deck(" + orangeDeck.getName() + ") was passed.");
        }
        return pickedCard;
    }
}
