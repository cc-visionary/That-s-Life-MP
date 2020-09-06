package main;

import main.cards.ActionCard.*;
import main.cards.BlueCard.*;
import main.cards.Card;
import main.cards.CareerCard.CareerCard;
import main.cards.HouseCard.HouseCard;
import main.cards.SalaryCard.SalaryCard;
import main.decks.Deck;
import main.paths.Path;
import main.players.Player;
import main.spaces.BlueSpace.BlueSpace;
import main.spaces.GreenSpace.PayDaySpace;
import main.spaces.GreenSpace.PayRaiseSpace;
import main.spaces.MagentaSpace.*;
import main.spaces.OrangeSpace.OrangeSpace;
import main.spaces.RetirementSpace.RetirementSpace;
import main.spaces.Space;
import main.utilities.InputUtil;
import main.utilities.RandomUtil;

import java.util.ArrayList;
import java.util.Random;

/**
 * consists of Generators to produce Decks, Spaces, Paths, etc.
 */
public class Generator {
    private static int careerPathCount = 0, collegePathCount = 0, changeChareerPathCount = 0, startAFamilyPathCount = 0;

    public static Player[] generatePlayers(Deck careerDeck, Deck salaryDeck, Path careerPath, Path collegePath) {
        // asks the user for the number of players (makes sures that the input is only integers {1, 2, 3})
        int nPlayers = InputUtil.scanInt("Enter number of Players: ", 1, 3);

        ArrayList<Player> players = new ArrayList<Player>();
        for(int i = 0; i < nPlayers; i++) {
            System.out.println("Choose Starting Path for " + "P" + (i + 1) + ":");
            System.out.println("\t[1] Career Path");
            System.out.println("\t[2] College Path");
            int choice = InputUtil.scanInt("Choice: ", 1, 2);
            switch(choice) {
                case 1:
                    CareerCard careerCard = (CareerCard) careerDeck.pickTopCard();
                    SalaryCard salaryCard = (SalaryCard) salaryDeck.pickTopCard();
                    players.add(new Player("P" + (i + 1), careerPath, careerCard, salaryCard));
                    break;
                case 2:
                    players.add(new Player("P" + (i + 1), collegePath));
                    break;
            }
        }
        return players.toArray(new Player[0]);
    }

    /**
     * Generates a Deck for Orange Space composed of ActionCards
     * @return the generated and shuffled Deck for Orange Space which composed of ActionCards
     * @see Deck
     * @see ActionCard
     */
    public static Deck generateOrangeDeck() {
        // Collect from the Bank (40% - 20 cards)
        Card[] collectBankCards = {
                new CollectBankCard("Tax Refund", "You got a tax refund! Collect from the bank!", 1000),
                new CollectBankCard("Tax Refund", "You got a tax refund! Collect from the bank!", 2000),
                new CollectBankCard("Sell an Item", "You sold an item! Collect from the bank!", 1500),
                new CollectBankCard("Bonus Payday", "You had a bonus payday! Collect from the bank!", 4000),
                new CollectBankCard("Setup School", "The school you setup was successful! Collect from the bank!", 4500),
                new CollectBankCard("Write a Book", "The book you wrote became a best-seller! Collect from  the Bank!", 5000)
        };

        // Pay the Bank (40% - 20 cards)
        Card[] payBankCards = {
                new PayBankCard("Buy an Item", "You bought an item! You need to pay the bank!", 2000),
                new PayBankCard("Visit a Place", "You visited a place! You need to pay the bank!", 1000),
                new PayBankCard("Hiking", "You went hiking! You enjoyed it, but now it's time to pay the bank!", 500),
                new PayBankCard("Watch a Show", "You watched a show! You need to pay the bank!", 500),
                new PayBankCard("Traffic Violation", "You crossed the while it was red on the traffic light. You need to pay the bank!", 500)
        };

        // Pay the Player (10% - 5 cards)
        Card[] payPlayerCards = {
                new PayPlayerCard("Lawsuit", "A Lawsuit has been filed on you! Pay the other player!", 4000),
                new PayAllCard("Christmas Bonus", "It's Christmas time! Time for you to pay all the other players!", 1000)
        };

        // Collect from Players (10% - 5 cards)
        Card[] collectPlayerCards = {
                new CollectPlayerCard("File a Lawsuit", "You're lawsuit was successful! You'll receive money from the other player!", 4000),
                new CollectAllCard("It's Your Birthday", "Happy Birthday! All the other players gave you money as a gift!", 1000)
        };

        Deck orangeDeck = new Deck("Orange Deck");
        // randomly choose from any of Collect from Bank Cards
        for(int i = 0; i < 20; i++) orangeDeck.addCard(RandomUtil.chooseRandomCard(collectBankCards));
        // randomly choose from any of Pay to Bank Cards
        for(int i = 0; i < 20; i++) orangeDeck.addCard(RandomUtil.chooseRandomCard(payBankCards));
        // randomly choose from any of Pay to Player Bank Cards
        for(int i = 0; i < 5; i++) orangeDeck.addCard(RandomUtil.chooseRandomCard(payPlayerCards));
        // randomly choose from any of Collect from Player Bank Cards
        for(int i = 0; i < 5; i++) orangeDeck.addCard(RandomUtil.chooseRandomCard(collectPlayerCards));

        orangeDeck.shuffle();

        return orangeDeck;
    }

