package resources.cards.BlueCard;

public class F1RaceCard extends BlueCard {
    public F1RaceCard(String career) {
        super("F1 Race", "The players pays 10% of his current salary", career);
    }

    @Override
    public double getAmount() {
        return 0;
//        return getOwner().getSalaryCard();
    }
}
