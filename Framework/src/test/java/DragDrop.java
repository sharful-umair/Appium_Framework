import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class DragDrop extends BaseTest {

    @Test
    public void LongPressGestures() throws MalformedURLException, InterruptedException {

        //Automation Code
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Drag and Drop")).click();

        WebElement source = driver.findElement(AppiumBy.id("io.appium.android.apis:id/drag_dot_1"));
        //Drag and Drop Code
        ((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) source).getId(),
                "endX", 821,
                "endY", 712
        ));
        Thread.sleep(3000);
        String result = driver.findElement(AppiumBy.id("io.appium.android.apis:id/drag_result_text")).getText();
        Assert.assertEquals(result, "Dropped!");
    }
}
