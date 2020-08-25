package resources.cards.BlueCard;

import resources.cards.Card;
import resources.utilities.StringUtil;
import resources.players.Player;

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
        super("Blue Card", name, description);

        this.career = career;
    }

    /**
     * Returns an ArrayList of other Players which has the same career as the card (to be paid to)
     * @param otherPlayers : Player[]
     * @return players : ArrayList<Player></Player>
     */
    public ArrayList<Player> playersWithSameCareer(Player[] otherPlayers) {
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
    public boolean isSameCareer(Player player) {
        return true;
//        return player.getCareerCard().getName() == this.career;
    }

    /**
     * Returns the name of the Career
     * @return career : String
     */
    public String getCareer() {
        return career;
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
        System.out.println("╰───────────────────────╯");
    }
}
