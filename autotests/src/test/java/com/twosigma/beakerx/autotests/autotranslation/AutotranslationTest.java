package com.twosigma.beakerx.autotests.autotranslation;

import com.twosigma.beakerx.autotests.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AutotranslationTest extends BaseTest {

    int cellIndex = 0;

    @BeforeClass
    public static void setupClass() {
        BaseTest.setupClass();
        beakerxPO.runNotebookByUrl("/autotests/ipynb/groovy/AutotranslationTest.ipynb");
    }

    @Test(priority = 1, description = "Execute Results output contains 'groovy value'.")
    public void setBeakerxObjectOnGroovy() {
        cellIndex = 0;
        WebElement codeCell = beakerxPO.runCodeCellByIndex(cellIndex);
        String txt = beakerxPO.getAllOutputsOfCodeCell(codeCell, beakerxPO.getAllOutputsExecuteResultsSelector())
                .get(0).getText();
        Assert.assertEquals(txt, "a groovy value");
    }

    @Test(priority = 5, description = "Execute Results output contains 'groovy value'.")
    public void translateGroovyToScala() {
        cellIndex++;
        WebElement codeCell = beakerxPO.runCodeCellByIndex(cellIndex);
        String txt = beakerxPO.getAllOutputsOfCodeCell(codeCell, beakerxPO.getAllOutputsExecuteResultsSelector())
                .get(0).getText();
        Assert.assertEquals(txt, "a groovy value");
    }

    @Test(priority = 10, description = "Execute Results output contains 'scala value'.")
    public void setBeakerxObjectOnScala() {
        cellIndex++;
        WebElement codeCell = beakerxPO.runCodeCellByIndex(cellIndex);
        String txt = beakerxPO.getAllOutputsOfCodeCell(codeCell, beakerxPO.getAllOutputsExecuteResultsSelector())
                .get(0).getText();
        Assert.assertTrue(txt.contains("3.14, scala, value"));
    }

    @Test(priority = 15, description = "Execute Results output contains 'scala value'.")
    public void translateScalaToGroovy() {
        cellIndex++;
        WebElement codeCell = beakerxPO.runCodeCellByIndex(cellIndex);
        String txt = beakerxPO.getAllOutputsOfCodeCell(codeCell, beakerxPO.getAllOutputsExecuteResultsSelector())
                .get(0).getText();
        Assert.assertTrue(txt.contains("3.14, scala, value"));
    }

    @Test(priority = 20, description = "Execute Results output contains 'scala value'.")
    public void translateScalaToClojure() {
        cellIndex++;
        WebElement codeCell = beakerxPO.runCodeCellByIndex(cellIndex);
        String txt = beakerxPO.getAllOutputsOfCodeCell(codeCell, beakerxPO.getAllOutputsExecuteResultsSelector())
                .get(0).getText();
        Assert.assertTrue(txt.contains("3.14, scala, value"));
    }

    @Test(priority = 25, description = "Execute Results output contains 'clojure value'.")
    public void setBeakerxObjectOnClojure() {
        cellIndex++;
        WebElement codeCell = beakerxPO.runCodeCellByIndex(cellIndex);
        String txt = beakerxPO.getAllOutputsOfCodeCell(codeCell, beakerxPO.getAllOutputsExecuteResultsSelector())
                .get(0).getText();
        Assert.assertTrue(txt.contains("345, clojure, value"));
    }

    @Test(priority = 30, description = "Execute Results output contains 'clojure value'.")
    public void translateClojureToGroovy() {
        cellIndex++;
        WebElement codeCell = beakerxPO.runCodeCellByIndex(cellIndex);
        String txt = beakerxPO.getAllOutputsOfCodeCell(codeCell, beakerxPO.getAllOutputsExecuteResultsSelector())
                .get(0).getText();
        Assert.assertTrue(txt.contains("345, clojure, value"));
    }

    @Test(priority = 35, description = "Execute Results output contains 'clojure value'.")
    public void translateClojureToJava() {
        cellIndex++;
        WebElement codeCell = beakerxPO.runCodeCellByIndex(cellIndex);
        String txt = beakerxPO.getAllOutputsOfCodeCell(codeCell, beakerxPO.getAllOutputsExecuteResultsSelector())
                .get(0).getText();
        Assert.assertTrue(txt.contains("345, clojure, value"));
    }

    @Test(priority = 40, description = "Execute Results output contains 'java value'.")
    public void setBeakerxObjectOnJava() {
        cellIndex++;
        WebElement codeCell = beakerxPO.runCodeCellByIndex(cellIndex);
        String txt = beakerxPO.getAllOutputsOfCodeCell(codeCell, beakerxPO.getAllOutputsExecuteResultsSelector())
                .get(0).getText();
        Assert.assertTrue(txt.contains("10, java, value"));
    }

    @Test(priority = 45, description = "Execute Results output contains 'java value'.")
    public void translateJavaToGroovy() {
        cellIndex++;
        WebElement codeCell = beakerxPO.runCodeCellByIndex(cellIndex);
        String txt = beakerxPO.getAllOutputsOfCodeCell(codeCell, beakerxPO.getAllOutputsExecuteResultsSelector())
                .get(0).getText();
        Assert.assertTrue(txt.contains("10, java, value"));
    }

    @Test(priority = 50, description = "Execute Results output contains 'java value'.")
    public void translateJavaToKotlin() {
        cellIndex++;
        WebElement codeCell = beakerxPO.runCodeCellByIndex(cellIndex);
        String txt = beakerxPO.getAllOutputsOfCodeCell(codeCell, beakerxPO.getAllOutputsExecuteResultsSelector())
                .get(0).getText();
        Assert.assertTrue(txt.contains("10, java, value"));
    }

    @Test(priority = 55, description = "Execute Results output contains 'kotlin value'.")
    public void setBeakerxObjectOnKotlin() {
        cellIndex++;
        WebElement codeCell = beakerxPO.runCodeCellByIndex(cellIndex);
        String txt = beakerxPO.getAllOutputsOfCodeCell(codeCell, beakerxPO.getAllOutputsExecuteResultsSelector())
                .get(0).getText();
        Assert.assertTrue(txt.contains("33, kotlin, value"));
    }

    @Test(priority = 60, description = "Execute Results output contains 'kotlin value'.")
    public void translateKotlinToGroovy() {
        cellIndex++;
        WebElement codeCell = beakerxPO.runCodeCellByIndex(cellIndex);
        String txt = beakerxPO.getAllOutputsOfCodeCell(codeCell, beakerxPO.getAllOutputsExecuteResultsSelector())
                .get(0).getText();
        Assert.assertTrue(txt.contains("33, kotlin, value"));
    }
}
