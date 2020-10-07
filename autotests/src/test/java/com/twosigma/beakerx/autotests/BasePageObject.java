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

package com.twosigma.beakerx.autotests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;

import javax.imageio.ImageIO;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public abstract class BasePageObject {

    public static String userDir = System.getProperties().getProperty("user.dir");
    public static String fileSeparator = System.getProperties().getProperty("file.separator");
    public static String imgDir = userDir + fileSeparator + "resources" + fileSeparator + "img";
    public static String imgFormat = "png";
    public boolean createExpectedData = false;
    public String tableVersion = "v0";

    protected final WebDriver webDriver;
    public Actions action;

    protected BasePageObject(WebDriver webDriver) {
        this.webDriver = webDriver;
        action = new Actions(webDriver);
    }

    public abstract void runNotebookByUrl(String url);
    public abstract WebElement runCodeCellByIndex(int index);
    public abstract WebElement getCodeCellByIndex (int index);
    public abstract void waitKernelIdleIcon(int timeOutInSeconds);
    public abstract void closeAndHaltNotebook();
    public abstract By getAllOutputsExecuteResultsSelector();
    public abstract By getAllOutputsStdoutSelector();
    public abstract By getAllOutputsStderrSelector();
    public abstract void setTableVersion(String prefix);

    public void setCreateExpectedData(boolean createExpectedData) {
        this.createExpectedData = createExpectedData;
    }

    public void doubleClick(By locator){
        WebElement element = webDriver.findElement(locator);
        action.moveToElement(element).doubleClick().pause(500).perform();
    }

    public void pause(int mseconds){
        action.pause(mseconds).perform();
    }

    public Screenshot createScreenshot(WebElement element, String dirName, String fileName) throws IOException {
        String pathName = getPathNameForImage(dirName, fileName);
        Screenshot actualScreenshot =
                new AShot().coordsProvider(new WebDriverCoordsProvider()).takeScreenshot(webDriver, element);
        ImageIO.write(actualScreenshot.getImage(), imgFormat, new File(pathName));
        return actualScreenshot;
    }

    public Screenshot createActualScreenshot(WebElement element, String dirName, String fileName) throws IOException {
        return createScreenshot(element, dirName, fileName + "Act");
    }

    public Screenshot createExpectedScreenshot(WebElement element, String dirName, String fileName) throws IOException {
        return createScreenshot(element, dirName, fileName + "Exp");
    }

    public int checkScreenshot(WebElement element, String dirName, String fileName) throws IOException {
        Screenshot actualScreenshot = createActualScreenshot(element, dirName, fileName);
        String expectedPathName = getPathNameForImage(dirName, fileName + "Exp");
        Screenshot expectedScreenshot = new Screenshot(ImageIO.read(new File(expectedPathName)));
        ImageDiff diff = new ImageDiffer().makeDiff(expectedScreenshot, actualScreenshot);
        if(diff.getDiffSize() > 0) {
            File diffFile = new File(getPathNameForImage(dirName, fileName + "Dif"));
            ImageIO.write(diff.getMarkedImage(), "png", diffFile);
        }
        return diff.getDiffSize();
    }

    private String getPathNameForImage(String dirName, String fileName){
        return imgDir + fileSeparator + dirName + fileSeparator + tableVersion + fileName  + "." + imgFormat;
    }

    public List<WebElement> getAllOutputsOfCodeCell(WebElement codeCell, By selector){
        FluentWait<WebElement> wait = new FluentWait<WebElement>(codeCell).withTimeout(20, TimeUnit.SECONDS);
        wait.until(new Function<WebElement, Boolean>() {
            public Boolean apply(WebElement codeCell) {
                return codeCell.findElements(selector).size() > 0;
            }
        });
        return codeCell.findElements(selector);
    }

    public WebElement runCellToGetDtContainer(int index){
        WebElement codeCell = runCodeCellByIndex(index);
        return codeCell.findElement(By.cssSelector("div.dtcontainer"));
    }

    public WebElement runCellToGetSvgElement(int index){
        WebElement codeCell = runCodeCellByIndex(index);
        return codeCell.findElement(By.cssSelector("#svgg"));
    }

    public WebElement runCellToGetDataGridViewport(int cellIndex){
        WebElement codeCell = runCodeCellByIndex(cellIndex);
        scrollIntoView(codeCell);
        pause(1000);
        WebElement element = codeCell.findElement(By.cssSelector("div.p-DataGrid-viewport"));
        return element;
    }

    public WebElement debug_runCellToGetDataGridViewport(int cellIndex, String dir, String name) throws IOException {
        WebElement codeCell = runCodeCellByIndex(cellIndex);
        scrollIntoView(codeCell);
        createActualScreenshot(codeCell, dir, name);
        WebElement element;
        try{
            element =  codeCell.findElement(By.cssSelector("div.p-DataGrid-viewport"));
        } catch (org.openqa.selenium.NoSuchElementException exception){
            exception.printStackTrace();
            createActualScreenshot(codeCell, dir, name + "error");
            codeCell = runCodeCellByIndex(cellIndex);
            element = codeCell.findElement(By.cssSelector("div.p-DataGrid-viewport"));
        }
        return element;
    }

    public void scrollIntoView(WebElement element){
        ((JavascriptExecutor)webDriver).executeScript("arguments[0].scrollIntoView();",element);
    }

    public WebElement getDataGridTooltip(WebElement element){
        FluentWait<WebElement> wait = new FluentWait<WebElement>(element).withTimeout(20, TimeUnit.SECONDS);
        wait.until(new Function<WebElement, Boolean>() {
            public Boolean apply(WebElement element) {
                return webDriver.findElements(By.cssSelector("div.p-DataGrid-tooltip")).size() > 0;
            }
        });
        return webDriver.findElement(By.cssSelector("div.p-DataGrid-tooltip"));
    }

    public Actions getAction() {
        return action;
    }


    public void testTableScreenshot(WebElement element, String ImageDir, String fileName) throws IOException {
        if(createExpectedData) {
            createExpectedScreenshot(element, ImageDir, fileName);
        }
        else {
            int diff = checkScreenshot(element, ImageDir, fileName);
            Assert.assertEquals(diff, 0);
        }
    }
    public void testTextTableValue(String actualValue, String ImageDir, String fileName){
        if(createExpectedData) {
            createExpectedTextValue(actualValue, ImageDir, fileName);
        }
        else {
            String expectedValue = readExpectedTextValue(ImageDir, fileName);
            Assert.assertEquals(actualValue, expectedValue);
        }
    }

    public void createExpectedTextValue(String actualValue, String dirName, String fileName){
        String filePath = imgDir + fileSeparator + dirName + fileSeparator + tableVersion + fileName  + ".txt";
        Path path = Paths.get(filePath);
        byte[] strToBytes = actualValue.getBytes();
        try {
            Files.write(path, strToBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readExpectedTextValue(String dirName, String fileName){
        String filePath = imgDir + fileSeparator + dirName + fileSeparator + tableVersion + fileName  + ".txt";
        String result = "";
        Path path = Paths.get(filePath);
        try {
            result = Files.readAllLines(path).get(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
