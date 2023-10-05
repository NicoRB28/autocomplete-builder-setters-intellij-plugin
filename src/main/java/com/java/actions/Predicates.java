package com.java.actions;

import java.util.function.Predicate;

public class Predicates {
    public static final Predicate<String> hasInnerBuilder = innerClass -> innerClass.toUpperCase().contains("BUILDER");
    private Predicates() {

    }
}
