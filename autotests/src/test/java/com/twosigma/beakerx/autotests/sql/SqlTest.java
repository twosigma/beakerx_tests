package com.twosigma.beakerx.autotests.sql;

import com.twosigma.beakerx.autotests.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SqlTest extends BaseTest {

    int cellIndex = 0;

    @BeforeClass
    public static void setupClass() {
        BaseTest.setupClass();
        beakerxPO.runNotebookByUrl("/autotests/ipynb/sql/SQLTest.ipynb");
    }

    @Test(priority = 1, description = "Stderr output is empty.")
    public void defineDatasourceOfH2() {
        cellIndex = 0;
        WebElement codeCell = beakerxPO.runCodeCellByIndex(cellIndex);
        int size = codeCell.findElements(beakerxPO.getAllOutputsStderrSelector()).size();
        Assert.assertEquals(size,0);
    }

    @Test(priority = 5, description = "Stderr output is empty")
    public void createTable() {
        cellIndex++;
        WebElement codeCell = beakerxPO.runCodeCellByIndex(cellIndex);
        int size = codeCell.findElements(beakerxPO.getAllOutputsStderrSelector()).size();
        Assert.assertEquals(size,0);
    }

    @Test(priority = 10, description = "Execute Results output contains 'Aquamarine'.")
    public void selectValueFromTable() {
        cellIndex++;
        WebElement codeCell = beakerxPO.runCodeCellByIndex(cellIndex);
        String txt = beakerxPO.getAllOutputsOfCodeCell(codeCell, beakerxPO.getAllOutputsExecuteResultsSelector())
                .get(0).getText();
        Assert.assertTrue(txt.contains("Aquamarine"));
    }

}