    /**
     * Generates a Deck composed of CareerCard to let the users
     * draw a random career from a Deck
     * @return the generated and shuffled Career Deck which consists of Career Cards
     * @see Deck
     * @see CareerCard
     */
    public static Deck generateCareerDeck() {
        Card[] careers = {
                new CareerCard("Lawyer", "A lawyer who makes law to create order in the world.", 5, 8, true),
                new CareerCard("Accountant", "", 4, 7, true),
                new CareerCard("Comp. Consultant", "", 3, 7, true),
                new CareerCard("Doctor", "", 5, 8, true),
                new CareerCard("Server", "", 1, 4, false),
                new CareerCard("Racecar Driver", "Broom Broom!", 2, 8, false),
                new CareerCard("Athlete", "You're a Sporty person huh?", 1, 6, false)
        };

        Deck careerDeck = new Deck("Career Deck");
        for(Card career : careers) {
            careerDeck.addCard(career);
        }

        careerDeck.shuffle();

        return careerDeck;
    }

    /**
     * Generates Salary Deck which consists of Salary Cards
     * with different salary value which is a multiple of 10000
     * @return the generated and shuffled Deck which consists of SalaryCard
     * @see Deck
     * @see SalaryCard
     */
    public static Deck generateSalaryDeck() {
        Card[] salaryCards = {
                new SalaryCard(10000),
                new SalaryCard(5000),
                new SalaryCard(2500),
                new SalaryCard(2000),
                new SalaryCard(1250),
                new SalaryCard(1000),
                new SalaryCard(625),
                new SalaryCard(500),
                new SalaryCard(400),
                new SalaryCard(250),
        };

        Deck salaryDeck = new Deck("Salary Deck");

        for(Card salaryCard : salaryCards) {
            salaryDeck.addCard(salaryCard);
        }

        salaryDeck.shuffle();

        return salaryDeck;
    }

    /**
     * Generates a Deck to be used when picking a Blue Card whenever you land on Blue Space
     * @param numberOfPlayers the number of players currently in the game (to be passed unto WorldCupCard)
     * @return the generated and shuffled Deck for Blue Space which composed of BlueCards
     * @see Deck
     * @see BlueCard
     */
    public static Deck generateBlueDeck(int numberOfPlayers) {
        String[] careers =  {
                "Lawyer", "Accountant", "Comp. Consultant", "Doctor", "Server", "Racecar Driver", "Athlete"
        };

        Deck blueDeck = new Deck("Blue Deck");

        for(int i = 0; i < 50; i++) {
            int randomNumber = RandomUtil.chooseRandomNumber(1, 7);
            String career = careers[i % 7];
            switch(randomNumber) {
                case 1:
                    blueDeck.addCard(new LawsuitCard(career, RandomUtil.chooseRandomNumber(1, 4)));
                    break;
                case 2:
                    blueDeck.addCard(new SalaryTaxDueCard(career));
                    break;
                case 3:
                    blueDeck.addCard(new TipTheServerCard(career));
                    break;
                case 4:
                    blueDeck.addCard(new SkiAccidentCard(career));
                    break;
                case 5:
                    blueDeck.addCard(new ComputerRepairCard(career));
                    break;
                case 6:
                    blueDeck.addCard(new WorldCupCard(career, numberOfPlayers));
                    break;
                case 7:
                    blueDeck.addCard(new F1RaceCard(career));
                    break;
                default:
                    // if value wasn't in the intended values (which is improbable)
                    // just continue/proceed to next loop without incrementing i
                    continue;
            }
        }

        blueDeck.shuffle();

        return blueDeck;
    }

