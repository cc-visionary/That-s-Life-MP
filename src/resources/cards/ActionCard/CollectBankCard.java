package resources.cards.ActionCard;

import resources.players.Player;

final public class CollectBankCard extends ActionCard {
    public CollectBankCard(String name, String description, double amount) {
        super(name, description, amount);
    }

    /**
     * Does the action of the ActionCard (Collect from Bank)
     * @param player
     * @param otherPlayers
     */
    @Override
    public void activate(Player player, Player[] otherPlayers) {
        // adds the amount to the balance of the person who drew the card
        player.addBalance(getAmount());
    }
}
