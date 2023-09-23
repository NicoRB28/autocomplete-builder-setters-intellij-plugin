package com.java.actions.utils;

import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;

public class DocumentUtils {
    private DocumentUtils() {
    }

    public static void insertText(@NotNull Project project, @NotNull PsiElement element, PsiElement theParent, String stringToAdd) {
        PsiDocumentManager psiDocumentManager = PsiDocumentManager.getInstance(project);
        PsiFile file = element.getContainingFile();
        Document document = psiDocumentManager.getDocument(file);
        document.insertString(theParent.getTextOffset() - 1, stringToAdd);
        psiDocumentManager.doPostponedOperationsAndUnblockDocument(document);
        psiDocumentManager.commitDocument(document);
        FileDocumentManager.getInstance().saveDocument(document);
    }
}