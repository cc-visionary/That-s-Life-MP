package main.spaces.MagentaSpace;

import main.Constants;
import main.cards.CareerCard.CareerCard;
import main.cards.SalaryCard.SalaryCard;
import main.decks.Deck;
import main.utilities.InputUtil;

/**
 * Represents the Magenta Space - Job Search Space
 *      which allows the Player to choose a new career
 */
final public class JobSearchSpace extends MagentaSpace {
    public JobSearchSpace() {
        super(Constants.JOB_SEARCH);
    }

    /**
     * Draws the top CareerCard then allows the Player to choose whether to keep it or discard it
     * @param careerDeck career deck to draw a CareerCard from
     * @return the CalaryCard depending on whether the Player kept it or discarded it
     */
    public CareerCard getCareerCard(Deck careerDeck) {
        CareerCard careerCard = (CareerCard) careerDeck.pickTopCard();
        System.out.println("CareerCard drawn:" + careerCard);
        System.out.println("Choices: (1) Keep, (2) Discard");
        int choice = InputUtil.scanInt("Enter choice:", 1, 2);

        // if the Card is to be discarded, add it back to the deck and shuffle
        if(choice == 2) {
            careerDeck.addCard(careerCard);
            careerDeck.shuffle();
            careerCard = null;
        }
        return careerCard;
    }

    /**
     * Draws the top SalaryCard then allows the Player to choose whether to keep it or discard it
     * @param salaryDeck salary deck to draw a SalaryCard from
     * @return the SalaryCard depending on whether the Player kept it or discarded it
     */
    public SalaryCard getSalaryCard(Deck salaryDeck) {
        SalaryCard salaryCard = (SalaryCard) salaryDeck.pickTopCard();
        System.out.println("SalaryCard drawn:" + salaryCard);
        System.out.println("Choices: (1) Keep, (2) Discard");
        int choice = InputUtil.scanInt("Enter choice:", 1, 2);

        // if the Card is to be discarded, add it back to the deck and shuffle
        if(choice == 2) {
            salaryDeck.addCard(salaryCard);
            salaryDeck.shuffle();
            salaryCard = null;
        }
        return salaryCard;
    }
}
