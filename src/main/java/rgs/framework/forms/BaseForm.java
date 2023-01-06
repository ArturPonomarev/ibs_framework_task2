package rgs.framework.forms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import rgs.framework.driver.DriverProvider;
import rgs.framework.utils.JsonDataProvider;

import java.sql.Driver;
import java.time.Duration;

public abstract class BaseForm {

    protected By uniqueElementLocator;
    protected WebDriver driver = DriverProvider.getInstance().getDriver();

    public BaseForm(By uniqueElement)
    {
        this.uniqueElementLocator = uniqueElement;
    }

    public boolean isActive()
    {
        return new WebDriverWait(driver, Duration.ofSeconds(JsonDataProvider.configData.pageLoadTimeout))
                .until( dr -> dr.findElements(uniqueElementLocator).size() > 0);
    }
}