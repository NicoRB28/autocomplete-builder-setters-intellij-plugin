package com.java.actions;

import com.intellij.openapi.projectRoots.JavaSdk;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.pom.java.LanguageLevel;
import com.intellij.testFramework.LightProjectDescriptor;
import com.intellij.testFramework.TestDataPath;
import com.intellij.testFramework.fixtures.LightJavaCodeInsightFixtureTestCase;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

@TestDataPath("/")
public class AutocompleteBuilderSetterChainTest extends LightJavaCodeInsightFixtureTestCase {

    @Override
    public void setUp() throws Exception {
        super.setUp();
        //myFixture.launchAction(new AutocompleteBuilderSetterChain());
        //myFixture.addClass("package java.lang; public final class String {}");
    }

/*    @Override
    protected @NotNull LightProjectDescriptor getProjectDescriptor() {
        return new ProjectDescriptor(LanguageLevel.JDK_17, true) {
            @Override
            public Sdk getSdk() {
                return JavaSdk.getInstance()
                        .createJdk(System.getProperty("java.specification.version"),
                                System.getProperty("java.home"), false);
            }
        };
    }*/

    @Override
    protected String getTestDataPath() {
        return "src/test/testData";
    }


    @Test
    public void testOne() {
        doTest("BuilderChain");
    }

    protected void doTest(String testName) {
        myFixture.configureByFile(testName+".before.java");
        myFixture.launchAction(new AutocompleteBuilderSetterChain());
        myFixture.checkResultByFile(testName+".after.java");
    }

}