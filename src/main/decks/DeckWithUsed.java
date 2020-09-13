package main.decks;

import main.cards.Card;

import java.util.ArrayList;
import java.util.Collections;

public class DeckWithUsed extends Deck {
    ArrayList<Card> usedCards;

    public DeckWithUsed(String name) {
        super(name);
    }

    public void addUsed(Card card) {
        usedCards.add(card);
    }

    /**
     * Returns the Card which is on the top of the deck or the card at index 0
     * then removes it from the deck.
     * If deck is empty, add all the cards from the used cards back into the deck
     * @return the card on the top of the deck with offset
     */
    @Override
    public Card pickTopCard() {
        if(isDeckEmpty()) { // if deck is empty, add all the used cards to the deck and shuffle
            returnUsedCards();
            System.out.println("All used cards are added back to the deck and shuffled");
        }

        Card card = getCards().get(0); // get the first indexed/top card
        usedCards.add(card);           // add to used cards
        getCards().remove(0);    // removes the card that was picked (top card)
        return card;
    }

    /**
     * Returns the Card which is on the top of the deck or the card at index 0
     * then removes it from the deck.
     * If deck is empty, add all the cards from the used cards back into the deck
     * @param offset the offset/index
     * @return the card on the top of the deck with offset
     */
    @Override
    public Card pickTopCard(int offset) {
        if(isDeckEmpty()) { // if deck is empty, add all the used cards to the deck and shuffle
            returnUsedCards();
            System.out.println("All used cards are added back to the deck and shuffled");
        }

        Card card = getCards().get(offset); // get the first indexed/top card
        usedCards.add(card);          // add to used cards
        getCards().remove(offset);    // removes the card that was picked (top card)
        return card;
    }

    /**
     * Adds/returns all the used Card back into the Deck
     */
    public void returnUsedCards() {
        for(Card usedCard : usedCards) {
            getCards().add(usedCard);
        }

        Collections.shuffle(usedCards);
    }
}
