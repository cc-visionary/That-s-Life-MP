package resources.cards;

import resources.utilities.StringUtil;

public class BlueCard extends Card{
    private String name;
    private String actionType;
    private double amount;
//    private Career career;

    public BlueCard(String name, String actionType, double amount) {
        super("This is a blue card");

        this.type = "Blue Card";
        this.name = name;
        this.actionType = actionType;
        this.amount = amount;
//        this.career = career;
    }

    /**
     * Returns the name of the Blue Card
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the type of the action (PAY / RECEIVE)
     * @return actionType
     */
    public String getActionType() {
        return actionType;
    }

    /**
     * Returns the amount
     * @return amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * This method displays the card into a 12(height) x 20(width) unit layout
     * @return None
     */
    public void  displayCard() {
        System.out.printf("╭────────────────────────────╮\n");
        System.out.printf("│ %-26s │\n", StringUtil.centerString(getType(), 26));
        System.out.printf("│ %-26s │\n", StringUtil.centerString("(" + getName() + ")", 26));
        System.out.printf("├────────────────────────────┤\n");
        System.out.printf("│ %-26s │\n", getDescription());
        System.out.printf("│ %-26s │\n", "");
        System.out.printf("│ %-26s │\n", "");
        System.out.printf("│ %-26s │\n", "");
        System.out.printf("│ %-26s │\n", "");
        System.out.printf("│ %26s │\n", "");
        System.out.printf("│ %26s │\n", "");
        System.out.printf("│ %26s │\n", "");
        System.out.printf("│ %26s │\n", "");
        System.out.printf("│ %26s │\n", "");
        System.out.printf("│ %26s │\n", "");
        System.out.printf("│ %26s │\n", "");
        System.out.printf("╰────────────────────────────╯\n");
    }
}
