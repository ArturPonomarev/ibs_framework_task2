package rgs.framework.elements;

import org.openqa.selenium.By;

public class Link extends BaseElement{
    public Link(By locator) {
        super(locator);
    }

    public void click()
    {
        element.click();
    }

    public String getText()
    {
        return element.getText();
    }
}
