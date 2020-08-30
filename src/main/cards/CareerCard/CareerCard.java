package main.cards.CareerCard;

import main.cards.Card;
import main.utilities.RandomUtil;
import main.utilities.StringUtil;

final public class CareerCard extends Card {
    private int maxPayRaise;
    private boolean requireCollegeDegree;

    public CareerCard(String name, String description, int minPayRaise, int maxPayRaise, boolean requireCollegeDegree) {
        super("Career Card", name, description);

        // randomly chooses the maxPayRaise from parameters minPayRaise - maxPayRaise
        this.maxPayRaise = RandomUtil.chooseRandomNumber(minPayRaise, maxPayRaise);
        this.requireCollegeDegree = requireCollegeDegree;
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
        System.out.println("│" + StringUtil.centerString("Max Pay Raise: " +  getMaxPayRaise(), length) + "│");
        System.out.println("│" + StringUtil.centerString("College Degree: " + (isRequireCollegeDegree() ? "Yes" : "No"), length) + "│");
        System.out.println("╰───────────────────────╯");
    }

    @Override
    public String toString() {
        return String.format("CareerCard{name=%s,maxPayRaise=%d,collegeRequired=%s}", getName(), getMaxPayRaise(), isRequireCollegeDegree() ? "Yes" : "No");
    }
}
