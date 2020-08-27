package main.cards.BlueCard;

import main.cards.Card;
import main.utilities.StringUtil;
import main.players.Player;

import java.util.ArrayList;

/**
 * Blue Card Class
 *
 * @author      Christopher G. Lim
 * @version     1.0
 * @last-edited Aug 18, 2020
 * @since       Aug 18, 2020
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
     *
     * @param player
     * @param otherPlayers
     */
    public void activate(Player player, Player[] otherPlayers) {
        if(isSameCareer(player)) { // player receives 15000
            player.addBalance(15000);
            System.out.println(player.getName() + " receives $15000");
        } else {
            double amountToBePayed = getAmount(player);

            Player[] playersWithSameCareer = playersWithSameCareer(otherPlayers).toArray(new Player[0]);
            if(playersWithSameCareer.length > 0) { // player pays all the players with the same career
                for(Player otherPlayer : playersWithSameCareer) {
                    player.payBalance(amountToBePayed);
                    otherPlayer.addBalance(amountToBePayed);
                    System.out.println(player.getName() + " paid $" + amountToBePayed + " to " + otherPlayer.getName());
                }
            } else { // player pays the bank
                player.payBalance(amountToBePayed);
                System.out.println(player.getName() + " paid $" + amountToBePayed + " to the bank.");
            }
        }
    }

    public abstract double getAmount(Player player);

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
}
