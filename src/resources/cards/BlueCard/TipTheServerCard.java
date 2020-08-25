package resources.cards.BlueCard;

final public class TipTheServerCard extends BlueCard {
    public TipTheServerCard(String career) {
        super("Tip the Server", "The player presses a random number then pays the generated number x $1000", career);
    }

    @Override
    public double getAmount() {
        // scanner
        int generatedNumber = 1;
        return generatedNumber * 1000;
    }
}
