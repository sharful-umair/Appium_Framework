package PageObjectsAndroid;

import Utils.AndroidActions;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class FormPage extends AndroidActions {

    AndroidDriver driver;
    public FormPage(AndroidDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
    private WebElement nameField;

    @AndroidFindBy(xpath = "//android.widget.RadioButton[@text='Female']")
    private WebElement femaleOptions;

    @AndroidFindBy(xpath = "//android.widget.RadioButton[@text='Male']")
    private WebElement maleOptions;

    @AndroidFindBy(id = "android:id/text1")
    private WebElement countrySelection;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
    private WebElement shopButton;

    @AndroidFindBy(xpath = "//android.widget.Toast[1]")
    private WebElement toastMessage;


    public void setNameField(String name)
    {
        nameField.sendKeys(name);
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
    }

    public void setGender(String gender)
    {
        if(gender.contains("female"))
        {
            femaleOptions.click();
        }
        else
        {
            maleOptions.click();
        }
    }

    public void setCountrySelection(String countryName)
    {
        countrySelection.click();
        scrollToText(countryName);
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='"+countryName+"']")).click();
    }

    public ProductCatalogue submitForm()
    {
        shopButton.click();
        return new ProductCatalogue(driver);
    }

    public String getToastMessage()
    {
        return toastMessage.getAttribute("name");
    }

    public void setActivity()
    {
        Activity activity = new Activity("com.androidsample.generalstore", "com.androidsample.generalstore.SplashActivity");
        driver.startActivity(activity);
    }
}
