package com.radek.travelplanet.util;

public final class NumberUtil {

    private NumberUtil() {
    }

    public static int findFirstNumber(String text) throws NumberFormatException {
        return Integer.parseInt(findFirstTextNumber(text));
    }

    public static String findFirstTextNumber(String text) {
        String trimmedText = trimAll(text);
        return trimmedText.replaceFirst(".*?(\\d+).*", "$1");
    }

    private static String trimAll(String strScore) {
        return strScore.trim().replaceAll("\\s+", "");
    }
}
