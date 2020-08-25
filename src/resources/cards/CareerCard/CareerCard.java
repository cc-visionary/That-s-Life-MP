package resources.cards.CareerCard;

import resources.cards.Card;
import resources.utilities.StringUtil;

final public class CareerCard extends Card {
    final private int minPayRaise, maxPayRaise;
    final private boolean requireCollegeDegree;

    public CareerCard(String name, String description, int minPayRaise, int maxPayRaise, boolean requireCollegeDegree) {
        super("Career Card", name, description);

        this.minPayRaise = minPayRaise;
        this.maxPayRaise = maxPayRaise;
        this.requireCollegeDegree = requireCollegeDegree;
    }

    /**
     * Returns the value of the Minimum Pay Raise for the Career
     * @return minPayRaise
     */
    public int getMinPayRaise() {
        return minPayRaise;
    }

    /**
     * Returns the value of the Maximum Pay Raise for the Career
     * @return maxPayRaise
     */
    public int getMaxPayRaise() {
        return maxPayRaise;
    }

    /**
     * Returns whether the career card needs a college degree or not
     * @return requireCollegeDegree
     */
    public boolean isRequireCollegeDegree() {
        return requireCollegeDegree;
    }

    /**
     * This method displays the card into a 10(max height) x 25(width) unit layout
     */
    @Override
    public void displayCard() {
        final int length = 23, descriptionHeight = 5;
        String[] splittedString = StringUtil.splitStringLength(getDescription(), length).toArray(new String[0]);

        System.out.println("╭───────────────────────╮");
        System.out.println("│" + StringUtil.centerString(getType(), length) + "│");
        System.out.println("│" + StringUtil.centerString("(" + getName() + ")", length) + "│");
        System.out.println("├───────────────────────┤");
        for (int i = 0; i < descriptionHeight; i++) {
            if (i < splittedString.length) {
                System.out.println("│" + StringUtil.centerString(splittedString[i], length) + "│");
            } else {
                System.out.println("│" + StringUtil.centerString("", length) + "│");
            }
        }
        System.out.println("├───────────────────────┤");
        System.out.println("│" + StringUtil.centerString("Pay Raises: " + getMinPayRaise() + " - " + getMaxPayRaise(), length) + "│");
        System.out.println("│" + StringUtil.centerString("College Degree: " + (isRequireCollegeDegree() ? "Yes" : "No"), length) + "│");
        System.out.println("╰───────────────────────╯");
    }
}
