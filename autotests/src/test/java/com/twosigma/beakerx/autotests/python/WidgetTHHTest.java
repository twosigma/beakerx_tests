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

    @Test(priority = 40, description = "Plot has two polygon elements")
    public void levelsOfDetails() {
        cellIndex++;
        WebElement svgElement = beakerxPO.runCellToGetSvgElement(cellIndex);
        Assert.assertTrue(svgElement.findElement(By.cssSelector("#i0 polygon")).isDisplayed());
        Assert.assertTrue(svgElement.findElement(By.cssSelector("#i1 polygon")).isDisplayed());
    }

    @Test(priority = 45, description = "Plot has Heatmap with legend")
    public void createHeatmap() {
        cellIndex++;
        beakerxPO.runCodeCellByIndex(cellIndex);
        cellIndex++;
        WebElement dtContainer = beakerxPO.runCellToGetDtContainer(cellIndex);
        Assert.assertEquals(dtContainer.findElements(By.cssSelector("g.heatmap")).size(), 1);
        Assert.assertEquals(dtContainer.findElements(By.cssSelector("#plotLegend")).size(), 1);
    }

    @Test(priority = 50, description = "Heatmap has Title and Axis Labels")
    public void setTitleAndAxisLabelsForHeatmap() {
        cellIndex++;
        WebElement dtContainer = beakerxPO.runCellToGetDtContainer(cellIndex);
        Assert.assertEquals(dtContainer.findElement(By.cssSelector("#plotTitle")).getText(),
                "Heatmap Second Example");
        Assert.assertEquals(dtContainer.findElement(By.cssSelector("#xlabel")).getText(),"X Label");
        Assert.assertEquals(dtContainer.findElement(By.cssSelector("#ylabel")).getText(),"Y Label");
    }

    @Test(priority = 55, description = "Set top position for legend")
    public void setTopPositionForHeatmapLegend() {
        WebElement dtContainer = beakerxPO.runCellToGetDtContainer(cellIndex);
        int y_svg = dtContainer.findElement(By.cssSelector("svg#svgg")).getLocation().getY();
        int y_legend = dtContainer.findElement(By.cssSelector("div#plotLegend")).getLocation().getY();
        Assert.assertTrue(y_svg > y_legend);
    }

    @Test(priority = 60, description = "Heatmap has gradient color")
    public  void setGradientColorToHeatmap(){
        cellIndex++;
        WebElement svgElement = beakerxPO.runCellToGetSvgElement(cellIndex);
        WebElement rect1 = svgElement.findElement(By.cssSelector("rect#i0_1"));
        WebElement rect550 = svgElement.findElement(By.cssSelector("rect#i0_550"));
        Assert.assertEquals(rect1.getCssValue("fill"), "rgb(50, 187, 0)");
        Assert.assertEquals(rect550.getCssValue("fill"), "rgb(238, 236, 2)");
    }

    @Test(priority = 65, description = "Heatmap has custom gradient color")
    public  void setCustomGradientColorToHeatmap(){
        cellIndex++;
        WebElement svgElement = beakerxPO.runCellToGetSvgElement(cellIndex);
        WebElement rect1 = svgElement.findElement(By.cssSelector("rect#i0_1"));
        WebElement rect35 = svgElement.findElement(By.cssSelector("rect#i0_35"));
        Assert.assertEquals(rect1.getCssValue("fill"), "rgb(179, 179, 0)");
        Assert.assertEquals(rect35.getCssValue("fill"), "rgb(255, 199, 0)");
    }

    @Test(priority = 70, description = "Heatmap has custom size 900x300")
    public  void setCustomSizeToHeatmap(){
        cellIndex++;
        WebElement svgElement = beakerxPO.runCellToGetSvgElement(cellIndex);
        Assert.assertEquals(svgElement.getCssValue("width"), "900px");
        Assert.assertEquals(svgElement.getCssValue("height"), "300px");
    }

}
