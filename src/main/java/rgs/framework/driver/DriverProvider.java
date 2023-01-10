package rgs.framework.driver;

import rgs.framework.utils.JsonDataProvider;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class DriverProvider {
    private static DriverProvider driverProvider;
    private WebDriver driver;

    private DriverProvider() {
    }

    public static DriverProvider getInstance() {
        if (driverProvider == null) {
            driverProvider = new DriverProvider();
        }
        return driverProvider;
    }

    public WebDriver getDriver() {
        if (driver == null) {
            driver = DriverFactory.createDriver();
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(JsonDataProvider.configData.pageLoadTimeout));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(JsonDataProvider.configData.elementWaitingTimeoutImplicit));
        }
        return driver;
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }

    }
}
