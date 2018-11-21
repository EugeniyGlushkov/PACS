package ru.alvisid.pacs.util;

public class Util {
    public static <T extends Comparable <T>> boolean isBetween(T temp, T start, T end) {
        if (start.compareTo(end) > 0) {
            throw new IllegalArgumentException("Value start=" + start + " must be less then end=" + end);
        }
        return temp.compareTo(start) >= 0 && temp.compareTo(end) <= 0;
    }
}
