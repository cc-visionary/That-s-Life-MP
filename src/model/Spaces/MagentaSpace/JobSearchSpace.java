package model.Spaces.MagentaSpace;

import gui.modals.Modal;
import model.Constants;
import model.Cards.CareerCard.CareerCard;
import model.Cards.SalaryCard.SalaryCard;
import model.Decks.Deck;

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
        new Modal().displayCard(careerCard);
        boolean result = new Modal().askYesNo("Keep Card", "Do you want to keep the Card?");
        if(!result) {
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
        new Modal().displayCard(salaryCard);

        boolean result = new Modal().askYesNo("Keep Card", "Do you want to keep the Card?");
        if(!result) {
            salaryDeck.addCard(salaryCard);
            salaryDeck.shuffle();
            salaryCard = null;
        }

        return salaryCard;
    }
}
