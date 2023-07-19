import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class ECOM_TC_4 extends BaseTest{

    @Test
    public void HybridApp() throws InterruptedException {
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField")).sendKeys("Sharful Umair");
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        //driver.hideKeyboard();
        driver.findElement(AppiumBy.xpath("//android.widget.RadioButton[@text='Female']")).click();
        driver.findElement(AppiumBy.id("android:id/text1")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));"));
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Argentina']")).click();
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        Thread.sleep(3000);
        driver.findElements(AppiumBy.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();
        driver.findElements(AppiumBy.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();

        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
        Thread.sleep(3000);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.attributeContains(driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/toolbar_title")), "text", "Cart"));

        List<WebElement> productPrices = driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productPrice"));
        int count = productPrices.size();
        double sum = 0;
        for(int i=0; i<count; i++)
        {
            String amount = productPrices.get(i).getText();
            Double price = FormattedAmount(amount);
            sum = sum + price;
        }
        String total = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
        Double totalSum = FormattedAmount(total);
        Assert.assertEquals(sum, totalSum);

        WebElement ele = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/termsButton"));
        LongPress(ele);
        String TCMsg = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/alertTitle")).getText();
        Assert.assertEquals(TCMsg, "Terms Of Conditions");
        driver.findElement(AppiumBy.id("android:id/button1")).click();
        driver.findElement(AppiumBy.className("android.widget.CheckBox")).click();
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnProceed")).click();
        Thread.sleep(10000);

        //Hybrid App
        Set<String> context = driver.getContextHandles();
        for(String contextName: context)
        {
            System.out.println(contextName);
        }
        driver.context("WEBVIEW_com.androidsample.generalstore");
        driver.findElement(By.name("q")).sendKeys("Rahul Shetty Academy");
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
        driver.pressKey(new KeyEvent(AndroidKey.BACK));

        driver.context("NATIVE_APP");

    }
}
