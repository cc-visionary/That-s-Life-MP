package resources.cards;

import resources.utilities.StringUtil;

/**
 * Salary Card Class
 *
 * @author      Christopher G. Lim
 * @version     1.0
 * @last-edited Aug 18, 2020
 * @since       Aug 18, 2020
 */

final public class SalaryCard extends Card {
    final private double salary, tax;
    public SalaryCard(double salary, double tax) {
        super("Salary Card", "Salary Card contains salary and tax due.");

        this.salary = salary;
        this.tax = tax;
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
        System.out.println("│" + StringUtil.centerString("Salary: " + getSalary(), length)            + "│");
        System.out.println("│" + StringUtil.centerString("Tax: " + getTax(), length)            + "│");
        System.out.println("╰───────────────────────╯");
    }
}
