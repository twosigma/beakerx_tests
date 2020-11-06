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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class WidgetPlotTest extends BaseTest {

    int cellIndex = 0;

    @BeforeClass
    public static void setupClass() {
        BaseTest.setupClass();
        beakerxPO.runNotebookByUrl("/autotests/ipynb/python/WidgetPlotTest.ipynb");
    }

    @Test(priority = 1, description = "Plot has Title and Axis Labels")
    public void createPlotWithTitleAndAxisLabels() {
        cellIndex = 0;
        beakerxPO.runCodeCellByIndex(cellIndex);
        beakerxPO.pause(10000);
        WebElement dtContainer = beakerxPO.runCellToGetDtContainer(cellIndex);
        Assert.assertEquals(dtContainer.findElement(By.cssSelector("#plotTitle")).getText(),
                "We Will Control the Title");
        Assert.assertEquals(dtContainer.findElement(By.cssSelector("#xlabel")).getText(),"Horizontal");
        Assert.assertEquals(dtContainer.findElement(By.cssSelector("#ylabel")).getText(),"Vertical");

    }

    @Test(priority = 5, description = "should display Line Plot")
    public void createPlotWithLine() {
        cellIndex++;
        WebElement svgElement = beakerxPO.runCellToGetSvgElement(cellIndex);
        Assert.assertEquals(svgElement.findElements(By.cssSelector("path.plot-line")).size(), 1);
        Assert.assertEquals(svgElement.findElements(By.cssSelector("circle.plot-respdot")).size(), 6);
    }

    @Test(priority = 10, description = "should set width, color, style of line")
    public void setLineProperties() {
        cellIndex++;
        WebElement svgElement = beakerxPO.runCellToGetSvgElement(cellIndex);
        Assert.assertEquals(svgElement.findElements(By.cssSelector("path.plot-line")).size(), 6);

        WebElement line0 = svgElement.findElement(By.cssSelector("g#i0 > path"));
        Assert.assertEquals(line0.getCssValue("stroke"), "rgb(255, 0, 0)");
        Assert.assertEquals(line0.getCssValue("stroke-width"), "10px");

        WebElement line2 = svgElement.findElement(By.cssSelector("g#i2 > path"));
        Assert.assertEquals(line2.getCssValue("stroke-dasharray"), "9px, 5px");
        Assert.assertEquals(line2.getCssValue("stroke-opacity"), "1");
    }

    @Test(priority = 15, description = "should display Stem Plot")
    public void createPlotWithStem() {
        cellIndex++;
        WebElement svgElement = beakerxPO.runCellToGetSvgElement(cellIndex);
        Assert.assertEquals(svgElement.findElements(By.cssSelector("g.plot-stem")).size(), 1);
        Assert.assertEquals(svgElement.findElements(By.cssSelector("g#i0 > line")).size(), 12);
    }

    @Test(priority = 20, description = "should set the base to Stem")
    public void setBaseToStem() {
        cellIndex++;
        WebElement svgElement = beakerxPO.runCellToGetSvgElement(cellIndex);
        Assert.assertEquals(svgElement.findElements(By.cssSelector("g.plot-stem")).size(), 1);
        List<WebElement> lines = svgElement.findElements(By.cssSelector("g#i0 > line"));
        float line0Y = Float.parseFloat(lines.get(0).getAttribute("y2"));
        float line1Y = Float.parseFloat(lines.get(1).getAttribute("y2"));
        Assert.assertTrue(line0Y > line1Y);
    }

    @Test(priority = 25, description = "should display Bar Plot")
    public void createPlotWithBars() {
        cellIndex++;
        WebElement svgElement = beakerxPO.runCellToGetSvgElement(cellIndex);
        Assert.assertEquals(svgElement.findElements(By.cssSelector("g.plot-bar")).size(), 1);
        Assert.assertEquals(svgElement.findElements(By.cssSelector("g#i0 > rect")).size(), 5);
    }

    @Test(priority = 30, description = "should display Point Plot")
    public void createPlotWithPoints() {
        cellIndex++;
        WebElement svgElement = beakerxPO.runCellToGetSvgElement(cellIndex);
        Assert.assertEquals(svgElement.findElements(By.cssSelector("g.plot-point")).size(), 4);
        Assert.assertEquals(svgElement.findElements(By.cssSelector("g#i0 rect")).size(), 6);
        Assert.assertEquals(svgElement.findElements(By.cssSelector("g#i1 circle")).size(), 6);
    }

    @Test(priority = 35, description = "should set colors and size of Points")
    public void setPointProperties() {
        cellIndex++;
        WebElement svgElement = beakerxPO.runCellToGetSvgElement(cellIndex);
        Assert.assertEquals(svgElement.findElements(By.cssSelector("g.plot-point")).size(), 4);

        WebElement rect_i0_0 = svgElement.findElement(By.cssSelector("g#i0 rect#i0_0"));
        Assert.assertEquals(rect_i0_0.getAttribute("width"), "12");
        Assert.assertEquals(rect_i0_0.getAttribute("height"), "12");
        Assert.assertEquals(rect_i0_0.getCssValue("fill"), "rgb(0, 0, 0)");

        WebElement rect_i1_0 = svgElement.findElement(By.cssSelector("g#i1 rect#i1_0"));
        Assert.assertEquals(rect_i1_0.getAttribute("width"), "12");
        Assert.assertEquals(rect_i1_0.getAttribute("height"), "12");
        Assert.assertEquals(rect_i1_0.getCssValue("fill"), "rgb(128, 128, 128)");
    }

    @Test(priority = 40, description = "should display Area Plot")
    public void createPlotWithAreas() {
        cellIndex++;
        WebElement svgElement = beakerxPO.runCellToGetSvgElement(cellIndex);
        Assert.assertEquals(svgElement.findElements(By.cssSelector("polygon.plot-area")).size(), 2);
    }

    @Test(priority = 45, description = "should set the base to Area")
    public void setBaseToArea() {
        cellIndex++;
        WebElement svgElement = beakerxPO.runCellToGetSvgElement(cellIndex);
        List<WebElement> rects = svgElement.findElements(By.cssSelector("g#i1 > rect"));
        float rect0Y = Float.parseFloat(rects.get(0).getAttribute("y")) +
                Float.parseFloat(rects.get(0).getAttribute("height"));
        float rect1Y = Float.parseFloat(rects.get(1).getAttribute("y")) +
                Float.parseFloat(rects.get(1).getAttribute("height"));
        Assert.assertTrue(rect0Y > rect1Y);
    }

    @Test(priority = 50, description = "should display XYStacker")
    public void createXYStackerElement() {
        cellIndex++;
        WebElement svgElement = beakerxPO.runCellToGetSvgElement(cellIndex);
        Assert.assertEquals(svgElement.findElements(By.cssSelector("polygon.plot-area")).size(), 2);
        List<WebElement> rects0 = svgElement.findElements(By.cssSelector("g#i0 > rect"));
        float rect0Y = Float.parseFloat(rects0.get(0).getAttribute("y")) +
                Float.parseFloat(rects0.get(0).getAttribute("height"));
        List<WebElement> rects1 = svgElement.findElements(By.cssSelector("g#i1 > rect"));
        float rect1Y = Float.parseFloat(rects1.get(0).getAttribute("y")) +
                Float.parseFloat(rects1.get(0).getAttribute("height"));
        Assert.assertTrue(rect0Y > rect1Y);
    }

    @Test(priority = 55, description = "should display Constant Lines Plot")
    public void createPlotWithConstantLines() {
        cellIndex++;
        WebElement svgElement = beakerxPO.runCellToGetSvgElement(cellIndex);
        Assert.assertEquals(svgElement.findElements(By.cssSelector("g.plot-constline")).size(), 3);
    }

    @Test(priority = 60, description = "should display Constant Bands Plot")
    public void createPlotWithConstantBands() {
        cellIndex++;
        WebElement svgElement = beakerxPO.runCellToGetSvgElement(cellIndex);
        Assert.assertEquals(svgElement.findElements(By.cssSelector("g.plot-constband")).size(), 1);
    }

    @Test(priority = 65, description = "should set Constant Band Color")
    public void setConstantBandColor() {
        cellIndex++;
        WebElement svgElement = beakerxPO.runCellToGetSvgElement(cellIndex);
        Assert.assertEquals(svgElement.findElements(By.cssSelector("g.plot-constband")).size(), 3);
        WebElement band1 = svgElement.findElements(By.cssSelector("g.plot-constband")).get(0);
        Assert.assertEquals(band1.getCssValue("fill"), "rgb(128, 128, 128)");
    }

    @Test(priority = 70, description = "should display Text elements")
    public void createPlotWithTextElements() {
        cellIndex++;
        WebElement svgElement = beakerxPO.runCellToGetSvgElement(cellIndex);
        Assert.assertEquals(svgElement.findElements(By.cssSelector("g.plot-text")).size(), 8);
    }

    @Test(priority = 75, description = "should display Crosshair element")
    public void createPlotWithCrosshair() {
        cellIndex++;
        WebElement svgElement = beakerxPO.runCellToGetSvgElement(cellIndex);
        beakerxPO.scrollIntoView(svgElement);
        beakerxPO.action.moveToElement(svgElement, 20, 20)
                .pause(1000).moveToElement(svgElement, 50, 50).build().perform();
        WebElement dtContainer = beakerxPO.getDtContainerByIndex(cellIndex);
        Assert.assertTrue(dtContainer.findElement(By.cssSelector("div#cursor_xlabel")).isDisplayed());
        Assert.assertTrue(dtContainer.findElement(By.cssSelector("div#cursor_ylabel")).isDisplayed());
    }

    @Test(priority = 80, description = "TimePlot has 2 lines and time axis")
    public void createSimpleTimePlot() {
        cellIndex += 2;
        beakerxPO.runCodeCellByIndex(cellIndex);
        cellIndex++;
        WebElement svgElement = beakerxPO.runCellToGetSvgElement(cellIndex);
        beakerxPO.scrollIntoView(svgElement);

        Assert.assertTrue(svgElement.findElements(By.cssSelector("g#i0 > circle")).size() > 0);
        Assert.assertTrue(svgElement.findElements(By.cssSelector("g#i1 > circle")).size() > 0);
        Assert.assertTrue(svgElement.findElement(By.cssSelector("g#i0 > path.plot-line")).isDisplayed());
        Assert.assertTrue(svgElement.findElement(By.cssSelector("g#i1 > path.plot-line")).isDisplayed());
        Assert.assertEquals(svgElement.findElement(By.cssSelector("text#label_x_0")).getText(), "1990");
    }

    @Test(priority = 85, description = "TimePlot has millisecond resolution")
    public void createMillisecondResolution() {
        cellIndex++;
        WebElement svgElement = beakerxPO.runCellToGetSvgElement(cellIndex);
        beakerxPO.scrollIntoView(svgElement);

        WebElement rect0 = svgElement.findElement(By.cssSelector("rect#i0_0"));
        beakerxPO.action.moveToElement(rect0).click().build().perform();
        WebElement tipElement =
                beakerxPO.getPlotToolTip(beakerxPO.getDtContainerByIndex(cellIndex), "div#tip_i0_0");
        Assert.assertTrue(tipElement.getText().contains("x: 2017 Oct 09 Mon, 05:26:41 .624"));
        Assert.assertTrue(tipElement.getText().contains("y: 0"));
    }

    @Test(priority = 90, description = "TimePlot has nanosecond resolution")
    public void createNanosecondResolution() {
        cellIndex++;
        WebElement svgElement = beakerxPO.runCellToGetSvgElement(cellIndex);
        beakerxPO.scrollIntoView(svgElement);

        WebElement rect1 = svgElement.findElement(By.cssSelector("rect#i0_1"));
        beakerxPO.action.moveToElement(rect1).click().build().perform();
        WebElement tipElement =
                beakerxPO.getPlotToolTip(beakerxPO.getDtContainerByIndex(cellIndex), "div#tip_i0_1");
        Assert.assertEquals(tipElement.getText(), "x: 2017 Oct 09 Mon, 02:26:41.624000007");
        Assert.assertTrue(tipElement.getText().contains("y: 1"));
    }

    @Test(priority = 95, description = "Plot has second Y Axis")
    public void createSecondYAxis() {
        cellIndex++;
        WebElement svgElement = beakerxPO.runCellToGetSvgElement(cellIndex);
        beakerxPO.scrollIntoView(svgElement);

        Assert.assertEquals(svgElement.findElement(By.cssSelector("text#yrlabel")).getText(), "Test y axis");
        Assert.assertEquals(svgElement.findElement(By.cssSelector("text#label_yr_2")).getText(), "30");
        Assert.assertTrue(svgElement.findElement(By.cssSelector("line#tick_yr_2")).isEnabled());
    }

    @Test(priority = 100, description = "Combined plot has 3 plots")
    public void createLogarithmicScale() {
        cellIndex++;
        WebElement codeCell = beakerxPO.runCodeCellByIndex(cellIndex);
        WebElement combPlot = codeCell.findElement(By.cssSelector("div.combplot-plotcontainer"));
        beakerxPO.scrollIntoView(combPlot);

        Assert.assertTrue(combPlot.isDisplayed());
        Assert.assertEquals(combPlot.findElements(By.cssSelector("div.dtcontainer")).size(), 3);
    }

    @Test(priority = 105, description = "Second plot has Log Y axis")
    public void logarithmicYAxis() {
        WebElement codeCell = beakerxPO.getCodeCellByIndex(cellIndex);
        WebElement combPlot = codeCell.findElement(By.cssSelector("div.combplot-plotcontainer"));

        WebElement g0 = combPlot.findElements(By.cssSelector("div.dtcontainer")).get(0);
        WebElement g1 = combPlot.findElements(By.cssSelector("div.dtcontainer")).get(1);
        Assert.assertEquals(getCircleAttribute(g0, "i0_0", "cy"),
                getCircleAttribute(g1, "i0_0", "cy"));
        Assert.assertTrue(Double.parseDouble(getCircleAttribute(g0, "i0_50", "cy")) >
                Double.parseDouble(getCircleAttribute(g1, "i0_50", "cy")));
    }

    @Test(priority = 110, description = "Second plot has Log X axis")
    public void logarithmicXAxis() {
        WebElement codeCell = beakerxPO.getCodeCellByIndex(cellIndex);
        WebElement combPlot = codeCell.findElement(By.cssSelector("div.combplot-plotcontainer"));

        WebElement g0 = combPlot.findElements(By.cssSelector("div.dtcontainer")).get(0);
        WebElement g2 = combPlot.findElements(By.cssSelector("div.dtcontainer")).get(2);
        Assert.assertEquals(getCircleAttribute(g0, "i0_0", "cx"),
                getCircleAttribute(g2, "i0_0", "cx"));
        Assert.assertTrue(Double.parseDouble(getCircleAttribute(g0, "i0_50", "cx")) <
                Double.parseDouble(getCircleAttribute(g2, "i0_50", "cx")));
    }

    private String getCircleAttribute(WebElement webElement, String index, String attr){
        return webElement.findElement(By.cssSelector("circle#" + index)).getAttribute(attr);
    }

}
