package model.Spaces.BlueSpace;

import model.Constants;
import model.Cards.BlueCard.BlueCard;
import model.Decks.Deck;
import model.Spaces.Space;

/**
 * Represents the Blue Space
 *      where when a Player lands in this space, he/she simply picks an Blue Card from the Blue Cards Deck
 */
final public class BlueSpace extends Space {
    public BlueSpace() {
        super(Constants.BLUE_SPACE);
    }

    /**
     * Picks the Top Card from the Blue Deck (composed of BlueCard)
     * @param blueDeck blue deck to be drawn from
     * @return         top card from the blue deck
     */
    public BlueCard pickCard(Deck blueDeck) {
        BlueCard pickedCard;
        if(blueDeck.getName() == "Blue Deck") {
            pickedCard = (BlueCard) blueDeck.pickTopCard();
        } else {
            pickedCard = null;
            System.out.println("An incorrect deck(" + blueDeck.getName() + ") was passed.");
        }
        return pickedCard;
    }
}
