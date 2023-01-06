package rgs.framework.utils;

import rgs.framework.driver.DriverProvider;

public class BrowserUtils {
    public static void switchToFrame(String frameId) {
        DriverProvider.getInstance().getDriver().switchTo().frame(frameId);
    }

    public static void leaveAllFrames() {
        DriverProvider.getInstance().getDriver().switchTo().defaultContent();
    }
}
