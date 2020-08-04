package com.twosigma.beakerx.autotests.python;

import com.twosigma.beakerx.autotests.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class TableAPITest extends BaseTest {

    int cellIndex = 0;
    String imgDir = "python" + beakerxPO.fileSeparator + "tableAPITest";
    boolean createExpectedData = false;

    @BeforeClass
    public static void setupClass() {
        BaseTest.setupClass();
        beakerxPO.runNotebookByUrl("/autotests/ipynb/python/TableAPITest.ipynb");
    }

    @Test(priority = 1, description = "Execute Results output contains 'no errors'.")
    public void importTableDisplay() {
        cellIndex = 0;
        WebElement codeCell = beakerxPO.runCodeCellByIndex(cellIndex);
        String txt = beakerxPO.getAllOutputsOfCodeCell(codeCell, beakerxPO.getAllOutputsExecuteResultsSelector())
                .get(0).getText();
        Assert.assertTrue(txt.contains("no errors"));
    }

    @Test(priority = 5, description = "should display default table")
    public void pandasReadCSV() {
        cellIndex++;
        WebElement codeCell = beakerxPO.runCodeCellByIndex(cellIndex);
        WebElement output = beakerxPO.getAllOutputsOfCodeCell(codeCell, beakerxPO.getAllOutputsExecuteResultsSelector()).get(0);
        Assert.assertTrue(output.findElements(By.tagName("table")).size() > 0);
        String info = output.findElement(By.cssSelector("div > p")).getText();
        Assert.assertTrue(info.contains("313 rows"));
        Assert.assertTrue(info.contains("11 columns"));
    }

    @Test(priority = 15, description = "should display beakerx table")
    public void callTableDisplay() throws IOException {
        cellIndex++;
        testTableScreenshot(cellIndex, "codeCell3");
}

    @Test(priority = 15, description = "should display beakerx table")
    public void defaultAlignmentProviderForColumn() throws IOException {
        cellIndex++;
        testTableScreenshot(cellIndex, "codeCell4");
    }

    @Test(priority = 20, description = "should display beakerx table")
    public void setAlignmentProviderForColumn() throws IOException {
        cellIndex++;
        testTableScreenshot(cellIndex, "codeCell5");
    }

    @Test(priority = 25, description = "should display beakerx table")
    public void setAlignmentProviderForType() throws IOException {
        cellIndex++;
        testTableScreenshot(cellIndex, "codeCell6");
    }

    @Test(priority = 30, description = "should display beakerx table")
    public void setBarChartsRenderer() throws IOException {
        cellIndex++;
        testTableScreenshot(cellIndex, "codeCell7");
    }

    @Test(priority = 35, description = "should display beakerx table")
    public void setFormattingString() throws IOException {
        cellIndex++;
        testTableScreenshot(cellIndex, "codeCell8");
    }

    private void testTableScreenshot(int cellIndex, String fileName) throws IOException {
        WebElement codeCell = beakerxPO.runCodeCellByIndex(cellIndex);
        beakerxPO.scrollIntoView(codeCell);
        WebElement element = codeCell.findElement(By.cssSelector("div.p-DataGrid-viewport"));
        if(createExpectedData) {
            beakerxPO.createExpectedScreenshot(element, imgDir, fileName);
        }
        else {
            int diff = beakerxPO.checkScreenshot(element, imgDir, fileName);
            Assert.assertEquals(diff, 0);
        }
    }

}
