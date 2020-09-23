package com.twosigma.beakerx.autotests.python;

import com.twosigma.beakerx.autotests.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class TableAPITest extends BaseTest {

    int cellIndex = 0;
    String imgDir = "python" + beakerxPO.fileSeparator + "tableAPITest";

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

    @Test(priority = 10, description = "should display beakerx table")
    public void callTableDisplay() throws IOException {
        cellIndex++;
        WebElement element = beakerxPO.runCellToGetDataGridViewport(cellIndex);
        beakerxPO.testTableScreenshot(element, imgDir, "codeCell3");
}

    @Test(priority = 15, description = "should display beakerx table")
    public void defaultAlignmentProviderForColumn() throws IOException {
        cellIndex++;
        WebElement element = beakerxPO.runCellToGetDataGridViewport(cellIndex);
        beakerxPO.testTableScreenshot(element,  imgDir, "codeCell4");
    }

    @Test(priority = 20, description = "should display beakerx table")
    public void setAlignmentProviderForColumn() throws IOException {
        cellIndex++;
        WebElement element = beakerxPO.runCellToGetDataGridViewport(cellIndex);
        beakerxPO.testTableScreenshot(element,  imgDir, "codeCell5");
    }

    @Test(priority = 25, description = "should display beakerx table")
    public void setAlignmentProviderForType() throws IOException {
        cellIndex++;
        WebElement element = beakerxPO.runCellToGetDataGridViewport(cellIndex);
        beakerxPO.testTableScreenshot(element,  imgDir, "codeCell6");
    }

    @Test(priority = 30, description = "should display beakerx table")
    public void setBarChartsRenderer() throws IOException {
        cellIndex++;
        WebElement element = beakerxPO.runCellToGetDataGridViewport(cellIndex);
        beakerxPO.testTableScreenshot(element,  imgDir, "codeCell7");
    }

    @Test(priority = 35, description = "should display beakerx table")
    public void setFormattingString() throws IOException {
        cellIndex++;
        WebElement element = beakerxPO.runCellToGetDataGridViewport(cellIndex);
        beakerxPO.testTableScreenshot(element,  imgDir, "codeCell8");
    }

    @Test(priority = 40, description = "should display beakerx table")
    public void setHTMLFormat() throws IOException {
        cellIndex++;
        WebElement element = beakerxPO.runCellToGetDataGridViewport(cellIndex);
        beakerxPO.testTableScreenshot(element,  imgDir, "codeCell9");
    }

    @Test(priority = 45, description = "should display beakerx table")
    public void setColumnVisibility() throws IOException {
        cellIndex++;
        WebElement element = beakerxPO.runCellToGetDataGridViewport(cellIndex);
        beakerxPO.testTableScreenshot(element,  imgDir, "codeCell10");
    }

    @Test(priority = 50, description = "should display beakerx table")
    public void setColumnOrder() throws IOException {
        cellIndex++;
        WebElement element = beakerxPO.runCellToGetDataGridViewport(cellIndex);
        beakerxPO.testTableScreenshot(element,  imgDir, "codeCell11");
    }

    @Test(priority = 55, description = "should display beakerx table")
    public void setHighlighterForRow() throws IOException {
        cellIndex++;
        WebElement element = beakerxPO.runCellToGetDataGridViewport(cellIndex);
        beakerxPO.testTableScreenshot(element,  imgDir, "codeCell12");
    }

    @Test(priority = 60, description = "should display beakerx table")
    public void setHighlighterForColumn() throws IOException {
        cellIndex++;
        WebElement element = beakerxPO.runCellToGetDataGridViewport(cellIndex);
        beakerxPO.testTableScreenshot(element,  imgDir, "codeCell13");
    }

    @Test(priority = 65, description = "should display beakerx table")
    public void removeAllHighlighters() throws IOException {
        cellIndex++;
        WebElement element = beakerxPO.runCellToGetDataGridViewport(cellIndex);
        beakerxPO.testTableScreenshot(element,  imgDir, "codeCell14");
    }

    @Test(priority = 70, description = "should display beakerx table")
    public void setHighlighterForType() throws IOException {
        cellIndex++;
        WebElement element = beakerxPO.runCellToGetDataGridViewport(cellIndex);
        beakerxPO.testTableScreenshot(element,  imgDir, "codeCell15");
    }

    @Test(priority = 75, description = "should display beakerx table")
    public void setUniqueEntriesHighlighter() throws IOException {
        cellIndex++;
        WebElement element = beakerxPO.runCellToGetDataGridViewport(cellIndex);
        beakerxPO.testTableScreenshot(element,  imgDir, "codeCell16");
    }

    @Test(priority = 80, description = "should display beakerx table")
    public void addCellHighlighter() throws IOException {
        cellIndex++;
        WebElement element = beakerxPO.runCellToGetDataGridViewport(cellIndex);
        beakerxPO.testTableScreenshot(element,  imgDir, "codeCell17");
    }

    @Test(priority = 85, description = "should display beakerx table")
    public void setFontSize() throws IOException {
        cellIndex++;
        WebElement element = beakerxPO.runCellToGetDataGridViewport(cellIndex);
        beakerxPO.testTableScreenshot(element,  imgDir, "codeCell18");
    }

    @Test(priority = 90, description = "should display beakerx table")
    public void setHeadersVertical() throws IOException {
        cellIndex++;
        WebElement element = beakerxPO.runCellToGetDataGridViewport(cellIndex);
        beakerxPO.testTableScreenshot(element,  imgDir, "codeCell19");
    }

    @Test(priority = 95, description = "should display beakerx table")
    public void setFontColorProvider() throws IOException {
        cellIndex++;
        WebElement element = beakerxPO.runCellToGetDataGridViewport(cellIndex);
        beakerxPO.testTableScreenshot(element,  imgDir, "codeCell20");
    }

    @Test(priority = 100, description = "should display tooltip on table")
    public void addToolTip() {
        cellIndex++;
        WebElement element = beakerxPO.runCellToGetDataGridViewport(cellIndex);
        Point pt = element.getLocation();
        beakerxPO.getAction().moveByOffset(pt.getX() + 20, pt.getY() + 20).pause(1500).perform();
        WebElement tooltip = beakerxPO.getDataGridTooltip(element);
        beakerxPO.testTextTableValue(tooltip.getText(), imgDir, "codeCell21");
    }

    @Test(priority = 105, description = "should display beakerx table")
    public void callImageFormat() throws IOException {
        cellIndex++;
        WebElement element = beakerxPO.runCellToGetDataGridViewport(cellIndex);
        beakerxPO.testTableScreenshot(element,  imgDir, "codeCell22");
    }

    @Test(priority = 110, description = "should display beakerx table")
    public void tableDisplayStringFormat_getImageFormat() throws IOException {
        cellIndex++;
        WebElement element = beakerxPO.runCellToGetDataGridViewport(cellIndex);
        beakerxPO.testTableScreenshot(element,  imgDir, "codeCell23");
    }

    @Test(priority = 115, description = "should display beakerx table")
    public void seamlessUpdateOfTable() throws IOException {
        cellIndex++;
        WebElement element = beakerxPO.runCellToGetDataGridViewport(cellIndex);
        beakerxPO.testTableScreenshot(element,  imgDir, "codeCell24_1");

        beakerxPO.runCodeCellByIndex(++cellIndex);
        beakerxPO.testTableScreenshot(element,  imgDir, "codeCell24_2");
    }

}
