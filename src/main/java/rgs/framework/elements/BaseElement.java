package rgs.framework.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import rgs.framework.driver.DriverProvider;

public abstract class BaseElement {

    protected WebElement element;

    public BaseElement(By locator) {
        element = DriverProvider.getInstance().getDriver().findElement(locator);
    }

    public boolean isVisible() {
        return element.isDisplayed();
    }

    public WebElement getElement() {
        return element;
    }
}