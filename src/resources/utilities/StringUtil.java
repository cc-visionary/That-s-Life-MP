package resources.utilities;

import java.util.ArrayList;

/**
 * String Utilities Class
 *
 * @author      Christopher G. Lim
 * @version     1.0
 * @last-edited Aug 18, 2020
 * @since       Aug 18, 2020
 */

public class StringUtil {
    /**
     * Formats the string to place it at the center
     * @param text text to be formatted
     * @param len maximum length of the whole string
     * @return
     */
    public static String centerString(String text, int len) {
        int whitespace = len - text.length();
        int left = whitespace / 2;
        int right = whitespace / 2;

        // if allowance whitespaces are odd,
        // increment right by 1
        if(whitespace % 2 == 1) right++;

        return String.format("%"+left+"s%s%"+right+"s", "",text,"");
    }

    /**
     * Splits the string into several ArrayList so that
     * it won't overflow a line
     * @param text text to be formatted
     * @param len maximum length of the whole string/line
     * @return
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
