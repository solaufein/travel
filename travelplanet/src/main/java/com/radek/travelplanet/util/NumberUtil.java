package com.radek.travelplanet.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class NumberUtil {

    private static final Pattern NUMBERS_PATTERN = Pattern.compile("(\\d+)");

    private NumberUtil() {
    }

    public static String findTextNumber(String text) {
        StringBuilder sb = new StringBuilder();
        Matcher m = NUMBERS_PATTERN.matcher(text);
        while (m.find()) {
            sb.append(m.group());
        }
        return sb.toString();
    }

    public static int findNumber(String text) throws NumberFormatException {
        return Integer.parseInt(findTextNumber(text));
    }

}