    /**
     * Generate the HouseDeck composed of House Card
     * @return generated House Deck
     */
    public static Deck generateHouseDeck() {
        Deck houseDeck = new Deck("House Deck");

        // add house cards to the Deck
        houseDeck.addCard(new HouseCard("Mansion", "", 50000));
        houseDeck.addCard(new HouseCard("Penthouse", "", 25000));
        houseDeck.addCard(new HouseCard("Condomenium", "", 10000));
        houseDeck.addCard(new HouseCard("Normal House", "", 5000));

        return houseDeck;
    }

    /**
     * <p>Generates the Board (of Paths). A path can be generated with left and right nodes (path1 and path2) or none.</p>
     * <p>Note: the Path has to be generated from the End of the Board to the Start</p>
     * <a href="https://app.lucidchart.com/invitations/accept/151b81a7-1b87-4ad8-8a07-25f41bef561c">Link to UML</a>
     * @return array with length 2 where index 0 = Starting Career Path, index 1 = Starting College Path
     */
    public static Path[] generateBoard() {
        // depth 6
        Path rp = generateRetirementPath();

        // depth 5
        Path cap1 = generateCareerPath(rp, null);
        Path safp1 = generateStartAFamilyPath(rp, null);
        Path safp2 = generateStartAFamilyPath(rp, null);

        // depth 4
        Path safp3 = generateStartAFamilyPath(cap1, safp1);
        Path cap2 = generateCareerPath(safp2, null);
        Path ccp1 = generateChangeCareerPath(safp2, null);

        // depth 3
        Path cap3 = generateCareerPath(safp3, null);
        Path ccp2 = generateChangeCareerPath(safp3, cap2);
        Path cap4 = generateCareerPath(cap2, ccp1);

        // depth 2
        Path ccp3 = generateChangeCareerPath(cap3, null);
        Path cap5 = generateCareerPath(cap3, ccp2);
        Path cap6 = generateCareerPath(ccp2, cap4);
        Path ccp4 = generateChangeCareerPath(cap4, null);

        // depth 1
        Path cap7 = generateCareerPath(ccp3, cap5);
        Path cop1 = generateCollegePath(cap5, null);
        Path cap8 = generateCareerPath(cap6, ccp4);

        // depth 0
        Path startingPaths[] = {generateCareerPath(cap7, cop1), generateCollegePath(cap8, null)};
        return startingPaths;
    }

    /**
     * Generates a Career Path
     * @param path1 path1/left node to be passed unto the Career Path
     * @param path2 path2/right node to be passed unto the Career Path
     * @return career path that was generated
     */
    public static Path generateCareerPath(Path path1, Path path2) {
        careerPathCount++;
        int nSpaces = RandomUtil.chooseRandomNumber(20, 35);
        ArrayList<Space> spaces = new ArrayList<Space>();
        int randomNumber = RandomUtil.chooseRandomNumber(0, nSpaces - 2);
        for(int i = 0; i < nSpaces - 1; i++) {
            // if random number matches the index, add a GetMarriedSpace
            if(randomNumber == i) spaces.add(new GetMarriedSpace());
                // else add an OrangeSpace
            else spaces.add(new OrangeSpace());
        }
        spaces.add(new WhichPathSpace());
        return new Path("Career Path", "cap" + careerPathCount, spaces.toArray(new Space[0]), path1, path2);
    }

    /**
     * Generates a College Path
     * @param path1 path1/left node to be passed unto the College Path
     * @param path2 path2/right node to be passed unto the College Path
     * @return generated College Path
     */
    public static Path generateCollegePath(Path path1, Path path2) {
        collegePathCount++;
        int nSpaces = RandomUtil.chooseRandomNumber(20, 30);
        ArrayList<Space> spaces = new ArrayList<Space>();
        // adds Orange Spaces
        for(int i = 0; i < nSpaces - 2; i++) {
            spaces.add(new OrangeSpace());
        }
        spaces.add(new CollegeCareerChoiceSpace());
        spaces.add(new WhichPathSpace());
        return new Path("College Path", "cop" + collegePathCount, spaces.toArray(new Space[0]), path1, path2);
    }

