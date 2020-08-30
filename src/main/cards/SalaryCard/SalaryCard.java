package main.cards.SalaryCard;

import main.cards.Card;
import main.utilities.StringUtil;

/**
 * SalaryCard Class
 *      Contains Salary and SalaryTaxDue. Salary can be increased along with SalaryTaxDue.
 */

final public class SalaryCard extends Card {
    private double salary, tax;
    private int payRaise = 0;
    public SalaryCard(double salary) {
        super("Salary Card", "Salary Card contains salary and tax due.");

        this.salary = salary;
        this.tax = salary * 0.1; // the tax due is 10% of the salary
    }

    /**
     * Increases the salary by a parameter amount and increments the payraise
     * @param amount amount to increase the salary
     */
    public void increaseSalary(double amount) {
        salary += amount;
        tax = salary * 0.1; // the tax due is 10% of the salary
        payRaise++;
    }

    /**
     * Returns the value of the salary
     * @return salary
     */
    public double getSalary() {
        return salary;
    }

    /**
     * Returns the value of the tax
     * @return tax
     */
    public double getTax() {
        return tax;
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

    @Override
    public String toString() {
        return String.format("SalaryCard{salary=%.2f,tax=%.2f,payRaise=%d}", salary, tax, payRaise);
    }
}
