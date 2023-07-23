package PageObjectsIOS;

import Utils.IOSActions;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class AlertViewPage extends IOSActions {

    IOSDriver driver;
    public AlertViewPage(IOSDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @iOSXCUITFindBy(accessibility = "Alert Views")
    private WebElement alertViews;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`lable == 'Text Entry']")
    private WebElement textEntry;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeCell")
    private WebElement enterText;

    @iOSXCUITFindBy(accessibility = "OK")
    private WebElement okButton;

    @iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeStaticText' AND value == 'Confirm / Cancel'")
    private WebElement confirmCancelButton;

    @iOSXCUITFindBy(iOSNsPredicate = "label == 'Confirm'")
    private WebElement confirmButton;

    @iOSXCUITFindBy(iOSNsPredicate = "name BEGINSWITH[c] 'A message'")
    private WebElement confirmMessage;

    public void selectAlertViews()
    {
        alertViews.click();
    }

    public void setTextEntry()
    {
        textEntry.click();
    }

    public void setEnterText(String text)
    {
        enterText.sendKeys(text);
    }

    public void clickOKButton()
    {
        okButton.click();
    }

    public void clickConfirmCancelButton()
    {
        confirmCancelButton.click();
    }

    public void clickConfirmButton()
    {
        confirmButton.click();
    }

    public String getConfirmMessage()
    {
        return confirmMessage.getText();
    }


}
