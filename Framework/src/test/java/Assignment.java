import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class Assignment extends BaseTest {

    @Test
    public void AssigmentOne() throws MalformedURLException, InterruptedException {

        //Automation Code
        driver.findElement(AppiumBy.accessibilityId("App")).click();
        driver.findElement(AppiumBy.accessibilityId("Alert Dialogs")).click();

        //OK Cancel dialog with a message
        String expected = "Lorem ipsum dolor sit aie consectetur adipiscing\n" +
                "Plloaso mako nuto siwuf cakso dodtos anr koop.";
        driver.findElement(AppiumBy.accessibilityId("OK Cancel dialog with a message")).click();
        String result = driver.findElement(AppiumBy.id("android:id/alertTitle")).getText();
        driver.findElement(AppiumBy.id("android:id/button1")).click();
        System.out.println(result);
        System.out.println(expected);
        Assert.assertEquals(result, expected);
    }

    @Test
    public void AssignmentTwo()
    {
        driver.findElement(AppiumBy.accessibilityId("App")).click();
        driver.findElement(AppiumBy.accessibilityId("Alert Dialogs")).click();

        //List dialog
        driver.findElement(AppiumBy.accessibilityId("List dialog")).click();
        driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[3]")).click();
        String result = driver.findElement(AppiumBy.id("android:id/message")).getText();
        Assert.assertEquals(result, "You selected: 2 , Command three");
    }
}
