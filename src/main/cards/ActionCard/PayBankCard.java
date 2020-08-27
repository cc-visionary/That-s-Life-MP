package main.cards.ActionCard;

import main.players.Player;

final public class PayBankCard extends ActionCard {
    public PayBankCard(String name, String description, double amount) {
        super(name, description, amount);
    }

    /**
     * Does the action of the ActionCard (Pay to the Bank)
     * @param player
     * @param otherPlayers
     */
    @Override
    public void activate(Player player, Player[] otherPlayers) {
        // deducts the amount indicated on the card to the player's balance
        player.payBalance(getAmount());
    }
}
