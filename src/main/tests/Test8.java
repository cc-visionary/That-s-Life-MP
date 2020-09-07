package main.tests;

import main.GameOfLife;
import main.Generator;
import main.cards.BlueCard.BlueCard;
import main.decks.Deck;
import main.utilities.InputUtil;

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
        while(blueDeck.hasCard()) {
            BlueCard blueCard = (BlueCard) blueDeck.pickTopCard();
            blueCard.displayCard();
            blueCard.setOwner(gameOfLife.getCurrentPlayer());
            blueCard.setOtherPlayers(gameOfLife.getOtherPlayers());
            blueCard.activate();
            gameOfLife.setTurn(gameOfLife.getTurn() + 1);
            gameOfLife.displayStats();
            if(gameOfLife.getTurn() == gameOfLife.getNPlayers()) {
                gameOfLife.setTurn(0);
                gameOfLife.setRound(gameOfLife.getRound() + 1);
            }
        }
    }

}
