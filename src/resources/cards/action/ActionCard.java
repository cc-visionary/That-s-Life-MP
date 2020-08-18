package resources.cards.action;

import resources.cards.Card;
import resources.utilities.StringUtil;

public class ActionCard extends Card {
    private String name, actionType, recipient;
    private double amount;
    public ActionCard(String name, String description, String actionType, String recipient, double amount) {
        super("Action Card", description);
        this.name = name;
        this.actionType = actionType;
        this.recipient = recipient;
        this.amount = amount;
    }

    public void transact() {
        switch(actionType) {
            case "PAY":
                break;
            case "RECEIVE":
                break;
            default:
                System.out.println("Invalid Code for Action Card Transaction...");
        }
    }

    /**
     * Returns the name of the card
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the value of the action type (PAY / RECEIVE)
     * @return actionType
     */
    public String getActionType() {
        return actionType;
    }

    /**
     * Returns the value of the recipient (SINGLE / ALL / BANK / NONE)
     * @return recipient
     */
    public String getRecipient() {
        return recipient;
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
    public void  displayCard() {
        final int length = 23, descriptionHeight = 6;
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
        System.out.println("│" + StringUtil.centerString("Action Type: " + getActionType(), length)            + "│");
        System.out.println("│" + StringUtil.centerString("Recipient: " + getRecipient(), length)            + "│");
        System.out.println("│" + StringUtil.centerString("Amount: " + getAmount(), length)            + "│");
        System.out.println("╰───────────────────────╯");
    }
}
