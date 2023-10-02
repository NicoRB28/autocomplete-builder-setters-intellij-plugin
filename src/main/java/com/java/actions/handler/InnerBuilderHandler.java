package com.java.actions.handler;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiParameter;
import com.intellij.psi.PsiType;
import com.java.actions.Constants;
import com.java.actions.HandleResult;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.java.actions.utils.GenericUtils.safeExtraction;

public class InnerBuilderHandler {
    @NotNull
    public static HandleResult handleInnerBuilderImplementation(PsiClass[] innerClasses) {
        PsiMethod[] methods = innerClasses[0].getMethods();

        Map<String, String> methodNameWithParamType = new HashMap<>();
        Set<String> importsToAdd = Set.of();
        for (var method : methods) {
            PsiType psiType = safeExtraction(method::getReturnType, PsiType.VOID);
            if (method.getName().contains("set") && !psiType.equalsToText("void")) {
                var params = method.getParameterList();
                PsiParameter parameter = params.getParameter(0);
                var paramType = parameter.getType();
                methodNameWithParamType.put(method.getName(), paramType.getPresentableText());
                importsToAdd = methodNameWithParamType
                        .values()
                        .stream()
                        .filter(Constants.DEFAULT_IMPORTS::containsKey)
                        .map(Constants.DEFAULT_IMPORTS::get)
                        .collect(Collectors.toSet());
            }
        }
        return new HandleResult(methodNameWithParamType, importsToAdd);
        //return methodNameWithParamType;
    }
}