package com.java.actions.utils;

import java.util.Optional;
import java.util.function.Supplier;

public class GenericUtils {
    public static <T> T safeExtraction(Supplier<T> possibleNullSupplier, T defaultValue) {
        return Optional.ofNullable(possibleNullSupplier.get()).orElse(defaultValue);
    }
}
