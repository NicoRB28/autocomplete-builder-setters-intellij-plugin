package com.java.actions.handler;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiType;
import com.java.actions.Constants;
import com.java.actions.HandleResult;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.java.actions.utils.GenericUtils.safeExtraction;

public class LombokBuilderHandler {
    public static HandleResult handleLombokAnnotation(PsiClass psiClass) {

        PsiField[] allFields = psiClass.getAllFields();

        Map<String, String> methodNameWithParamType = new HashMap<>();
        Set<String> importsToAdd = Set.of();
        for (var field : allFields) {
            PsiType psiType = safeExtraction(field::getType, PsiType.VOID);
            if (!psiType.equalsToText("void")) {
                methodNameWithParamType.put(field.getName(), psiType.getPresentableText());
                importsToAdd = methodNameWithParamType
                        .values()
                        .stream()
                        .filter(Constants.DEFAULT_IMPORTS::containsKey)
                        .map(Constants.DEFAULT_IMPORTS::get)
                        .collect(Collectors.toSet());
            }
        }
        //return methodNameWithParamType;
        return new HandleResult(methodNameWithParamType, importsToAdd);
    }
}
