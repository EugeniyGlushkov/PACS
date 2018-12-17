package ru.alvisid.pacs.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Miscellaneous utility methods and constants for work with date and time.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public class DateTimeUtil {
    /**
     * Formatter for printing and parsing date-time objects.
     * Presents date in format: <li>"yyy-MM-dd"</li>
     *
     * @see DateTimeFormatter
     */
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Formatter for printing and parsing date-time objects.
     * Presents time in format: <li>"HH:mm"</li>
     *
     * @see DateTimeFormatter
     */
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    /**
     * Formatter for printing and parsing date-time objects.
     * Presents date-time in format: <li>"yyyy-MM-dd HH:mm"</li>
     *
     * @see DateTimeFormatter
     */
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Returns a String object representing a specified <b>LocalDate</b> object
     * formatting with specific {@code DateTimeFormatter}.
     *
     * @param ld the specified <b>LocalDate</b> object.
     * @return the String object representing a specified <b>LocalDate</b> object.
     */
    public static String toString(LocalDate ld) {
        return ld == null ? "" : ld.format(DATE_FORMATTER);
    }

    /**
     * Returns a String object representing a specified <b>LocalTime</b> object
     * formatting with specific {@code DateTimeFormatter}.
     *
     * @param lt the specified <b>LocalTime</b> object.
     * @return the String object representing a specified <b>LocalTime</b> object.
     */
    public static String toString(LocalTime lt) {
        return lt == null ? "" : lt.format(TIME_FORMATTER);
    }

    /**
     * Returns a String object representing a specified <b>LocalDateTime</b> object
     * formatting with specific {@code DateTimeFormatter}.
     *
     * @param ldt the specified <b>LocalDateTime</b> object.
     * @return the String object representing a specified <b>LocalDateTime</b> object.
     */
    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }

    // never instantiated
    private DateTimeUtil() {
    }
}
