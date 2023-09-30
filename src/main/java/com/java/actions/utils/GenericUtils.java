package com.java.actions.utils;

import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.java.actions.Constants.DEFAULT_VALUES;

public class GenericUtils {

    public static <T> T safeExtraction(Supplier<T> possibleNullSupplier, T defaultValue) {
        return Optional.ofNullable(possibleNullSupplier.get()).orElse(defaultValue);
    }

    @NotNull
    public static String createTextToInsert(Map<String, String> methodNameWithParamType) {
        return  "." + methodNameWithParamType.entrySet()
                .stream()
                .map(GenericUtils::mapper)
                .collect(Collectors.joining("\n\t\t\t\t."));
    }

    private static String mapper(Map.Entry<String, String> entry) {
        return entry.getKey() + "(" + DEFAULT_VALUES.get(entry.getValue().toUpperCase()) + ")";
    }
}
