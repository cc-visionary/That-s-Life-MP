package main.utilities;

import java.util.ArrayList;

/**
 * String Utilities Class
 *   useful tools/classes for formatting Strings and other String related tasks
 */

public class StringUtil {
    /**
     * Formats the string to place it at the center
     * @param text text to be formatted
     * @param len  maximum length of the whole string
     * @return     the formatted string
     */
    public static String centerString(String text, int len) {
        int whitespace = len - text.length();
        int left = whitespace / 2;  // divide the remaining white space for
        int right = whitespace / 2; // the left and right

        // if allowance whitespaces are odd,
        // increment right by 1
        if(whitespace % 2 == 1) right++;

        // if text.length > than len
        if(whitespace < 0) {
            return text;
        }

        String leftSpace = "", rightSpace = "";
        for(int i = 0; i < left; i++) leftSpace += " ";
        for(int i = 0; i < right; i++) rightSpace += " ";

        return leftSpace + text + rightSpace;
    }

    /**
     * Splits the string into several ArrayList where each string has a length of parameter len
     * so that it won't overflow a line
     * @param text text to be formatted
     * @param len  maximum length of the whole string/line
     * @return     ArrayList of the splitted string
     */
    public static ArrayList<String> splitStringLength(String text, int len) {
        ArrayList<String> splittedString = new ArrayList<String>();
        String gatheredString = "";
        int length = 0;
        for(String str : text.split(" ")) {
            // concatenate the curr string
            gatheredString += (length == 0 ? "" : " ") + str;
            length += gatheredString.length();

            // if concatanated string length is greater than length,
            // add it to the ArrayList
            if(length > len) {
                length = 0;
                splittedString.add(gatheredString);
                gatheredString = "";
            }
        }

        // for the last iteration concatanated string
        // whose lengtht was not greater than len
        if(length > 0) splittedString.add(gatheredString);

        return splittedString;
    }
}
