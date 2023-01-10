package rgs.framework.forms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import rgs.framework.driver.DriverProvider;
import rgs.framework.utils.ElementUtils;

public abstract class BaseForm {

    protected By uniqueElementLocator;
    protected WebDriver driver = DriverProvider.getInstance().getDriver();

    public BaseForm(By uniqueElement) {
        this.uniqueElementLocator = uniqueElement;
    }

    public boolean isActive() {
        return ElementUtils.isElementPresent(uniqueElementLocator);
    }
}