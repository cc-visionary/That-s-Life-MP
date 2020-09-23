package model.Spaces.MagentaSpace;

import gui.modals.Modal;
import model.Constants;
import model.Cards.Card;
import model.Cards.CareerCard.CareerCard;
import model.Cards.SalaryCard.SalaryCard;
import model.Decks.Deck;

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
            Card card1 = careerDeck.pickTopCard();
            Card card2 = careerDeck.pickTopCard();

            pickedCard = (CareerCard) new Modal().chooseCard(card1, card2);

            if(card1.equals(pickedCard)) careerDeck.addCard(card2);
            else careerDeck.addCard(card1);

            careerDeck.shuffle();
        } else {
            pickedCard = null;
            System.out.println("An incorrect deck(" + careerDeck.getName() + ") was passed.");
        }
        return pickedCard;
    }

    public SalaryCard chooseSalaryCard(Deck salaryDeck) {
        SalaryCard pickedCard;
        if(salaryDeck.getName() == "Salary Deck") {
            Card card1 = salaryDeck.pickTopCard();
            Card card2 = salaryDeck.pickTopCard();

            pickedCard = (SalaryCard) new Modal().chooseCard(card1, card2);

            if(card1.equals(pickedCard)) salaryDeck.addCard(card2);
            else salaryDeck.addCard(card1);

            salaryDeck.shuffle();
        } else {
            pickedCard = null;
            System.out.println("An incorrect deck(" + salaryDeck.getName() + ") was passed.");
        }
        return pickedCard;
    }
}
