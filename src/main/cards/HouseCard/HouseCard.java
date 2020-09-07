package main.cards.HouseCard;

import main.Constants;
import main.cards.Card;

/**
 * Represents the  House Card
 *      which contains details about a House which can be bought by the Player in BuyAHouseSpace
 * @see main.spaces.MagentaSpace.BuyAHouseSpace
 */

public class HouseCard extends Card {
    private double cost;
    public HouseCard(String name, String description, double cost) {
        super(Constants.HOUSE_CARD, name, description);
        this.cost = cost;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return String.format("HouseCard{name=%s,cost=%.2f}", getName(), getCost());
    }
}
