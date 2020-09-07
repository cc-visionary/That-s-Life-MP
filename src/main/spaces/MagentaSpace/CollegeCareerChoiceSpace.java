package main.spaces.MagentaSpace;

import main.Constants;
import main.cards.CareerCard.CareerCard;
import main.cards.SalaryCard.SalaryCard;
import main.decks.Deck;
import main.players.Player;
import main.utilities.InputUtil;

/**
 * Represents the Magenta Space - College Career Choice Space
 *      gets the top 2 Career Card and Salary Card from their respective Decks are shown to the Player
 *      where in the Player will choose one of each and return the others.
 */

final public class CollegeCareerChoiceSpace extends MagentaSpace {
    public CollegeCareerChoiceSpace() {
        super(Constants.COLLEGE_CARREER_CHOICE);
    }

    public CareerCard chooseCareerCard(Deck careerDeck) {
        CareerCard pickedCard;
        if(careerDeck.getName() == "Career Deck") {
            int choice;
            System.out.println("CareersCards to choose from:");
            careerDeck.displayTopCards(2);
            choice = InputUtil.scanInt("Choose Career Card:", 1, 2);

            pickedCard = (CareerCard) careerDeck.pickTopCard(choice - 1);
        } else {
            pickedCard = null;
            System.out.println("An incorrect deck(" + careerDeck.getName() + ") was passed.");
        }
        return pickedCard;
    }

    public SalaryCard chooseSalaryCard(Deck salaryDeck) {
        SalaryCard pickedCard;
        if(salaryDeck.getName() == "Salary Deck") {
            int choice;
            System.out.println("SalaryCards to choose from:");
            salaryDeck.displayTopCards(2);
            choice = InputUtil.scanInt("Choose Salary Card:", 1, 2);

            pickedCard = (SalaryCard) salaryDeck.pickTopCard(choice - 1);
        } else {
            pickedCard = null;
            System.out.println("An incorrect deck(" + salaryDeck.getName() + ") was passed.");
        }
        return pickedCard;
    }
}
