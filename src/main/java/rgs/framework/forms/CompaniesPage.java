package rgs.framework.forms;

import org.openqa.selenium.By;
import rgs.framework.elements.Button;
import rgs.framework.utils.ElementUtils;


public class CompaniesPage extends BaseForm {

    private final String healthInsuranceButtonPath = "//*[contains(@class,'header-list-products')]//*[contains(text(),'%s')]";
    private final By healthListButton = By.xpath("//*[contains(@class,'list')]//*[contains(text(),'Здоровье')]");
    private final By healthInsuranceList = By.xpath("//*[contains(@class,'row-container')]");

    private static final By UniqueElementLocator = By.xpath("//*[@id = 'tender']");

    public CompaniesPage() {
        super(UniqueElementLocator);
    }

    public void ClickHealthListButton() {
        new Button(healthListButton).click();
    }

    public boolean IsHealthInsuranceListOpened() {
        return ElementUtils.isElementPresent(healthInsuranceList);
    }

    public void ClickHealthInsuranceButton(String healthInsuranceButtonName) {
        new Button(By.xpath(String.format(healthInsuranceButtonPath,healthInsuranceButtonName))).click();
    }
}