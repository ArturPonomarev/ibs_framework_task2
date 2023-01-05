package ibs.framework.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverSingle {
    private static DriverSingle driverSingle;
    private WebDriver driver;

    private DriverSingle() {
    }

    public static DriverSingle getInstance() {
        if (driverSingle == null) {
            driverSingle = new DriverSingle();
        }
        return driverSingle;
    }

    public WebDriver getDriver() {
        //TODO: вынести в фабрику через переменную
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
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
