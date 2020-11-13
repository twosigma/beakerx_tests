package com.twosigma.beakerx.autotests.groovy;

import com.twosigma.beakerx.autotests.BaseTest;
import com.twosigma.beakerx.autotests.BaseWidgetPlotTest;
import org.testng.annotations.BeforeClass;

public class WidgetPlotTest extends BaseWidgetPlotTest {

    public int cellIndex = 0;

    @BeforeClass
    public static void setupClass() {
        BaseTest.setupClass();
        beakerxPO.runNotebookByUrl("/autotests/ipynb/groovy/WidgetPlotTest.ipynb");
    }
    
}
