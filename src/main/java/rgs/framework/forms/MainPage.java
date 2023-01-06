package rgs.framework.forms;

import org.openqa.selenium.By;
import rgs.framework.elements.Button;
import rgs.framework.utils.BrowserUtils;
import rgs.framework.utils.ElementUtils;


public class MainPage extends BaseForm {

    private final String subscribeFrameId = "fl-616371"; //Айди не автогенерируется
    private final By subscribeFrame = By.id(subscribeFrameId);
    private final By forCompainesButton = By.xpath("//*[@href = '/for-companies']");
    private final By closeSubscribeFrameButton = By.xpath("//*[@data-fl-track = 'click-close-login']");

    private static final By UniqueElementLocator = By.xpath("//*[@href = '/aktivatsiya']");

    public MainPage() {
        super(UniqueElementLocator);
    }

    public void ClickCompaniesButton() {
        new Button(forCompainesButton).click();
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