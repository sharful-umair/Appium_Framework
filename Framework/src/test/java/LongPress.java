import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class LongPress extends BaseTest {

    @Test
    public void LongPressGestures() throws MalformedURLException {

        //Automation Code
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc='Expandable Lists']")).click();
        driver.findElement(AppiumBy.accessibilityId("1. Custom Adapter")).click();
        WebElement ele = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='People Names']"));

        //Long Press Method in BaseTest Class
        LongPress(ele);

        String longPressValue = driver.findElement(AppiumBy.id("android:id/title")).getText();
        Assert.assertEquals(longPressValue, "Sample menu");
        Assert.assertTrue(driver.findElement(AppiumBy.id("android:id/title")).isDisplayed());

    }
}
