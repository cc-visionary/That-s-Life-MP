package resources.cards;

/**
 * Abstract Card Class
 *
 * @author      Christopher G. Lim
 * @version     1.0
 * @last-edited Aug 18, 2020
 * @since       Aug 18, 2020
 */

public abstract class Card {
    protected String type = "Card";
    private String description;

    /**
     * Constructor assigns the parameter description
     * to the private variable description
     * @param description
     */
    public Card(String description) {
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
     * Returns the value of the description
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method displays the card into a 12(height) x 20(width) unit layout
     * @return None
     */
    public void  displayCard() {
        System.out.printf("╭──────────────────╮\n");
        System.out.printf("│ %16s │\n", type);
        System.out.printf("│ %16s │\n", "");
        System.out.printf("│ %16s │\n", "");
        System.out.printf("│ %16s │\n", "");
        System.out.printf("│ %16s │\n", "");
        System.out.printf("│ %16s │\n", "");
        System.out.printf("│ %16s │\n", "");
        System.out.printf("│ %16s │\n", "");
        System.out.printf("│ %16s │\n", "");
        System.out.printf("│ %16s │\n", "");
        System.out.printf("╰──────────────────╯\n");
    }
}
