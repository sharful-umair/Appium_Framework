import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class SwipeDemo extends BaseTest {

    @Test
    public void LongPressGestures() throws MalformedURLException, InterruptedException {

        //Automation Code
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Gallery")).click();
        driver.findElement(AppiumBy.accessibilityId("1. Photos")).click();
        WebElement firstImage = driver.findElement(AppiumBy.xpath("(//android.widget.ImageView)[1]"));
        String imageValue = firstImage.getAttribute("focusable");
        Assert.assertEquals(imageValue, "true");

        //Swipe Code
        swipeGesturesAction(firstImage, "left");

        Thread.sleep(4000);
        Assert.assertEquals(imageValue, "false");
    }
}
