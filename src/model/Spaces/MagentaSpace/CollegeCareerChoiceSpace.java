package model.Spaces.MagentaSpace;

import gui.modals.Modal;
import model.Constants;
import model.Cards.Card;
import model.Cards.CareerCard.CareerCard;
import model.Cards.SalaryCard.SalaryCard;
import model.Decks.Deck;
import model.GameOfLife;

/**
 * Represents the Magenta Space - College Career Choice Space
 *      gets the top 2 Career Card and Salary Card from their respective Decks are shown to the Player
 *      where in the Player will choose one of each and return the others.
 */

final public class CollegeCareerChoiceSpace extends MagentaSpace {
    public CollegeCareerChoiceSpace() {
        super(Constants.COLLEGE_CARREER_CHOICE);
    }

    /**
     * Lets the player choose a CareerCard.
     * If a player hasn't graduated yet, only give CareerCards to player that
     * doesn't require a player to graduate first
     * @param careerDeck Deck which contains the Career Cards
     * @param graduated  determines whether the card to be returned has to have a graduation requirement or not
     * @return           the chosen card
     */
    public CareerCard chooseCareerCard(Deck careerDeck, boolean graduated) {
        CareerCard pickedCard;
        if(careerDeck.getName() == "Career Deck") {
            if(!graduated) {
                int nCareerWithoutGraduation = 0;
                for(Card card : careerDeck.getCards()) {
                    if(!((CareerCard) card).isRequireCollegeDegree()) nCareerWithoutGraduation++;
                }
                if(nCareerWithoutGraduation >= 2) {
                    Card card1 = null;
                    Card card2 = null;
                    // pick top card until card take is one which doesn't need a player to graduate
                    do {
                        if(card1 != null) careerDeck.addCard(card1);
                        card1 = careerDeck.pickTopCard();
                    } while (((CareerCard) card1).isRequireCollegeDegree());

                    // pick top card until card take is one which doesn't need a player to graduate
                    do {
                        if(card2 != null) careerDeck.addCard(card2);
                        card2 = careerDeck.pickTopCard();
                    } while (((CareerCard) card2).isRequireCollegeDegree());

                    pickedCard = (CareerCard) new Modal().chooseCard(card1, card2);

                    if(card1.equals(pickedCard)) careerDeck.addCard(card2);
                    else careerDeck.addCard(card1);

                    careerDeck.shuffle();
                } else if(nCareerWithoutGraduation == 1) {
                    Card card = null;
                    // pick top card until card take is one which doesn't need a player to graduate
                    do {
                        if(card != null) careerDeck.addCard(card);
                        card = careerDeck.pickTopCard();
                    } while (((CareerCard) card).isRequireCollegeDegree());

                    new Modal().displayCard(card);
                    pickedCard = (CareerCard) card;
                } else {
                    pickedCard = null;
                    GameOfLife.addRoundStat("There's no more Career Card which doesn't require a player to Graduate first.");
                }
            } else {
                Card card1 = careerDeck.pickTopCard();
                Card card2 = careerDeck.pickTopCard();

                pickedCard = (CareerCard) new Modal().chooseCard(card1, card2);

                if(card1.equals(pickedCard)) careerDeck.addCard(card2);
                else careerDeck.addCard(card1);

                careerDeck.shuffle();
            }
        } else {
            pickedCard = null;
            System.out.println("An incorrect deck(" + careerDeck.getName() + ") was passed.");
        }
        return pickedCard;
    }

    /**
     * Lets a Player choose a salary card from the Salary Deck
     * @param salaryDeck Deck containing the salary Cards
     * @return           the chosen Salary Card
     */
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
