package main.utilities;

import java.util.Scanner;

/**
 * Scanner Utilities Class
 *   useful tools/functions that can be used for
 *   getting user input may it be an integer, string, etc.
 *
 * @author        Christopher G. Lim
 * @version       1.0
 * @last_modified Aug 27, 2020
 * @since         Aug 27, 2020
 */

public class ScannerUtil {
    /**
     * Use the java.util.Scanner to get an input then verifies
     * if the input by the user is in between the lowerBound
     * and upperBound, if it is not, ask and ask again.
     * @param message message to ask
     * @param lowerBound
     * @param upperBound
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
}
