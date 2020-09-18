package com.twosigma.beakerx.autotests.python;

import com.twosigma.beakerx.autotests.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class TableInputDataTest extends BaseTest {

    int cellIndex = 0;
    String imgDir = "python" + beakerxPO.fileSeparator + "tableInputDataTest";

    @BeforeClass
    public static void setupClass() {
        BaseTest.setupClass();
        beakerxPO.runNotebookByUrl("/autotests/ipynb/python/TableInputDataTest.ipynb");
    }

        @Test(priority = 1, description = "Execute Results output contains 'no errors'.")
        public void importTableDisplay() {
            cellIndex = 0;
            WebElement codeCell = beakerxPO.runCodeCellByIndex(cellIndex);
            String txt = beakerxPO.getAllOutputsOfCodeCell(codeCell, beakerxPO.getAllOutputsExecuteResultsSelector())
                    .get(0).getText();
            Assert.assertTrue(txt.contains("no errors"));
        }

        @Test(priority = 5, description = "should display beakerx table")
        public void recognizeArrayOfIntegers() throws IOException {
            cellIndex++;
            WebElement element = beakerxPO.runCellToGetDataGridViewport(cellIndex);
            beakerxPO.testTableScreenshot(element, imgDir, "codeCell1");
        }

        @Test(priority = 10, description = "should display beakerx table")
        public void recognize2DArrayOfIntegers() throws IOException {
            cellIndex++;
            WebElement element = beakerxPO.runCellToGetDataGridViewport(cellIndex);
            beakerxPO.testTableScreenshot(element,  imgDir, "codeCell2");
        }

        @Test(priority = 15, description = "should display beakerx table")
        public void recognizeArrayOfDecimals() throws IOException {
            cellIndex++;
            WebElement element = beakerxPO.runCellToGetDataGridViewport(cellIndex);
            beakerxPO.testTableScreenshot(element,  imgDir, "codeCell3");
        }

        @Test(priority = 20, description = "should display beakerx table")
        public void recognize2DArrayOfDecimals() throws IOException {
            cellIndex++;
            WebElement element = beakerxPO.runCellToGetDataGridViewport(cellIndex);
            beakerxPO.testTableScreenshot(element,  imgDir, "codeCell4");
        }

        @Test(priority = 25, description = "should display beakerx table")
        public void recognizeArrayOfStrings() throws IOException {
            cellIndex++;
            WebElement element = beakerxPO.runCellToGetDataGridViewport(cellIndex);
            beakerxPO.testTableScreenshot(element,  imgDir, "codeCell5");
        }

        @Test(priority = 30, description = "should display beakerx table")
        public void recognize2DArrayOfStrings() throws IOException {
            cellIndex++;
            WebElement element = beakerxPO.runCellToGetDataGridViewport(cellIndex);
            beakerxPO.testTableScreenshot(element,  imgDir, "codeCell6");
        }

        @Test(priority = 35, description = "should display beakerx table")
        public void recognizeArrayOfIntegerArrays() throws IOException {
            cellIndex++;
            WebElement element = beakerxPO.runCellToGetDataGridViewport(cellIndex);
            beakerxPO.testTableScreenshot(element,  imgDir, "codeCell7");
        }

        @Test(priority = 40, description = "should display beakerx table")
        public void recognize2DArrayOfIntegerArrays() throws IOException {
            cellIndex++;
            WebElement element = beakerxPO.runCellToGetDataGridViewport(cellIndex);
            beakerxPO.testTableScreenshot(element,  imgDir, "codeCell8");
        }

        @Test(priority = 45, description = "should display beakerx table")
        public void recognizeOfDifferentTypesOnColumns() throws IOException {
            cellIndex++;
            WebElement element = beakerxPO.runCellToGetDataGridViewport(cellIndex);
            beakerxPO.testTableScreenshot(element,  imgDir, "codeCell9");
        }

        @Test(priority = 50, description = "should display beakerx table")
        public void recognizeArrayOfDifferentTypes() throws IOException {
            cellIndex++;
            WebElement element = beakerxPO.runCellToGetDataGridViewport(cellIndex);
            beakerxPO.testTableScreenshot(element,  imgDir, "codeCell10");
        }

        @Test(priority = 55, description = "should display beakerx table")
        public void recognizeArraysOfDifferenceTypesOnRows() throws IOException {
            cellIndex++;
            WebElement element = beakerxPO.runCellToGetDataGridViewport(cellIndex);
            beakerxPO.testTableScreenshot(element,  imgDir, "codeCell11");
        }

        @Test(priority = 60, description = "should display beakerx table")
        public void recognizeDifferenceTypesForRows() throws IOException {
            cellIndex++;
            WebElement element = beakerxPO.runCellToGetDataGridViewport(cellIndex);
            beakerxPO.testTableScreenshot(element,  imgDir, "codeCell12");
        }

        @Test(priority = 65, description = "should display beakerx table")
        public void recognizeColumnNames() throws IOException {
            cellIndex++;
            WebElement element = beakerxPO.runCellToGetDataGridViewport(cellIndex);
            beakerxPO.testTableScreenshot(element,  imgDir, "codeCell13");
        }

        @Test(priority = 70, description = "should display beakerx table")
        public void setBeakerxPandasDisplayTableMode() throws IOException {
            cellIndex++;
            WebElement element = beakerxPO.runCellToGetDataGridViewport(cellIndex);
            beakerxPO.testTableScreenshot(element,  imgDir, "codeCell14");
        }

        @Test(priority = 75, description = "should display beakerx table")
        public void setNumberIndexToDataFrame() throws IOException {
            cellIndex++;
            WebElement element = beakerxPO.runCellToGetDataGridViewport(cellIndex);
            beakerxPO.testTableScreenshot(element,  imgDir, "codeCell15");
        }

        @Test(priority = 80, description = "should display beakerx table")
        public void setTimeIndexToDataFrame() throws IOException {
            cellIndex++;
            WebElement element = beakerxPO.runCellToGetDataGridViewport(cellIndex);
            beakerxPO.testTableScreenshot(element,  imgDir, "codeCell16");
        }

        @Test(priority = 85, description = "should display error message")
        public void setLengthOfTypesNotEqualsNumberOfColumns() throws IOException {
            cellIndex++;
            WebElement codeCell = beakerxPO.runCodeCellByIndex(cellIndex);
            beakerxPO.scrollIntoView(codeCell);
            String txt = beakerxPO.getAllOutputsOfCodeCell(codeCell, beakerxPO.getAllOutputsStderrSelector())
                    .get(0).getText();
            Assert.assertTrue(txt.contains("The length of types should be same as number of columns."));
        }

}
