package model;

import model.Cards.ActionCard.*;
import model.Cards.BlueCard.*;
import model.Cards.Card;
import model.Cards.CareerCard.CareerCard;
import model.Cards.HouseCard.HouseCard;
import model.Cards.SalaryCard.SalaryCard;
import model.Decks.Deck;
import model.Decks.DeckWithUsed;
import model.Paths.Path;
import model.Players.Player;
import model.Spaces.BlueSpace.BlueSpace;
import model.Spaces.GreenSpace.PayDaySpace;
import model.Spaces.GreenSpace.PayRaiseSpace;
import model.Spaces.MagentaSpace.*;
import model.Spaces.OrangeSpace.OrangeSpace;
import model.Spaces.RetirementSpace.RetirementSpace;
import model.Spaces.Space;
import utilities.IntUtil;
import utilities.RandomUtil;

import java.util.ArrayList;

/**
 * consists of Generators to produce Decks, Spaces, Paths, etc.
 */
public class Generator {
    private static int careerPathCount = 0, collegePathCount = 0, changeChareerPathCount = 0, startAFamilyPathCount = 0;

    public static ArrayList<Player> generatePlayers(int nPlayers, int startingMoney) {
        ArrayList<Player> players = new ArrayList<Player>();
        for(int i = 0; i < nPlayers; i++) {
            players.add(new Player(startingMoney));
        }
        return players;
    }

