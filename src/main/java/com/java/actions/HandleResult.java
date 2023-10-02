package com.java.actions;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.java.actions.Constants.DEFAULT_VALUES;

public record HandleResult(Map<String, String> methodNamesWithParamTypes, Set<String> importsToAdd) {

    public String getTextToBeInserted() {
        return  "." + methodNamesWithParamTypes.entrySet()
                .stream()
                .map(this::mapper)
                .collect(Collectors.joining("\n\t\t\t\t."));
    }

    private String mapper(Map.Entry<String, String> entry) {
        return entry.getKey() + "(" + DEFAULT_VALUES.get(entry.getValue().toUpperCase()) + ")";
    }
}
