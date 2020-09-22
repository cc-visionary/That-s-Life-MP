package model.spaces.MagentaSpace;

import gui.modals.Modal;
import model.Constants;
import model.cards.Card;
import model.cards.HouseCard.HouseCard;
import model.decks.Deck;
import utilities.InputUtil;

/**
 * Represents the Magenta Space - Buy a House Space
 *      allows the Player to have an option whether to buy a house by choosing from the
 *      house Deck or not
 */

final public class BuyAHouseSpace extends MagentaSpace {
    public BuyAHouseSpace() {
        super(Constants.BUY_A_HOUSE);
    }

    /**
     * Allows a Player to buy a HouseCard from the House Deck
     * @param houseDeck house deck which Player can choose from
     * @return          HouseCard chosen by the Player
     */
    public HouseCard pickCard(Deck houseDeck) {
        HouseCard pickedCard;
        if(houseDeck.getName() == "House Deck") {
            // lets a Player choose a House through GUI
            pickedCard = new Modal().chooseHouse(houseDeck);
            // removes the card
            houseDeck.getCards().remove(pickedCard);
        } else {
            pickedCard = null;
            System.out.println("An incorrect deck was passed.");
        }
        return pickedCard;
    }
}
