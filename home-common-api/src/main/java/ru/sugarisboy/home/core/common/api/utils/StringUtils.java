package ru.sugarisboy.home.core.common.api.utils;

import java.util.Collection;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    public static boolean containsAnyIgnoreCase(String source, Collection<String> any) {
        source = source.toLowerCase();
        return any.stream().map(String::toLowerCase).anyMatch(source::contains);
    }

    public static boolean startWithAnyIgnoreCase(String source, Collection<String> any) {
        source = source.toLowerCase();
        return any.stream().map(String::toLowerCase).anyMatch(source::startsWith);
    }

    public static Optional<String> extractByRegEx(String source, String regEx) {
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(source);
        if (matcher.find()) {
            return Optional.of(matcher.group());
        }
        return Optional.empty();
    }
}
