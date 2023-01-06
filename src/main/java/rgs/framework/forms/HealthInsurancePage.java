package rgs.framework.forms;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import rgs.framework.elements.Button;
import rgs.framework.elements.Label;
import rgs.framework.elements.TextBox;
import rgs.framework.models.UserData;
import rgs.framework.utils.ElementUtils;

public class HealthInsurancePage extends BaseForm {

    private final By healthInsuranceLabel = UniqueElementLocator;
    private final By sendRequestButton = By.xpath("//*[contains(text(),'Отправить заявку')]");
    private final By userNameFieldTextBox = By.xpath("//input[@name = 'userName']");
    private final By userPhoneFieldTextBox = By.xpath("//input[@name = 'userTel']");
    private final By userEmailFieldTextBox = By.xpath("//input[@name = 'userEmail']");
    private final By userAddressFieldTextBox = By.xpath("//input[@placeholder = 'Введите']");
    private final By acceptRulesCheckBox = By.xpath("//input[@type = 'checkbox'][contains(@class,'input')]");
    private final By checkedRulesCheckBox = By.xpath("//*[contains(@class,'is-checked')]");
    private final By callMeButton = By.xpath("//button[@type = 'submit']");
    private final By emailErrorLabel = By.xpath("//*[@formkey='email']//*[contains(@class,'input__error')]");

    private static final By UniqueElementLocator = By.xpath("//*[@itemprop]//*[contains(text(),'Добровольное медицинское страхование')]");

    public HealthInsurancePage() {
        super(UniqueElementLocator);
    }

    public boolean isHealthInsuranceHeaderExist() {
        return ElementUtils.isElementPresent(healthInsuranceLabel);
    }

    public void clickSendRequestButton() {
        new Button(sendRequestButton).click();
    }

    public boolean isRequestFormVisible() {
        return new TextBox(userNameFieldTextBox).isVisible();
    }

    public void inputUserData(UserData data) {
        new TextBox(userNameFieldTextBox).sendKeys(data.username, false);
        new TextBox(userPhoneFieldTextBox).sendKeys(data.phoneNumber, false);
        new TextBox(userEmailFieldTextBox).sendKeys(data.email, false);
        new TextBox(userAddressFieldTextBox).sendKeys(data.address, false);
    }


    //TODO: Метод не актуален, сделать проверку каждого поля через assert.that
    public UserData getInputedUserData() {
        UserData data = new UserData();
        data.username = new TextBox(userNameFieldTextBox).getValue();
        data.phoneNumber = new TextBox(userPhoneFieldTextBox).getValue();
        data.email = new TextBox(userEmailFieldTextBox).getValue();
        data.address = new TextBox(userAddressFieldTextBox).getValue();
        return data;
    }

    public void clickAcceptRulesCheckbox() {
        //Через .click() не кликается
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", new TextBox(acceptRulesCheckBox).getElement());
    }

    public boolean isRulesCheckBoxChecked() {
        return ElementUtils.isElementPresent(checkedRulesCheckBox);
    }

    public void clickCallMeButton() {
        //Через .click() не кликается
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", new Button(callMeButton).getElement());
    }

    public boolean isIncorrectEmailErrorExist() {
        return ElementUtils.isElementPresent(emailErrorLabel);
    }

    public String getIncorrectEmailErrorText() {
        return new Label(emailErrorLabel).getText();
    }
}