package model;

/**
 * <h1>Constants Class</h1>
 * <p>Stores different constants such as</p>
 * <ul>
 *     <li>Types of Cards</li>
 *     <li>Types of Careers</li>
 *     <li>Types of Spaces</li>
 *     <li>Names of Green Spaces</li>
 *     <li>Names of Magenta Spaces</li>
 *     <li>Game Config</li>
 * </ul>
 */
public class Constants {
    // Types of Cards
    final public static String ACTION_CARD = "Action Card";
    final public static String BLUE_CARD = "Blue Card";
    final public static String CAREER_CARD = "Career Card";
    final public static String SALARY_CARD = "Salary Card";
    final public static String HOUSE_CARD = "House Card";

    // Types of Careers
    final public static String LAWYER = "Lawyer";
    final public static String ACCOUNTANT = "Accountant";
    final public static String COMPUTER_CONSULTANT = "Computer Consultant";
    final public static String DOCTOR = "Doctor";
    final public static String SERVER = "Server";
    final public static String RACECAR_DRIVER = "Racecar Driver";
    final public static String ATHLETE = "Athlete";

    // Types of Spaces
    final public static String BLUE_SPACE = "Blue Space";
    final public static String GREEN_SPACE = "Green Space";
    final public static String MAGENTA_SPACE = "Magenta Space";
    final public static String ORANGE_SPACE = "Orange Space";
    final public static String RETIREMENT_SPACE = "Retirement Space";

    // Different Green Spaces
    final public static String PAY_DAY = "Pay Day";
    final public static String PAY_RAISE = "Pay Raise";

    // Different Magenta Spaces
    final public static String BUY_A_HOUSE = "Buy a House";
    final public static String JOB_SEARCH = "Job Search";
    final public static String COLLEGE_CARREER_CHOICE = "College Career Choice";
    final public static String GET_MARRIED = "Get Married";
    final public static String WHICH_PATH = "Which Path";

    // orange deck
    final private static int totalOrangeCard = 50;
    final private static double collectBankProportion = 0.4, payBankProportion = 0.4, collectPlayerProportion = 0.1, payPlayerProportion = 0.1;
    final public static int nCollectBank = (int) (totalOrangeCard * collectBankProportion);
    final public static int nPayBank = (int) (totalOrangeCard * payBankProportion);
    final public static int nCollectPlayer = (int) (totalOrangeCard * collectPlayerProportion);
    final public static int nPayPlayer = totalOrangeCard - (nCollectBank + nPayBank + nCollectPlayer);

    // Path Spaces
    final public static int PATH_SPACES = 20;

    // Game Settings
    final public static int MIN_PLAYER = 2;
    final public static int MAX_PLAYER = 3;
    final public static int MONEY_INC = 50000;
    final public static int MIN_MONEY = 50000;
    final public static int MAX_MONEY = 1000000;

    // Miscellaneous Config
    final public static double HEXAGON_SIZE = 25;
}