    /**
     * Generates a Deck for Orange Space composed of ActionCards
     * @return the generated and shuffled Deck for Orange Space which composed of ActionCards
     * @see Deck
     * @see ActionCard
     */
    public static DeckWithUsed generateOrangeDeck() {
        // Collect from the Bank (40% - 20 cards)
        Card[] collectBankCards = {
                new CollectBankCard("Tax Refund", "You got a tax refund! Collect from the bank!", 5000),
                new CollectBankCard("Sell an Item", "You sold an item! Collect from the bank!", 2500),
                new CollectBankCard("Bonus Payday", "You had a bonus payday! Collect from the bank!", 7500),
                new CollectBankCard("Setup School", "The school you setup was successful! Collect from the bank!", 7500),
                new CollectBankCard("Write a Book", "The book you wrote became a best-seller! Collect from  the Bank!", 5000)
        };

        // Pay the Bank (40% - 20 cards)
        Card[] payBankCards = {
                new PayBankCard("Buy an Item", "You bought an item! You need to pay the bank!", 5000),
                new PayBankCard("Visit a Place", "You visited a place! You need to pay the bank!", 2500),
                new PayBankCard("Hiking", "You went hiking! You enjoyed it, but now it's time to pay the bank!", 2500),
                new PayBankCard("Watch a Show", "You watched a show! You need to pay the bank!", 2500),
                new PayBankCard("Traffic Violation", "You crossed the while it was red on the traffic light. You need to pay the bank!", 5000)
        };

        // Pay the Player (10% - 5 cards)
        Card[] payPlayerCards = {
                new PayPlayerCard("Lawsuit", "A Lawsuit has been filed on you! Pay the other player!", 5000),
                new PayAllCard("Christmas Bonus", "It's Christmas time! Time for you to pay all the other players!", 2500)
        };

        // Collect from Players (10% - 5 cards)
        Card[] collectPlayerCards = {
                new CollectPlayerCard("File a Lawsuit", "You're lawsuit was successful! You'll receive money from the other player!", 5000),
                new CollectAllCard("It's Your Birthday", "Happy Birthday! All the other players gave you money as a gift!", 2500)
        };

        DeckWithUsed orangeDeck = new DeckWithUsed("Orange Deck");

        // randomly choose from any of Collect from Bank Cards
        for(int i = 0; i < Constants.nCollectBank; i++) orangeDeck.addCard(RandomUtil.chooseRandomCard(collectBankCards));
        // randomly choose from any of Pay to Bank Cards
        for(int i = 0; i < Constants.nPayBank; i++) orangeDeck.addCard(RandomUtil.chooseRandomCard(payBankCards));
        // randomly choose from any of Pay to Player Bank Cards
        for(int i = 0; i < Constants.nPayPlayer; i++) orangeDeck.addCard(RandomUtil.chooseRandomCard(payPlayerCards));
        // randomly choose from any of Collect from Player Bank Cards
        for(int i = 0; i < Constants.nCollectPlayer; i++) orangeDeck.addCard(RandomUtil.chooseRandomCard(collectPlayerCards));

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
                new CareerCard("Lawyer", "A licensed professional who advises and represents others in legal matters", 5, 8, true),
                new CareerCard("Accountant", "A person who use numbers and financial statements to paint a picture of the health of a company, organization, or individual", 4, 7, true),
                new CareerCard("Computer Consultant", "A person who helps companies use technology effectively to grow their businesses and solve problems", 3, 7, true),
                new CareerCard("Doctor", "A qualified practitioner of medicine", 5, 8, true),
                new CareerCard("Server", "A person whose job is to serve customers at their tables in a restaurant", 1, 4, false),
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
        Deck salaryDeck = new Deck("Salary Deck");

        for(int i = 0; i < 15; i++) {
            salaryDeck.addCard(new SalaryCard(RandomUtil.chooseRandomNumber(1, 15) * 10000));
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
        Deck blueDeck = new Deck("Blue Deck");
        blueDeck.addCard(new LawsuitCard(RandomUtil.chooseRandomNumber(1, 4)));
        blueDeck.addCard(new SalaryTaxDueCard());
        blueDeck.addCard(new TipTheServerCard());
        blueDeck.addCard(new SkiAccidentCard());
        blueDeck.addCard(new ComputerRepairCard());
        blueDeck.addCard(new WorldCupCard(numberOfPlayers));
        blueDeck.addCard(new F1RaceCard());

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
        houseDeck.addCard(new HouseCard("Mansion", "An expensive and large dwelling house.", 50000));
        houseDeck.addCard(new HouseCard("Penthouse", "An apartment or unit on the highest floor of an apartment building, condominium, or hotel.", 25000));
        houseDeck.addCard(new HouseCard("Apartment", "A self-contained housing unit that occupies only part of a building", 10000));
        houseDeck.addCard(new HouseCard("Normal House", "A single-unit residential building, which may range in complexity from a rudimentary hut to a complex, structure of wood, masonry, concrete or other material, outfitted with plumbing, electrical, and heating, ventilation, and air conditioning systems", 5000));

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

        // depth 4
        Path safp2 = generateStartAFamilyPath(cap1, null);
        Path cap2 = generateCareerPath(safp1, null);

        // depth 3
        Path cap3 = generateCareerPath(safp2, null);
        Path ccp1 = generateChangeCareerPath(safp2, cap2);
        Path cap4 = generateCareerPath(cap2, null);

        // depth 2
        Path ccp2 = generateChangeCareerPath(cap3, null);
        Path cap5 = generateCareerPath(ccp1, cap3);
        Path ccp3 = generateChangeCareerPath(ccp1, cap4);
        Path cap6 = generateCareerPath(cap4, null);

        // depth 1
        Path cap7 = generateCareerPath(ccp2, null);
        Path cop1 = generateCollegePath(cap5, null);
        Path cap8 = generateCareerPath(ccp3, null);
        Path ccp4 = generateChangeCareerPath(cap6, null);

        // depth 0
        Path startingPaths[] = {generateCareerPath(cap7, cop1), generateCollegePath(cap8, ccp4)};
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
        ArrayList<Space> spaces = new ArrayList<Space>();
        int randomNumber = RandomUtil.chooseRandomNumber(0, Constants.PATH_SPACES - 2);
        for(int i = 0; i < Constants.PATH_SPACES - 1; i++) {
            // if random number matches the index, add a GetMarriedSpace
            if(randomNumber == i) spaces.add(new GetMarriedSpace());
                // else add an OrangeSpace
            else {
                if(RandomUtil.chooseRandomNumber(1, 2) == 1) {
                    spaces.add(new OrangeSpace());
                } else {
                    spaces.add(RandomUtil.chooseRandomNumber(1, 2) == 1 ? new PayDaySpace() : new PayRaiseSpace(RandomUtil.chooseRandomNumber(1, 10) * 500));
                }
            }
        }
        if(path2 == null) spaces.add(new OrangeSpace());
        else spaces.add(new WhichPathSpace());

        return new Path("Career Path", "cap" + careerPathCount, spaces.toArray(new Space[0]), path1, path2, "Career Path is where you can improve your lifestyle by earning as much as possible, and as efficient as possible. Players will work hard here to be able achieve their both life and personal goals.");
    }

    /**
     * Generates a College Path
     * @param path1 path1/left node to be passed unto the College Path
     * @param path2 path2/right node to be passed unto the College Path
     * @return generated College Path
     */
    public static Path generateCollegePath(Path path1, Path path2) {
        collegePathCount++;
        ArrayList<Space> spaces = new ArrayList<Space>();
        // adds Orange Spaces
        for(int i = 0; i < Constants.PATH_SPACES - 2; i++) {
            spaces.add(new OrangeSpace());
        }
        spaces.add(new CollegeCareerChoiceSpace());
        if(path2 == null) spaces.add(new OrangeSpace());
        else spaces.add(new WhichPathSpace());
        return new Path("College Path", "cop" + collegePathCount, spaces.toArray(new Space[0]), path1, path2, "College Path is where Players go through so that they can get better jobs which will increase the likelihood of a better lifestyle.");
    }

    /**
     * Generates a Change a Career Path
     * @param path1 path1/left node to be passed unto the Change a Career Path
     * @param path2 path2/right node to be passed unto the Change a Career Path
     * @return generated Change a Career Path
     */
    public static Path generateChangeCareerPath(Path path1, Path path2) {
        changeChareerPathCount++;
        ArrayList<Space> spaces = new ArrayList<Space>();

        // determines where the respective spaces will be located
        // Note: This make sures that each of the spaces to be added are available.
        int ranges[] = IntUtil.splitEqually(4, Constants.PATH_SPACES);
        int payDaySpace = RandomUtil.chooseRandomNumber(0, ranges[0]);
        int payRaiseSpace = RandomUtil.chooseRandomNumber(ranges[0], ranges[1]);
        int orangeSpace = RandomUtil.chooseRandomNumber(ranges[1], ranges[2]);
        int blueSpace = RandomUtil.chooseRandomNumber(ranges[2], ranges[3]);

        // Adds the CareerChoiceSpace as the first space
        spaces.add(new CollegeCareerChoiceSpace());

        // generate the spaces randomly
        for(int i = 0; i < Constants.PATH_SPACES - 2; i++) {
            if(payDaySpace == i) spaces.add(new PayDaySpace());
            else if(payRaiseSpace == i) spaces.add(new PayRaiseSpace(RandomUtil.chooseRandomNumber(1, 10) * 500));
            else if(orangeSpace == i) spaces.add(new OrangeSpace());
            else if(blueSpace == i) spaces.add(new BlueSpace());
            else { // after making sure each is in the Path, choose randomly to fill the Path
                int random_number = RandomUtil.chooseRandomNumber(1, 3);
                switch (random_number) {
                    case 1:
                        spaces.add(new PayDaySpace());
                        break;
                    case 2:
                        spaces.add(new PayRaiseSpace(RandomUtil.chooseRandomNumber(1, 10) * 500));
                        break;
                    case 3:
                        spaces.add(new OrangeSpace());
                        break;
                }
            }
        }

        if(path2 == null) spaces.add(new OrangeSpace());
        else spaces.add(new WhichPathSpace());
        return new Path("Change Career Path", "ccp" + changeChareerPathCount, spaces.toArray(new Space[0]), path1, path2, "Change a Career Path is where Players find more opportunities for vacant Jobs to gain even more money. Passing through here will surely grant you benefits and opportunities.");
    }

    /**
     * Generates a Start a Family Path
     * @param path1 path1/left node to be passed unto the Start a Family Path
     * @param path2 path2/right node to be passed unto the Start a Family Path
     * @return generated Start a Family Path
     */
    public static Path generateStartAFamilyPath(Path path1, Path path2) {
        startAFamilyPathCount++;
        ArrayList<Space> spaces = new ArrayList<Space>();

        // determines where the respective spaces will be located
        int ranges[] = IntUtil.splitEqually(4, Constants.PATH_SPACES);
        int getMarriedSpace = RandomUtil.chooseRandomNumber(0, ranges[0]);
        int buyAHouseSpace = RandomUtil.chooseRandomNumber(ranges[1], ranges[2]);
        int haveABaby = RandomUtil.chooseRandomNumber(ranges[2], ranges[3]);
        int blueSpace = RandomUtil.chooseRandomNumber(ranges[3], Constants.PATH_SPACES);

        // generate the spaces randomly
        for(int i = 0; i < Constants.PATH_SPACES - 1; i++) {
            if(getMarriedSpace == i) spaces.add(new GetMarriedSpace());
            else if(buyAHouseSpace == i) spaces.add(new BuyAHouseSpace());
            else if(haveABaby == i) spaces.add(new HaveBabySpace(RandomUtil.chooseRandomNumber(1, 3)));
            else if(blueSpace == i) spaces.add(new BlueSpace());
            else spaces.add(new OrangeSpace());
        }

        if(path2 == null) spaces.add(new OrangeSpace());
        else spaces.add(new WhichPathSpace());
        return new Path("Start a Family Path", "safp" + startAFamilyPathCount, spaces.toArray(new Space[0]), path1, path2, "Start a Family Path is where Players start their own family by buying a house and having kids. Through passing here, you'll always have your children to inherit your belongings.");
    }

    public static Path generateRetirementPath() {
        ArrayList<Space> spaces = new ArrayList<Space>();
        for(int i = 0; i < Constants.PATH_SPACES - 1; i++) {
            spaces.add(new OrangeSpace());
        }
        spaces.add(new RetirementSpace());
        return new Path("Retirement Path", "rp", spaces.toArray(new Space[0]));
    }
}
