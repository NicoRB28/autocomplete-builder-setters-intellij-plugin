package com.java.actions.handler;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiParameter;
import com.intellij.psi.PsiType;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import static com.java.actions.Constants.DEFAULT_VALUES;
import static com.java.actions.utils.GenericUtils.safeExtraction;

public class InnerBuilderHandler {
    @NotNull
    public static Map<PsiMethod, String> handleInnerBuilderImplementation(PsiClass[] innerClasses) {
        PsiMethod[] methods = innerClasses[0].getMethods();

        Map<PsiMethod, String> methodNameWithParamType = new HashMap<>();
        for (var method : methods) {
            PsiType psiType = safeExtraction(method::getReturnType, PsiType.VOID);
            if (method.getName().contains("set") && !psiType.equalsToText("void")) {
                var params = method.getParameterList();
                PsiParameter parameter = params.getParameter(0);
                var paramType = parameter.getType();
                methodNameWithParamType.put(method, paramType.getPresentableText());
            }
        }
        return methodNameWithParamType;
    }

    public static String mapper(Map.Entry<PsiMethod, String> entry) {
        return entry.getKey().getName() + "(" + DEFAULT_VALUES.get(entry.getValue().toUpperCase()) + ")";
    }
}