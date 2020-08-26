package resources.decks;

import resources.cards.Card;

import java.util.ArrayList;
import java.util.Random;

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
     * @param card
     */
    public void addCard(Card card) {
        cards.add(card);
    }

    /**
     * Returns the Card which is on the top of the deck or the card at index 0
     * then removes it from the deck.
     * @return card : Card
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
     * Shuffles the cards in the deck
     */
    public void shuffle() {
        Random randomizer = new Random();
        ArrayList<Card> shuffledDeck = new ArrayList<Card>();

        // chooses a random index for a card then add it to shuffledDeck
        while(!isDeckEmpty()) {
            int random_index = randomizer.nextInt(cards.size());
            Card card = cards.get(random_index);
            shuffledDeck.add(card);
            cards.remove(card);
        }

        // adds all the shuffled deck back to the card
        for(Card card : shuffledDeck) {
            cards.add(card);
        }
    }

    /**
     * Displaying the deck (for cheating purposes?)
     */
    public void displayDeck() {
        for(int i = 0; i < cards.size(); i++) {
            Card card = cards.get(i);
            System.out.println((i + 1) + " | " + card.getType() + " | " + card.getName());
        }
    }

    /**
     * Checks whether the deck is already empty or not
     * @return Boolean
     */
    private boolean isDeckEmpty() {
        return cards.size() == 0;
    }
}
