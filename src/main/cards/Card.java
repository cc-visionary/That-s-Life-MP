package main.cards;

import main.utilities.StringUtil;

/**
 * Abstract Card Class
 *
 * @author      Christopher G. Lim
 * @version     1.0
 * @last-edited Aug 18, 2020
 * @since       Aug 18, 2020
 */

public abstract class Card {
    private String type, name;
    private String description;

    /**
     * Constructor assigns the parameter description
     * to the private variable description
     * @param description
     */
    public Card(String type, String name, String description) {
        this.type = type;
        this.name = name;
        this.description = description;
    }

    public Card(String type, String description) {
        this.type = type;
        this.description = description;
    }

    /**
     * Returns the value of the type
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * Returns the value of the name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the value of the description
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method displays the card into a 12(height) x 25(width) unit layout
     * @return None
     */
    public void  displayCard() {
        final int length = 23, descriptionHeight = 8;
        String[] splittedString = StringUtil.splitStringLength(getDescription(), length).toArray(new String[0]);

        System.out.println("╭───────────────────────╮");
        System.out.println("│" + StringUtil.centerString(getType(), length)                  + "│");
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

    @Override
    public String toString() {
        return String.format("Card{type=%sname=%s}", getType(), getName());
    }
}
