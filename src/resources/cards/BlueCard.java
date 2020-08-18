package resources.cards;

import resources.utilities.StringUtil;

/**
 * Blue Card Class
 *
 * @author      Christopher G. Lim
 * @version     1.0
 * @last-edited Aug 18, 2020
 * @since       Aug 18, 2020
 */

public class BlueCard extends Card {
    private String name;
    private String career;
    private double amount;

    public BlueCard(String name, String description, String career) {
        super("Blue Card", description);

        this.name = name;
        this.career = career;
    }

    /**
     * Returns the name of the Blue Card
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the name of the Career
     * @return career
     */
    public String getCareer() {
        return career;
    }

    /**
     * Sets the value of the amount if parameter amount > 0
     * @param amount
     */
    public void setAmount(double amount) {
        if(amount > 0) {
            this.amount = amount;
        }
    }

    /**
     * Returns the value of the amount
     * @return amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * This method displays the card into a 14(max height) x 25(width) unit layout
     * @return None
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
