package com.java.actions;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.java.actions.Constants.DEFAULT_VALUES;

public record HandleResult(Map<String, String> methodNamesWithParamTypes) {

    public String getTextToBeInserted() {
        return  "." + methodNamesWithParamTypes.entrySet()
                .stream()
                .map(this::mapper)
                .collect(Collectors.joining("\n\t\t\t\t."));
    }

    private String mapper(Map.Entry<String, String> entry) {
        return entry.getKey() + "(" + DEFAULT_VALUES.get(parseGenericType(entry.getValue()).toUpperCase()) + ")";
    }

    public Set<String> getImports() {
        return methodNamesWithParamTypes
                .values()
                .stream()
                .map(this::parseGenericType)
                .filter(Constants.DEFAULT_IMPORTS::containsKey)
                .map(Constants.DEFAULT_IMPORTS::get)
                .collect(Collectors.toSet());
    }

    private String parseGenericType(String presentableText) {
        if (presentableText.contains("<")) {
            return presentableText.split("<")[0];
        }
        return presentableText;
    }
}
