package resources.cards.ActionCard;

import resources.players.Player;

final public class PayAllCard extends ActionCard {
    public PayAllCard(String name, String description, double amount) {
        super(name, description, amount);
    }

    /**
     * Does the action of the ActionCard (Pay to All the Players)
     * @param player
     * @param otherPlayers
     */
    @Override
    public void activate(Player player, Player[] otherPlayers) {
        // deducts the number of players * amount from the player who drew the card's balance
        player.payBalance(otherPlayers.length * getAmount());

        // then gives it to all the other players seperately
        for(Player otherPlayer : otherPlayers) {
            otherPlayer.addBalance(getAmount());
        }
    }
}
