package com.twosigma.beakerx.autotests;

import org.openqa.selenium.WebDriver;

public class BeakerxPOFactory {

    public static BasePageObject create(WebDriver driver){
        BasePageObject beakerxPO;
        if("lab".equalsIgnoreCase(System.getProperty("cur_app"))){
            System.out.println("current app is jupyter lab");
            beakerxPO = new LabPO(driver);
        }
        else {
            System.out.println("current app is jupyter notebook");
            beakerxPO = new NotebookPO(driver);
        }
        beakerxPO.setTableVersion(System.getProperty("tbl_ver"));
        beakerxPO.setCreateExpectedData("yes".equalsIgnoreCase(System.getProperty("create_exp_data")));
        return beakerxPO;
    }
}
