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

package com.twosigma.beakerx.autotests.clojure;

import com.twosigma.beakerx.autotests.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ClojureTest  extends BaseTest {

    int cellIndex = 0;

    @BeforeClass
    public static void setupClass() {
        BaseTest.setupClass();
        beakerxPO.runNotebookByUrl("/autotests/ipynb/clojure/ClojureTest.ipynb");
    }

    @Test(priority = 1, description = "Execute Results output contains 'beaker_clojure_shell'.")
    public void defineClojureFunction() {
        cellIndex = 0;
        WebElement codeCell = beakerxPO.runCodeCellByIndex(cellIndex);
        String txt = beakerxPO.getAllOutputsOfCodeCell(codeCell, beakerxPO.getAllOutputsExecuteResultsSelector())
                .get(0).getText();
        Assert.assertTrue(txt.contains("beaker_clojure_shell"));
    }

    @Test(priority = 5, description = "Execute Results output contains '0, 1, 1, 2, 3, 5, 8, 13, 21'.")
    public void callClojureFunction() {
        cellIndex++;
        WebElement codeCell = beakerxPO.runCodeCellByIndex(cellIndex);
        String txt = beakerxPO.getAllOutputsOfCodeCell(codeCell, beakerxPO.getAllOutputsExecuteResultsSelector())
                .get(0).getText();
        Assert.assertTrue(txt.contains("0, 1, 1, 2, 3, 5, 8, 13, 21"));
    }

    @Test(priority = 10, description = "Stdout output contains 'Will print'.")
    public void defineAndCallClojureFunction() {
        cellIndex++;
        WebElement codeCell = beakerxPO.runCodeCellByIndex(cellIndex);
        String txt = beakerxPO.getAllOutputsOfCodeCell(codeCell, beakerxPO.getAllOutputsStdoutSelector())
                .get(0).getText();
        Assert.assertTrue(txt.contains("Will print"));
    }
}