    /**
     * Generates a Change a Career Path
     * @param path1 path1/left node to be passed unto the Change a Career Path
     * @param path2 path2/right node to be passed unto the Change a Career Path
     * @return generated Change a Career Path
     */
    public static Path generateChangeCareerPath(Path path1, Path path2) {
        changeChareerPathCount++;
        int nSpaces = RandomUtil.chooseRandomNumber(20, 30);
        boolean hasCareerSpace, hasOrangeSpace, hasPayDaySpace, hasPayRaiseSpace, hasBlueSpace, hasJunction;
        ArrayList<Space> spaces = new ArrayList<Space>();
        // generate the spaces randomly
        for(int i = 0; i < nSpaces - 3; i++) {
            switch(RandomUtil.chooseRandomNumber(1, 5)) {
                case 1:
                    spaces.add(new OrangeSpace());
                    break;
                case 2:
                    spaces.add(new PayDaySpace());
                    break;
                case 3:
                    spaces.add(new PayRaiseSpace(RandomUtil.chooseRandomNumber(1, 10) * 500));
                    break;
                case 4:
                    spaces.add(new CollegeCareerChoiceSpace());
                    break;
                case 5:
                    spaces.add(new BlueSpace());
                    break;
            }
        }
//        for(Space space : spaces) {
//            if(space.getType() == "Magenta Space") {
//                if(space.getName() == "Which Path Space") hasJunction = true;
//            } else if(space.getType() == "Green Space") {
//                if(space.getName() == "Pay Day Space") hasPayDaySpace = true;
//                else if(space.getName() == "Pay Raise Space") hasPayRaiseSpace = true;
//            } else if(space.getType() == "Blue Space") hasBlueSpace = true;
//            else if(space.getType() == "Orange Space") hasOrangeSpace = true;
//        }
        spaces.add(new WhichPathSpace());
        return new Path("Change Career Path", "ccp" + changeChareerPathCount, spaces.toArray(new Space[0]), path1, path2);
    }

    /**
     * Generates a Start a Family Path
     * @param path1 path1/left node to be passed unto the Start a Family Path
     * @param path2 path2/right node to be passed unto the Start a Family Path
     * @return generated Start a Family Path
     */
    public static Path generateStartAFamilyPath(Path path1, Path path2) {
        startAFamilyPathCount++;
        int nSpaces = RandomUtil.chooseRandomNumber(20, 30);
        boolean hasMarried = false, hasBuyAHouse = false, hasBaby = false, hasBlue = false;
        ArrayList<Space> spaces = new ArrayList<Space>();
        // generate the spaces randomly
        for(int i = 0; i < nSpaces - 1; i++) {
            switch(RandomUtil.chooseRandomNumber(1, 5)) {
                case 1:
                    if(!hasMarried) {
                        spaces.add(new GetMarriedSpace());
                        hasMarried = true;
                        break;
                    }
                case 2:
                    if(!hasBuyAHouse) {
                        spaces.add(new BuyAHouseSpace());
                        hasBuyAHouse = true;
                        break;
                    }
                case 3:
                    if(!hasBaby) {
                        spaces.add(new HaveBabySpace(RandomUtil.chooseRandomNumber(1, 3)));
                        hasBaby = true;
                        break;
                    }
                case 4:
                    if(!hasBlue) {
                        spaces.add(new BlueSpace());
                        hasBlue = true;
                        break;
                    }
                case 5:
                    spaces.add(new OrangeSpace());
                    break;
            }
        }
        spaces.add(new WhichPathSpace());
        return new Path("Start a Family Path", "safp" + startAFamilyPathCount, spaces.toArray(new Space[0]), path1, path2);
    }

    public static Path generateRetirementPath() {
        int nSpaces = RandomUtil.chooseRandomNumber(10, 40);
        ArrayList<Space> spaces = new ArrayList<Space>();
        for(int i = 0; i < nSpaces - 1; i++) {
            spaces.add(new OrangeSpace());
        }
        spaces.add(new RetirementSpace());
        return new Path("Retirement Path", "rp", spaces.toArray(new Space[0]));
    }
}
