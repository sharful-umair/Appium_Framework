package PageObjectsAndroid;

import Utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AndroidActions {

    AndroidDriver driver;
    public CartPage(AndroidDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
    private List<WebElement> productList;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
    private WebElement totalAmount;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/termsButton")
    private WebElement terms;

    @AndroidFindBy(id = "android:id/button1")
    private WebElement acceptButton;

    @AndroidFindBy(className = "android.widget.CheckBox")
    private WebElement checkBox;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnProceed")
    private WebElement proceed;

    public List<WebElement> getProductList()
    {
        return productList;
    }

    public double getProductSum()
    {
        int count = productList.size();
        double sum = 0;
        for(int i=0; i<count; i++)
        {
            String amount = productList.get(i).getText();
            Double price = FormattedAmount(amount);
            sum = sum + price;
        }
        return sum;
    }

    public Double getTotalAmountDisplayed()
    {
        return FormattedAmount(totalAmount.getText());
    }

    public void acceptTermsConditions()
    {
        LongPress(terms);
        acceptButton.click();
    }

    public void submitOrder()
    {
        checkBox.click();
        proceed.click();
    }

}
