package rgs.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import rgs.framework.driver.DriverProvider;
import org.openqa.selenium.WebDriver;

public abstract class BaseTest {

    protected WebDriver driver = DriverProvider.getInstance().getDriver();

    @BeforeEach
    public void setUp() {
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }

    @AfterEach
    public void tearDown() {
        DriverProvider.getInstance().quitDriver();
    }
}
