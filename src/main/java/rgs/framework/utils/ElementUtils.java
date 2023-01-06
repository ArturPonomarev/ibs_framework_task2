package rgs.framework.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.WebDriverWait;
import rgs.framework.driver.DriverProvider;

import java.time.Duration;

public class ElementUtils {
    public static boolean isElementPresent(By locator)
    {
        boolean isPresented;
        DriverProvider.getInstance().getDriver().manage().timeouts().implicitlyWait(Duration.ZERO);
        try
        {
            new WebDriverWait(DriverProvider.getInstance().getDriver(), Duration.ofSeconds(JsonDataProvider.configData.elementWaitingTimeoutExplicit))
                    .pollingEvery(Duration.ofMillis(JsonDataProvider.configData.explicitPollingInterval))
                    .until(dr->dr.findElements(locator).size() > 0);
            isPresented = true;
        }
        catch (TimeoutException e) {
            isPresented = false;
        }
        DriverProvider.getInstance().getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(JsonDataProvider.configData.elementWaitingTimeoutImplicit));
        return isPresented;
    }
}
