package model.utilities;

/**
 * useful tools/classes for formatting int or Integer related tasks
 */
public class IntUtil {
    /**
     * Splits an integer into segments
     * @param segments number of segments
     * @param n        maximum
     * @return returned splitted integers
     */
    public static int[] splitEqually(int segments, int n) {
        int ranges[] = new int[segments];

        for(int i = 0; i < segments; i++) {
            ranges[i] = n / segments * i;
        }

        return ranges;
    }
}
