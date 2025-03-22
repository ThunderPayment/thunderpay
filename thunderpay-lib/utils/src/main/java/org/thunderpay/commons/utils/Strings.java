/**
 * @file Strings.java
 * @author Krisna Pranav
 * @brief Strings
 * @version 1.0
 * @date 2025-03-22
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.commons.utils;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.CheckForNull;

public final class Strings {

    public static boolean isNullOrEmpty(final String string) {
        return string == null || string.isEmpty();
    }

    public static String emptyToNull(final String string) {
        return isNullOrEmpty(string) ? null : string;
    }

    public static List<String> split(final String string, final String separator) {
        if (isNullOrEmpty(string)) {
            return Collections.emptyList();
        }

        return Stream.of(string.split(separator))
                .filter(s -> !s.isBlank())
                .map(String::trim)
                .collect(Collectors.toUnmodifiableList());
    }

    public static String nullToEmpty(@CheckForNull final String string) {
        return (string == null) ? "" : string;
    }

    public static boolean containsUpperCase(final String str) {
        if (isNullOrEmpty(str)) {
            return false;
        }
        return !str.equals(str.toLowerCase());
    }

    public static String toCamelCase(String str, final boolean capitalizeFirstLetter, final char... delimiters) {
        if (str == null || str.isBlank()) {
            return str;
        }
        str = str.toLowerCase();
        final int strLen = str.length();
        final int[] newCodePoints = new int[strLen];
        int outOffset = 0;
        final Set<Integer> delimiterSet = toDelimiterSet(delimiters);
        boolean capitalizeNext = capitalizeFirstLetter;
        for (int index = 0; index < strLen; ) {
            final int codePoint = str.codePointAt(index);

            if (delimiterSet.contains(codePoint)) {
                capitalizeNext = outOffset != 0;
                index += Character.charCount(codePoint);
            } else if (capitalizeNext || outOffset == 0 && capitalizeFirstLetter) {
                final int titleCaseCodePoint = Character.toTitleCase(codePoint);
                newCodePoints[outOffset++] = titleCaseCodePoint;
                index += Character.charCount(titleCaseCodePoint);
                capitalizeNext = false;
            } else {
                newCodePoints[outOffset++] = codePoint;
                index += Character.charCount(codePoint);
            }
        }

        return new String(newCodePoints, 0, outOffset);
    }

    private static Set<Integer> toDelimiterSet(final char[] delimiters) {
        final Set<Integer> delimiterHashSet = new HashSet<>();
        delimiterHashSet.add(Character.codePointAt(new char[]{' '}, 0));
        if (delimiters == null || delimiters.length == 0) {
            return delimiterHashSet;
        }

        for (int index = 0; index < delimiters.length; index++) {
            delimiterHashSet.add(Character.codePointAt(delimiters, index));
        }
        return delimiterHashSet;
    }

    public static String toSnakeCase(final String str) {
        final StringBuilder result = new StringBuilder();

        boolean lastUppercase = false;

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            final char lastEntry = i == 0 ? 'X' : result.charAt(result.length() - 1);
            if (ch == '_') {
                lastUppercase = false;

                if (lastEntry == '_') {
                    continue;
                } else {
                    ch = '_';
                }
            } else if (Character.isUpperCase(ch)) {
                ch = Character.toLowerCase(ch);

                if (i > 0) {
                    if (lastUppercase) {
                        if (i + 1 < str.length()) {
                            char next = str.charAt(i + 1);
                            if (!Character.isUpperCase(next) && Character.isAlphabetic(next)) {
                                if (lastEntry != '_') {
                                    result.append('_');
                                }
                            } else {
                                result.append('_');
                            }
                        }
                    } else {
                        if (lastEntry != '_') {
                            result.append('_');
                        }
                    }
                }
                lastUppercase = true;
            } else {
                lastUppercase = false;
            }

            result.append(ch);
        }
        return result.toString();
    }
}