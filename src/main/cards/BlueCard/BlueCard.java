package main.cards.BlueCard;

import main.cards.Card;
import main.utilities.StringUtil;
import main.players.Player;

import java.util.ArrayList;

/**
 * Represents the Blue Card
 *      inherited by different types of Blue Cards
 */

public abstract class BlueCard extends Card {
    private String career;

    public BlueCard(String name, String description, String career) {
        super("Blue Card", name, "If player's Career is not the same with the Card's, " + description);

        this.career = career;
    }

    /**
     * Returns an ArrayList of other Players which has the same career as the card (to be paid to)
     * @param otherPlayers : Player[]
     * @return players : ArrayList<Player></Player>
     */
    private ArrayList<Player> playersWithSameCareer(Player[] otherPlayers) {
        ArrayList<Player> players = new ArrayList<Player>();

        for(Player player : otherPlayers) {
            if(isSameCareer(player)) {
                players.add(player);
            }
        }

        return players;
    }

    /**
     * Checks whether a Player's Career Card is the same career with the card
     * @param player : Player
     * @return true/false : boolean
     */
    private boolean isSameCareer(Player player) {
        return player.getCareerCard().getName() == this.career;
    }

    /**
     * Returns the name of the Career
     * @return career : String
     */
    public String getCareer() {
        return career;
    }

    /**
     * Activates the card by checking if the player who drew the card
     * has the same career with the card. If yes, player receives $15000.
     * If not, player pays the other players with that career / bank.
     */
    public void activate() {
        if(isSameCareer(getOwner())) { // player receives 15000
            getOwner().addBalance(15000);
            System.out.println(getOwner().getName() + " receives $15000");
        } else {
            double amountToBePayed = getAmount();

            Player[] playersWithSameCareer = playersWithSameCareer(getOtherPlayers()).toArray(new Player[0]);
            if(playersWithSameCareer.length > 0) { // player pays all the players with the same career
                for(Player otherPlayer : playersWithSameCareer) {
                    getOwner().payBalance(amountToBePayed);
                    otherPlayer.addBalance(amountToBePayed);
                    System.out.println(getOwner().getName() + " paid $" + amountToBePayed + " to " + otherPlayer.getName());
                }
            } else { // player pays the bank
                getOwner().payBalance(amountToBePayed);
                System.out.println(getOwner().getName() + " paid $" + amountToBePayed + " to the bank.");
            }
        }
    }

    public abstract double getAmount();

    /**
     * This method displays the card into a 14(max height) x 25(width) unit layout
     */
    @Override
    public void  displayCard() {
        final int length = 23, descriptionHeight = 8;
        String[] splittedString = StringUtil.splitStringLength(getDescription(), length).toArray(new String[0]);

        System.out.println("╭───────────────────────╮");
        System.out.println("│" + StringUtil.centerString(getType(), length)                  + "│");
        System.out.println("│" + StringUtil.centerString("(" + getName() + ")", length) + "│");
        System.out.println("├───────────────────────┤");
        for(int i = 0; i < descriptionHeight; i++) {
            if(i < splittedString.length) {
                System.out.println("│" + StringUtil.centerString(splittedString[i], length)  + "│");
            } else {
                System.out.println("│" + StringUtil.centerString("", length)            + "│");
            }
        }
        System.out.println("├───────────────────────┤");
        System.out.println("│" + StringUtil.centerString("Career: " + getCareer(), length) + "│");
        System.out.println("╰───────────────────────╯");
    }

    @Override
    public String toString() {
        return String.format("BlueCard{name=%s,career=%s}", getName(), getCareer());
    }
}
