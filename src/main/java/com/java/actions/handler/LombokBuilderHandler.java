package com.java.actions.handler;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiType;
import com.java.actions.HandleResult;

import java.util.HashMap;
import java.util.Map;

import static com.java.actions.utils.GenericUtils.safeExtraction;

public class LombokBuilderHandler {
    public static HandleResult handleLombokAnnotation(PsiClass psiClass) {

        PsiField[] allFields = psiClass.getAllFields();

        Map<String, String> methodNameWithParamType = new HashMap<>();
        for (var field : allFields) {
            PsiType psiType = safeExtraction(field::getType, PsiType.VOID);
            if (!psiType.equalsToText("void")) {
                methodNameWithParamType.put(field.getName(), psiType.getPresentableText());
            }
        }
        return new HandleResult(methodNameWithParamType);
    }
}
