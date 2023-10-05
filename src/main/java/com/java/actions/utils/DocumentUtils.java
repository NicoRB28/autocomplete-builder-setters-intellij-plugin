package com.java.actions.utils;

import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.project.Project;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiJavaFile;
import com.intellij.psi.impl.source.codeStyle.JavaCodeStyleManagerImpl;
import com.intellij.psi.search.GlobalSearchScope;

import java.util.Set;

public class DocumentUtils {

    private final Project project;
    private final PsiFile containingFile;
    private final PsiDocumentManager psiDocumentManager;
    private final Document document;

    public DocumentUtils(Project project, PsiElement element) {
        this.project = project;
        this.containingFile = element.getContainingFile();
        this.psiDocumentManager = PsiDocumentManager.getInstance(project);
        this.document = this.psiDocumentManager.getDocument(containingFile);
    }

    public void insertText(PsiElement theParent, String stringToAdd) {
        int offset = calculatePosition(theParent);
        document.insertString(offset, stringToAdd);
        saveChanges();
    }

    private int calculatePosition(PsiElement theParent) {
        int adjustment = 1;
        return theParent.getTextOffset() - adjustment;
    }

    public void insertImports(Set<String> imports) {
        if (containingFile instanceof PsiJavaFile) {
            imports.forEach(importStatement -> writeImport((PsiJavaFile) containingFile, importStatement));
        }
        saveChanges();
    }

    private void saveChanges() {
        psiDocumentManager.doPostponedOperationsAndUnblockDocument(document);
        psiDocumentManager.commitDocument(document);
        FileDocumentManager.getInstance().saveDocument(document);
    }


    private void writeImport(PsiJavaFile file, String importStatement) {
        var facade = JavaPsiFacade.getInstance(this.project);
        var javaCodeStyleManager = JavaCodeStyleManagerImpl.getInstance(project);
        var psiClass = facade.findClass(importStatement, GlobalSearchScope.allScope(project));
        javaCodeStyleManager.addImport(file, psiClass);
    }
}