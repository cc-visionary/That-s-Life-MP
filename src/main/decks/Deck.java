package main.decks;

import main.cards.Card;
import main.utilities.RandomUtil;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents the Deck
 *      to store the Cards. Contains methods to manipulate the cards.
 * @see Card
 */
final public class Deck {
    private String name;
    private ArrayList<Card> cards;

    public Deck(String name) {
        this.name = name;
        this.cards = new ArrayList<Card>();
    }

    public String getName() {
        return name;
    }

    /**
     * Adds a card to the deck
     * @param card Card to be added to the Deck
     */
    public void addCard(Card card) {
        cards.add(card);
    }

    /**
     * Returns the Card which is on the top of the deck or the card at index 0
     * then removes it from the deck.
     * @return the card on the top of the deck
     */
    public Card pickTopCard() {
        if(!isDeckEmpty()) {
            Card card = cards.get(0); // get the first indexed/top card
            cards.remove(0);    // removes the card that was picked (top card)
            return card;
        }
        return null;
    }

    /**
     * Shuffles the cards in the deck by using Collections.shuffle()
     */
    public void shuffle() {
        Collections.shuffle(cards);
    }

    public boolean hasCard() {
        return cards.size() > 0;
    }

    /**
     * Displaying the deck (for cheating purposes :P)
     */
    public void displayDeck() {
        for(int i = 0; i < cards.size(); i++) {
            Card card = cards.get(i);
            System.out.println(card);
        }
    }

    /**
     * Checks whether the deck is already empty or not
     * @return boolean values (true/false)
     */
    private boolean isDeckEmpty() {
        return cards.size() == 0;
    }

    @Override
    public String toString() {
        return String.format("Deck{name=%s,nCards=%d}", name, cards.size());
    }
}
