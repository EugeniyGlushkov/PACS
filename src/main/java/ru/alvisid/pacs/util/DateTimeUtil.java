package ru.alvisid.pacs.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static <T extends Comparable<T>> boolean isBetween(T temp, T start, T end) {
        if (start.compareTo(end) > 0) {
            throw new IllegalArgumentException("Value start=" + start + " must be less then end=" + end);
        }
        return temp.compareTo(start) >= 0 && temp.compareTo(end) <= 0;
    }

    public static String toString (LocalDate ld) {
        return ld == null ? "" : ld.format(DATE_FORMATTER);
    }

    public static String toString (LocalTime lt) {
        return lt == null ? "" : lt.format(TIME_FORMATTER);
    }

    public static String toString (LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }
}
