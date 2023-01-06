package rgs.framework.elements;

import org.openqa.selenium.By;

public class TextBox extends BaseElement{

    private final String VALUE_ATTRIBUTE_NAME = "value";

    public TextBox(By locator) {
        super(locator);
    }

    public void sendKeys(String keys, boolean pressEnter)
    {
        element.sendKeys(keys);
        if (pressEnter)
            element.submit();
    }

    public String getValue()
    {
        return element.getAttribute(VALUE_ATTRIBUTE_NAME);
    }
}