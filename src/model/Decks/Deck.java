package model.Decks;

import model.Cards.Card;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents the Deck
 *      which stores the Cards and contains methods to manipulate the cards within and outside the Deck.
 * @see Card
 */
public class Deck {
    private String name;
    private ArrayList<Card> cards;

    public Deck(String name) {
        this.name = name;
        this.cards = new ArrayList<Card>();
    }

    public String getName() {
        return name;
    }
    public ArrayList<Card> getCards() {
        return cards;
    }
    public int getNCards() {
        return cards.size();
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
        if(hasCard()) {
            Card card = cards.get(0); // get the first indexed/top card
            cards.remove(0);    // removes the card that was picked (top card)
            return card;
        } else {
            System.out.println("No more card to pick... Deck is Empty.");
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

    public void displayDeck() {
        System.out.println("\nDisplaying " + getName() + " Deck");
        for(Card card : getCards()) {
            System.out.println(card);
        }
    }

    @Override
    public String toString() {
        return String.format("Deck{name=%s,nCards=%d}", name, cards.size());
    }
}
