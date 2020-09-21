package model.utilities;

import java.util.Scanner;

/**
 * useful tools/functions that can be used for getting user input may it be an integer, string,
 * waiting for enter key, etc.
 */

public class InputUtil {
    /**
     * Use the java.util.Scanner to get an input then verifies
     * if the input by the user is in between the lowerBound
     * and upperBound, if it is not, ask and ask again.
     * @param message    message to ask
     * @param lowerBound minimum integer to be accepted
     * @param upperBound maximum integer to be accepted
     * @return valid input by the user
     */
    public static int scanInt(String message, int lowerBound, int upperBound) {
        Scanner scanner = new Scanner(System.in);
        int input = lowerBound - 1;
        do {
            System.out.print(message);
            if (scanner.hasNextInt()) // only accept integer inputs, and denies string inputs
                input = scanner.nextInt();
            else {
                System.out.println("Invalid input. Please enter a number from " + lowerBound + " to " + upperBound + "...\n");
                scanner.next();
                continue;
            }
        } while(input < lowerBound || input > upperBound);
        return input;
    }

    public static double scanDouble(String message, double lowerBound, double upperBound) {
        Scanner scanner = new Scanner(System.in);
        double input = lowerBound - 1;
        do {
            System.out.print(message);
            if (scanner.hasNextDouble()) // only accept integer inputs, and denies string inputs
                input = scanner.nextDouble();
            else {
                System.out.println("Invalid input. Please enter a number from " + lowerBound + " to " + upperBound + "...\n");
                scanner.next();
                continue;
            }
        } while(input < lowerBound || input > upperBound);
        return input;
    }

    public static void waitForEnterKey() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please press Enter to proceed...");
        scanner.nextLine();
    }
}
