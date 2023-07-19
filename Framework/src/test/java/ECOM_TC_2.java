import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class ECOM_TC_2 extends BaseTest{

    @Test
    public void AddProductToCart() throws InterruptedException {
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField")).sendKeys("Sharful Umair");
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        //driver.hideKeyboard();
        driver.findElement(AppiumBy.xpath("//android.widget.RadioButton[@text='Female']")).click();
        driver.findElement(AppiumBy.id("android:id/text1")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));"));
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Argentina']")).click();
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        Thread.sleep(3000);

        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Jordan 6 Rings\"));"));

        int productCount = driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productName")).size();
        for(int i=0; i<productCount; i++)
        {
            String productName = driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productName")).get(i).getText();
            System.out.println(productName);
            if(productName.equalsIgnoreCase("Jordan 6 Rings"))
            {
                driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
                break;
            }
        }

        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
        Thread.sleep(3000);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.attributeContains(driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/toolbar_title")), "text", "Cart"));

        String cartProductName = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/productName")).getText();
        Assert.assertEquals(cartProductName, "Jordan 6 Rings");
    }
}
