/*
 *  Copyright 2020 TWO SIGMA OPEN SOURCE, LLC
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.twosigma.beakerx.autotests.python;

import com.twosigma.beakerx.autotests.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class WidgetTHHTest extends BaseTest {

    int cellIndex = 0;

    @BeforeClass
    public static void setupClass() {
        BaseTest.setupClass();
        beakerxPO.runNotebookByUrl("/autotests/ipynb/python/WidgetTHHTest.ipynb");
    }

    @Test(priority = 1, description = "should display Histogram")
    public void createHistogram() {
        cellIndex = 0;
        beakerxPO.runCodeCellByIndex(cellIndex++);
        WebElement svgElement = beakerxPO.runCellToGetSvgElement(cellIndex);
        Assert.assertEquals(svgElement.findElements(By.cssSelector("g.plot-bar")).size(), 1);
        Assert.assertTrue(svgElement.findElements(By.cssSelector("g#i0 > rect")).size() > 20);
    }

    @Test(priority = 5, description = "Histogram has Title and Axis Labels")
    public void setTitleAndAxisLabelsForHistogram() {
        cellIndex++;
        WebElement dtContainer = beakerxPO.runCellToGetDtContainer(cellIndex);
        Assert.assertEquals(dtContainer.findElement(By.cssSelector("#plotTitle")).getText(),
                "Wide Histogram with Manual Parameters");
        Assert.assertEquals(dtContainer.findElement(By.cssSelector("#xlabel")).getText(),"Size");
        Assert.assertEquals(dtContainer.findElement(By.cssSelector("#ylabel")).getText(),"Count");

    }

    @Test(priority = 10, description = "Plot has two histograms (Overlap)")
    public void addTwoHistogramsToPlot_Overlap() {
        cellIndex++;
        WebElement svgElement = beakerxPO.runCellToGetSvgElement(cellIndex);
        WebElement plotBar0 = svgElement.findElement(By.cssSelector("g#i0.plot-bar"));
        WebElement plotBar1 = svgElement.findElement(By.cssSelector("g#i1.plot-bar"));
        Assert.assertTrue(plotBar0.isDisplayed());
        Assert.assertTrue(plotBar1.isDisplayed());
        Assert.assertEquals(plotBar0.getCssValue("fill"), "rgb(0, 154, 166)");
        Assert.assertEquals(plotBar1.getCssValue("fill"), "rgb(230, 50, 50)");
        Assert.assertEquals(plotBar0.findElement(By.cssSelector("rect#i0_15")).getAttribute("x"),
                plotBar1.findElement(By.cssSelector("rect#i1_15")).getAttribute("x"));
        double y0_15 = Double.parseDouble(plotBar0.findElement(By.cssSelector("rect#i0_15")).getAttribute("y"));
        double y1_15 = Double.parseDouble(plotBar1.findElement(By.cssSelector("rect#i1_15")).getAttribute("y"));
        Assert.assertTrue( y0_15 > y1_15);

    }

    @Test(priority = 15, description = "Plot has two histograms (Stack)")
    public void addTwoHistogramsToPlot_Stack() {
        cellIndex++;
        WebElement svgElement = beakerxPO.runCellToGetSvgElement(cellIndex);
        WebElement plotBar0 = svgElement.findElement(By.cssSelector("g#i0.plot-bar"));
        WebElement plotBar1 = svgElement.findElement(By.cssSelector("g#i1.plot-bar"));
        Assert.assertTrue(plotBar0.isDisplayed());
        Assert.assertTrue(plotBar1.isDisplayed());
        Assert.assertEquals(plotBar0.findElement(By.cssSelector("rect#i0_15")).getAttribute("x"),
                plotBar1.findElement(By.cssSelector("rect#i1_15")).getAttribute("x"));
        double y0_15 = Double.parseDouble(plotBar0.findElement(By.cssSelector("rect#i0_15")).getAttribute("y"));
        double y1_15 = Double.parseDouble(plotBar1.findElement(By.cssSelector("rect#i1_15")).getAttribute("y"));
        Assert.assertTrue( y0_15 < y1_15);
    }

    @Test(priority = 20, description = "Plot has two histograms (Side by side)")
    public void addTwoHistogramsToPlot_SideBySide() {
        cellIndex++;
        WebElement svgElement = beakerxPO.runCellToGetSvgElement(cellIndex);
        WebElement plotBar0 = svgElement.findElement(By.cssSelector("g#i0.plot-bar"));
        WebElement plotBar1 = svgElement.findElement(By.cssSelector("g#i1.plot-bar"));
        Assert.assertTrue(plotBar0.isDisplayed());
        Assert.assertTrue(plotBar1.isDisplayed());
        Assert.assertNotEquals(plotBar0.findElement(By.cssSelector("rect#i0_15")).getAttribute("x"),
                plotBar1.findElement(By.cssSelector("rect#i1_15")).getAttribute("x"));
        double y0_15 = Double.parseDouble(plotBar0.findElement(By.cssSelector("rect#i0_15")).getAttribute("y"));
        double y1_15 = Double.parseDouble(plotBar1.findElement(By.cssSelector("rect#i1_15")).getAttribute("y"));
        Assert.assertTrue( y0_15 > y1_15);
    }

    @Test(priority = 25, description = "Plot has histogram (Cumulative)")
    public void addHistogramToPlot_Cumulative() {
        cellIndex++;
        WebElement svgElement = beakerxPO.runCellToGetSvgElement(cellIndex);
        WebElement plotBar0 = svgElement.findElement(By.cssSelector("g#i0.plot-bar"));
        Assert.assertTrue(plotBar0.isDisplayed());
        Assert.assertTrue(plotBar0.findElements(By.cssSelector("rect")).size() > 40);
        double y0_10 = Double.parseDouble(plotBar0.findElement(By.cssSelector("rect#i0_10")).getAttribute("y"));
        double y0_25 = Double.parseDouble(plotBar0.findElement(By.cssSelector("rect#i0_25")).getAttribute("y"));
        double y0_40 = Double.parseDouble(plotBar0.findElement(By.cssSelector("rect#i0_40")).getAttribute("y"));
        Assert.assertTrue( y0_10 > y0_25);
        Assert.assertTrue( y0_25 > y0_40);
    }

    @Test(priority = 30, description = "Plot has histogram (Normed)")
    public void addHistogramToPlot_Normed() {
        cellIndex++;
        WebElement svgElement = beakerxPO.runCellToGetSvgElement(cellIndex);
        WebElement plotBar0 = svgElement.findElement(By.cssSelector("g#i0.plot-bar"));
        Assert.assertTrue(plotBar0.isDisplayed());
        Assert.assertTrue(plotBar0.findElements(By.cssSelector("rect")).size() > 40);
        double y0_10 = Double.parseDouble(plotBar0.findElement(By.cssSelector("rect#i0_10")).getAttribute("y"));
        double y0_25 = Double.parseDouble(plotBar0.findElement(By.cssSelector("rect#i0_25")).getAttribute("y"));
        double y0_40 = Double.parseDouble(plotBar0.findElement(By.cssSelector("rect#i0_40")).getAttribute("y"));
        Assert.assertTrue( y0_10 > y0_25);
        Assert.assertTrue( y0_25 < y0_40);
    }

    @Test(priority = 35, description = "Plot has histogram (Log)")
    public void addHistogramToPlot_Log() {
        cellIndex++;
        WebElement svgElement = beakerxPO.runCellToGetSvgElement(cellIndex);
        WebElement plotBar0 = svgElement.findElement(By.cssSelector("g#i0.plot-bar"));
        Assert.assertTrue(plotBar0.isDisplayed());
        Assert.assertTrue(plotBar0.findElements(By.cssSelector("rect")).size() > 40);
        double y0_10 = Double.parseDouble(plotBar0.findElement(By.cssSelector("rect#i0_10")).getAttribute("y"));
        double y0_25 = Double.parseDouble(plotBar0.findElement(By.cssSelector("rect#i0_25")).getAttribute("y"));
        double y0_40 = Double.parseDouble(plotBar0.findElement(By.cssSelector("rect#i0_40")).getAttribute("y"));
        Assert.assertTrue( y0_10 > y0_25);
        Assert.assertTrue( y0_25 < y0_40);
    }

}
