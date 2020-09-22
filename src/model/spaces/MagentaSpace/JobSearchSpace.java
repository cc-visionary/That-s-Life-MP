package model.spaces.MagentaSpace;

import gui.modals.Modal;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.Constants;
import model.cards.CareerCard.CareerCard;
import model.cards.SalaryCard.SalaryCard;
import model.decks.Deck;
import utilities.InputUtil;

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
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to keep the Card?", ButtonType.NO, ButtonType.YES);
        alert.showAndWait();
        if(alert.getResult() == ButtonType.NO) {
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

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to keep the Card?", ButtonType.NO, ButtonType.YES);
        alert.showAndWait();
        if(alert.getResult() == ButtonType.NO) {
            salaryDeck.addCard(salaryCard);
            salaryDeck.shuffle();
            salaryCard = null;
        }

        return salaryCard;
    }
}
