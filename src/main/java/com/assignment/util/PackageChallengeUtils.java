package com.assignment.util;

public class PackageChallengeUtils {

    public static final double MAX_VALUE = 100 * 100;
    public static final double MAX_WEIGHT = 100 * 100;
    public static final int MAX_ITEMS_PER_LINE = 15;
    public static final String REGEX = "[0-9€,.()]+";
    public static final String SPACE = " ";
    public static final char OPENINGS_CHAR = '(';
    public static final char CLOSING_CHAR = ')';
    public static final String COMMA = ",";
    public static final char EURO = '€';
    public static final String SEMI_COLON = ":";
    public static final String DASH = "-";

    private PackageChallengeUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static String removeLeadingChar(String s, Character c) {
        StringBuilder sb = new StringBuilder(s);
        while (sb.length() > 0 && sb.charAt(0) == c) {
            sb.deleteCharAt(0);
        }
        return sb.toString();
    }

    public static String removeTrailingChar(String s, Character c) {
        StringBuilder sb = new StringBuilder(s);
        while (sb.length() > 0 && sb.charAt(sb.length() - 1) == c) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }
}
