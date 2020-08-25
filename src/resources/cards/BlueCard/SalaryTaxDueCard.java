package resources.cards.BlueCard;

final public class SalaryTaxDueCard extends BlueCard {
     public SalaryTaxDueCard(String career) {
         super("Salary Tax Due", "The player pays the tax due for his current salary.", career);
     }

    /**
     * Returns the value to be paid by the player
     * @return amount
     */
    @Override
    public double getAmount() {
         return 0;
//        return getOwner().;
    }
}
