package com.java.actions;

import com.intellij.codeInsight.intention.BaseElementAtCaretIntentionAction;
import com.intellij.codeInspection.util.IntentionFamilyName;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethodCallExpression;
import com.intellij.util.IncorrectOperationException;
import com.java.actions.handler.InnerBuilderHandler;
import com.java.actions.handler.LombokBuilderHandler;
import com.java.actions.utils.DocumentUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Predicate;

import static com.java.actions.Constants.LOMBOK_BUILDER;
import static com.java.actions.Predicates.hasInnerBuilder;
import static com.java.actions.utils.GenericUtils.safeExtraction;
import static java.util.Objects.requireNonNull;

public class AutocompleteBuilderSetterChain extends BaseElementAtCaretIntentionAction {

    @Override
    public void invoke(@NotNull Project project, Editor editor, @NotNull PsiElement element) throws IncorrectOperationException {
        var theParent = element.getParent();
        var children = theParent.getChildren();
        var theMethodCall = children[0];
         if (theMethodCall instanceof PsiMethodCallExpression method) {
            var containingClass = method.resolveMethod().getContainingClass();
            PsiClass[] innerClasses = containingClass.getInnerClasses();

            boolean hasLombokBuilder = containingClass.hasAnnotation(LOMBOK_BUILDER);
            Predicate<PsiClass> hasInnerBuilder = innerClass -> innerClass.getName().toUpperCase().contains("BUILDER");

            var hasBuilderInside = Arrays.stream(innerClasses).anyMatch(hasInnerBuilder);

            if (!hasBuilderInside && !hasLombokBuilder) return;

            HandleResult handleResult = null;
            if (hasBuilderInside) {
                handleResult = InnerBuilderHandler.handleInnerBuilderImplementation(innerClasses);
            }
            if (hasLombokBuilder) {
                handleResult = LombokBuilderHandler.handleLombokAnnotation(containingClass);
            }
            writeToDoc(project, element, handleResult);
        }
    }

    private void writeToDoc(@NotNull Project project, @NotNull PsiElement element, HandleResult handleResult) {
        var documentUtils  = new DocumentUtils(project, element);
        documentUtils.insertImports(handleResult.getImports());
        documentUtils.insertText(element.getParent(), handleResult.getTextToBeInserted());
    }

    @Override
    public boolean isAvailable(@NotNull Project project, Editor editor, @NotNull PsiElement element) {
        var parent = element.getParent();
        var child = parent.getChildren()[0];
        if (child instanceof PsiMethodCallExpression methodCall) {
            PsiClass containingClass = requireNonNull(methodCall.resolveMethod()).getContainingClass();
            return containingClass != null && validate(containingClass);
        }
        return false;
    }

    /**
     * Validate if the class has a builder implementation inside or if it is annotated with
     * Lombok Builder annotation.
     * @param cointainedClass
     * @return
     */
    private boolean validate(PsiClass cointainedClass) {
        return validateIfHasABuilderInside(cointainedClass) || validateIfHasLombokBuilder(cointainedClass);
    }

    private boolean validateIfHasLombokBuilder(PsiClass containedClass) {
        return containedClass.hasAnnotation(LOMBOK_BUILDER);
    }


    private boolean validateIfHasABuilderInside(PsiClass containedClass) {
        return Arrays
                .stream(containedClass.getInnerClasses())
                .filter(Objects::nonNull)
                .map(psiClass -> safeExtraction(psiClass::getName, ""))
                .anyMatch(hasInnerBuilder);
    }

    /**
     * Text to be shown in the intention actions availables
     * @return the action name
     */
    public @NotNull String getText() {
        return getFamilyName();
    }

    @Override
    public @NotNull @IntentionFamilyName String getFamilyName() {
        return "Autocomplete builder setters";
    }


}
