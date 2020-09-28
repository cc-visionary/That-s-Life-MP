package tests;

import model.Cards.CareerCard.CareerCard;
import model.Cards.SalaryCard.SalaryCard;
import model.GameOfLife;
import model.Cards.BlueCard.BlueCard;
import model.Decks.Deck;
import model.Players.Player;

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
        GameOfLife gameOfLife = new GameOfLife(2, 200000);

        for(Player player : gameOfLife.getAllActivePlayers()) {
            CareerCard careerCard = (CareerCard) gameOfLife.getCareerDeck().pickTopCard();
            SalaryCard salaryCard = (SalaryCard) gameOfLife.getSalaryDeck().pickTopCard();
            player.setName("P" + player.getNthPlayer());
            player.setPath(gameOfLife.getCareerPath());
            player.setCareerCard(careerCard);
            player.setSalaryCard(salaryCard);
        }

        // gets the generated BlueDeck
        Deck blueDeck = gameOfLife.getBlueDeck();

        // draw BlueCards from BlueDeck and activate it until Deck is empty
        for(int i = 0; i < 20;i ++) {
            BlueCard blueCard = (BlueCard) blueDeck.pickTopCard();
            System.out.println(blueCard);
            blueCard.setOwner(gameOfLife.getCurrentPlayer());
            blueCard.setOtherPlayers(gameOfLife.getOtherPlayers());
            blueCard.activate();

            // put the card back to the BlueDeck
            blueCard.setOwner(null);
            blueCard.setOtherPlayers(null);
            blueDeck.addCard(blueCard);
//            blueDeck.shuffle();

            gameOfLife.addTurn();
            gameOfLife.getBlueDeck().displayDeck();
        }

        for(Player player : gameOfLife.getAllActivePlayers()) {
            System.out.println(player);
        }
    }
}
