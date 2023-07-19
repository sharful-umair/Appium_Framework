import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class ScrollDemo extends BaseTest {

    @Test
    public void LongPressGestures() throws MalformedURLException {

        //Automation Code
        driver.findElement(AppiumBy.accessibilityId("Views")).click();

        //Using Google androidUIAutomator - use this when you know where to stop
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"WebView\"));"));

        //Using JavaScriptExecutor : use this when you dont know where to stop
        //scrollDonwAction();

    }
}
