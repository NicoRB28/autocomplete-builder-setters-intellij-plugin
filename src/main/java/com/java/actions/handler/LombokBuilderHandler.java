package com.java.actions.handler;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiType;

import java.util.HashMap;
import java.util.Map;

import static com.java.actions.Constants.DEFAULT_VALUES;
import static com.java.actions.utils.GenericUtils.safeExtraction;

public class LombokBuilderHandler {
    public static Map<String, String> handleLombokAnnotation(PsiClass psiClass) {

        PsiField[] allFields = psiClass.getAllFields();

        Map<String, String> methodNameWithParamType = new HashMap<>();
        for (var field : allFields) {
            PsiType psiType = safeExtraction(field::getType, PsiType.VOID);
            if (!psiType.equalsToText("void")) {
                methodNameWithParamType.put(field.getName(), psiType.getPresentableText());
            }
        }
        return methodNameWithParamType;
    }

    public static String mapper(Map.Entry<String, String> entry) {
        return entry.getKey() + "(" + DEFAULT_VALUES.get(entry.getValue().toUpperCase())+")";
    }
}
