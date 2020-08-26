package resources.cards.ActionCard;

import resources.players.Player;

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
    public void doAction(Player player, Player[] otherPlayers) {
        // deducts the amount indicated on the card to the player's balance
        player.payBalance(getAmount());
    }
}
