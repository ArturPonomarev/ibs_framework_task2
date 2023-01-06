package rgs.framework.forms;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import rgs.framework.elements.Button;
import rgs.framework.utils.ElementUtils;
import rgs.framework.utils.JsonDataProvider;

import java.time.Duration;

public class CompaniesPage extends BaseForm {

    private final By healthListButton = By.xpath("//*[contains(@class,'list')]//*[contains(text(),'Здоровье')]");
    private final By healthInsuranceButton = By.xpath("//*[contains(@class,'header-list-products')]//*[contains(text(),'Добровольное')]");

    private static final By UniqueElementLocator = By.xpath("//*[@id = 'tender']");

    public CompaniesPage() {
        super(UniqueElementLocator);
    }

    public void ClickHealthListButton()
    {
        new Button(healthListButton).click();
    }

    public boolean IsHealthInsuranceListOpened()
    {
        return ElementUtils.isElementPresent(healthInsuranceButton);
    }

    public void ClickHealthInsuranceButton()
    {
        new Button(healthInsuranceButton).click();
    }
}