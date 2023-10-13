package com.java.actions;

import com.intellij.testFramework.TestDataPath;
import com.intellij.testFramework.fixtures.LightJavaCodeInsightFixtureTestCase;
import org.junit.Test;

@TestDataPath("/")
public class AutocompleteBuilderSetterChainTest extends LightJavaCodeInsightFixtureTestCase {

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