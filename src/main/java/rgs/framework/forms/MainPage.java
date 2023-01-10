package rgs.framework.forms;

import org.openqa.selenium.By;
import rgs.framework.elements.Button;
import rgs.framework.utils.BrowserUtils;
import rgs.framework.utils.ElementUtils;


public class MainPage extends BaseForm {

    private final String subscribeFrameId = "fl-616371"; //Айди не автогенерируется
    private final String insuranceTargetButtonPath = "//*[@href][contains(text(),'%s')]";
    private final By subscribeFrame = By.id(subscribeFrameId);
    private final By closeSubscribeFrameButton = By.xpath("//*[@data-fl-track = 'click-close-login']");

    private static final By UniqueElementLocator = By.xpath("//*[@href = '/aktivatsiya']");

    public MainPage() {
        super(UniqueElementLocator);
    }


    public void ClickInsuranceTargetButton(String buttonName) {
        new Button(By.xpath(String.format(insuranceTargetButtonPath, buttonName))).click();
    }

    public boolean IsSubscribeFrameExist() {
        return ElementUtils.isElementPresent(subscribeFrame);
    }

    public void CloseSubscribeFrame() {
        BrowserUtils.switchToFrame(subscribeFrameId);
        new Button(closeSubscribeFrameButton).click();
        BrowserUtils.leaveAllFrames();
    }
}