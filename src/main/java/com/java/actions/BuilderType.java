package com.java.actions;

import com.java.actions.handler.InnerBuilderHandler;
import com.java.actions.handler.LombokBuilderHandler;

import java.util.Map;
import java.util.function.Function;

public enum BuilderType {
    LOMBOK_BUILDER(LombokBuilderHandler::mapper),
    INNER_BUILDER(InnerBuilderHandler::mapper);

    public Function<Map.Entry, String> mapper;

    BuilderType(Function<Map.Entry, String> createTextToInsert) {
        this.mapper = createTextToInsert;
    }
}
