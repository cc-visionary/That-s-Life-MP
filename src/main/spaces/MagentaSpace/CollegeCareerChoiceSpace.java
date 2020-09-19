package main.spaces.MagentaSpace;

import gui.choose.ChooseCard;
import main.Constants;
import main.cards.Card;
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
            Card card1 = careerDeck.pickTopCard();
            Card card2 = careerDeck.pickTopCard();
            ;
            pickedCard = (CareerCard) ChooseCard.choose2Cards(card1, card2);

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

            pickedCard = (SalaryCard) ChooseCard.choose2Cards(card1, card2);

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
