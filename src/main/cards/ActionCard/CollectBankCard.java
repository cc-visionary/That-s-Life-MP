package main.cards.ActionCard;

import main.players.Player;

/**
 * Represents the Action Card which requires a Player to Collect money from the Bank
 */

final public class CollectBankCard extends ActionCard {
    public CollectBankCard(String name, String description, double amount) {
        super(name, description, amount);
    }

    /**
     * Does the action of the ActionCard (Collect from Bank)
     * @param player       current Player who drew the card
     * @param otherPlayers other Players
     */
    @Override
    public void activate(Player player, Player[] otherPlayers) {
        // adds the amount to the balance of the person who drew the card
        player.addBalance(getAmount());

        System.out.println(player.getName() + " received $" + getAmount() + " from the bank");
    }

    @Override
    public String toString() {
        return String.format("ActionCard{name=%s,amount=%.2f,transaction=COLLECT,recipient=BANK}", getName(), getAmount());
    }
}
