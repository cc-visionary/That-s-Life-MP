package main.cards.SalaryCard;

import main.cards.Card;
import main.utilities.StringUtil;

/**
 * Salary Card Class
 *
 * @author      Christopher G. Lim
 * @version     1.0
 * @last_modified Aug 27, 2020
 * @since       Aug 18, 2020
 */

final public class SalaryCard extends Card {
    private double salary, tax;
    private int payRaise = 0;
    public SalaryCard(double salary, double tax) {
        super("Salary Card", "Salary Card contains salary and tax due.");

        this.salary = salary;
        this.tax = tax;
    }

    /**
     * Increments the Pay Raise
     */
    public void addPayRaise() {
        this.payRaise++;
    }

    /**
     * Returns the value of the salary
     * @return salary
     */
    public double getSalary() {
        return salary * (getPayRaise() + 1);
    }

    /**
     * Returns the value of the tax
     * @return tax
     */
    public double getTax() {
        return tax * (getPayRaise() + 1);
    }

    /**
     * Returns the amount of pay raise
     * @return the number of pay raise which was done
     */
    public int getPayRaise() {
        return payRaise;
    }

    /**
     * This method displays the card into a 10(max height) x 25(width) unit layout
     */
    @Override
    public void  displayCard() {
        final int length = 23, descriptionHeight = 5;
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
        System.out.println("├───────────────────────┤");
        System.out.println("│" + StringUtil.centerString("Salary: " + getSalary(), length)      + "│");
        System.out.println("│" + StringUtil.centerString("Tax: " + getTax(), length)            + "│");
        System.out.println("│" + StringUtil.centerString("Pay Raise: " + getPayRaise(), length) + "│");
        System.out.println("╰───────────────────────╯");
    }
}
