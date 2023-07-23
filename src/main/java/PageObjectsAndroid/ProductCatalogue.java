package PageObjectsAndroid;

import Utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalogue extends AndroidActions {

    AndroidDriver driver;
    public ProductCatalogue(AndroidDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }


    @AndroidFindBy(xpath = "//android.widget.TextView[@text='ADD TO CART']")
    private List<WebElement> addToCart;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='ADDED TO CART']")
    private List<WebElement> addedToCart;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
    private WebElement cart;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/counterText")
    private WebElement addToCartNumber;

    public void addItemToCartByIndex(int idx)
    {
        addToCart.get(idx).click();
    }

    public WebElement addToCartButton(int idx)
    {
        return addToCart.get(idx);
    }

    public WebElement addedToCartButton(int idx)
    {
        return addedToCart.get(idx);
    }

    public CartPage goToCartPage()
    {
        cart.click();
        return new CartPage(driver);
    }

    public String getNumberAddToCart()
    {
        return addToCartNumber.getText();
    }

}
