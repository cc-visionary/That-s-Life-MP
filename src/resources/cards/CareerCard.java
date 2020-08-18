package resources.cards;

import resources.utilities.StringUtil;

final public class CareerCard extends Card {
    final private String name;
    final private int payRaises;
    final private boolean requireCollegeDegree;

    public CareerCard(String name, String description, int payRaises, boolean requireCollegeDegree) {
        super("Career Card", description);

        this.name = name;
        this.payRaises = payRaises;
        this.requireCollegeDegree = requireCollegeDegree;
    }

    /**
     * Returns the name of the Career
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the number of pay raises
     * @return payRaises
     */
    public int getPayRaises() {
        return payRaises;
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
        System.out.println("│" + StringUtil.centerString("Pay Raises: " + getPayRaises(), length) + "│");
        System.out.println("│" + StringUtil.centerString("College Degree: " + (isRequireCollegeDegree() ? "Yes" : "No"), length) + "│");
        System.out.println("╰───────────────────────╯");
    }
}
