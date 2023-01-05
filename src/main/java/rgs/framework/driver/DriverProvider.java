package rgs.framework.driver;

import rgs.framework.utils.JsonDataProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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
        //TODO: вынести в фабрику через переменную
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
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
