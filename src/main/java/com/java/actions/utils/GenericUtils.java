package com.java.actions.utils;

import com.java.actions.BuilderType;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class GenericUtils {

    public static <T> T safeExtraction(Supplier<T> possibleNullSupplier, T defaultValue) {
        return Optional.ofNullable(possibleNullSupplier.get()).orElse(defaultValue);
    }

    @NotNull
    public static <T> String createTextToInsert(BuilderType builderType, Map<T, String> methodNameWithParamType) {
        return  "." + methodNameWithParamType.entrySet()
                .stream()
                .map(entry -> builderType.mapper.apply(entry))
                .collect(Collectors.joining("\n\t\t\t\t."));
    }
}
