package ru.alvisid.pacs.util;

/**
 * Miscellaneous utility methods.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public class Util {
    /**
     * Checks that certain value is between two specified values: min and max
     * according to its comparator.
     *
     * @param value the certain value to check.
     * @param min   the min value.
     * @param max   the max value.
     * @param <T>   the type of the checked object
     * @return {@code true} if value is more or equals min and value is less or equals max.
     */
    public static <T extends Comparable<T>> boolean isBetween(T value, T min, T max) {
        if (min.compareTo(max) > 0) {
            throw new IllegalArgumentException("Value min=" + min + " must be less then max=" + max);
        }
        return value.compareTo(min) >= 0 && value.compareTo(max) <= 0;
    }

    // never instantiated
    private Util(){};
}
