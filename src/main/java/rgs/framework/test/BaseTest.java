package rgs.framework.test;

import rgs.framework.driver.DriverProvider;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

public abstract class BaseTest {

    protected WebDriver driver = DriverProvider.getInstance().getDriver();

    @Before
    public void setUp(){
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }

    @After
    public void tearDown(){
        DriverProvider.getInstance().quitDriver();
    }
}
