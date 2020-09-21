package model.tests;

import gui.GameOfLife;
import model.cards.BlueCard.BlueCard;
import model.decks.Deck;

/**
 * <h1>Test Script #8</h1>
 * <p>Generates the allows Players to draw from the BlueDeck until it is empty.</p>
 *
 * <h1>Sequence:</h1>
 * <ol>
 *     <li>Creates a GameOfLife object</li>
 *     <li>Let players draw from the BlueDeck until its empty</li>
 * </ol>
 *
 * <p>This proves that drawing a Card from the Blue Deck and activating it works.</p>
 *
 * @see GameOfLife
 * @see BlueCard
 *
 * @since 09/07/2020
 */
public class Test8 {
    public static void main(String[] args) {
        // Creates Game of Life instance
        GameOfLife gameOfLife = new GameOfLife();

        // gets the generated BlueDeck
        Deck blueDeck = gameOfLife.getBlueDeck();

        // draw BlueCards from BlueDeck and activate it until Deck is empty
        for(int i = 0; i < 20;i ++) {
            BlueCard blueCard = (BlueCard) blueDeck.pickTopCard();
            blueCard.displayCard();
            blueCard.setOwner(gameOfLife.getCurrentPlayer());
            blueCard.setOtherPlayers(gameOfLife.getOtherPlayers());
            blueCard.activate();

            // put the card back to the BlueDeck
            blueCard.setOwner(null);
            blueCard.setOtherPlayers(null);
            blueDeck.addCard(blueCard);
            blueDeck.shuffle();

            gameOfLife.setTurn(gameOfLife.getTurn() + 1);
            if(gameOfLife.getTurn() == gameOfLife.getNPlayers()) {
                gameOfLife.setTurn(0);
                gameOfLife.setRound(gameOfLife.getRound() + 1);
            }
            gameOfLife.getBlueDeck().displayDeck();
        }
    }

}
