import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class iOSGestures extends IOSBaseTest {

    @Test
    public void IOSLongPress()
    {
        driver.findElement(AppiumBy.accessibilityId("Steppers")).click();

        WebElement ele =  driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`label == 'Increment'][3]"));
        Map<String, Object> params = new HashMap<>();
        params.put("element", ((RemoteWebElement)ele).getId());
        params.put("durations", 10);
        driver.executeScript("mobile : touchAndHold", params);
    }

    @Test
    public void IOSScroll()
    {
        WebElement ele = driver.findElement(AppiumBy.accessibilityId("Web View"));

        Map<String, Object> params = new HashMap<>();
        params.put("element", ((RemoteWebElement)ele).getId());
        params.put("direction", "down");
        driver.executeScript("mobile : scroll", params);

        driver.findElement(AppiumBy.accessibilityId("Web View")).click();
        driver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@name='UIKitCatalog']")).click();

        //Testing Picker View
        driver.findElement(AppiumBy.accessibilityId("Picker View")).click();
        driver.findElement(AppiumBy.accessibilityId("Red color component value")).sendKeys("80");
        driver.findElement(AppiumBy.accessibilityId("Green color component value")).sendKeys("165");
        driver.findElement(AppiumBy.accessibilityId("Blue color component value")).sendKeys("105");
        String number = driver.findElement(AppiumBy.accessibilityId("Red color component value")).getText();
        Assert.assertEquals(number, "105");
    }

    @Test
    public void IOSSlider()
    {
        WebElement ele = driver.findElement(AppiumBy.xpath("//XCUIElementTypeSlider[@label='AppElem']"));
        ele.sendKeys("0%");
        System.out.println(ele.getAttribute("value"));
        ele.sendKeys("1%");
        System.out.println(ele.getAttribute("value"));
    }

    @Test
    public void SwipeGestures()
    {
        //How to open app directly which is already install in the device
        Map<String, Object> params = new HashMap<>();
        params.put("bundleId", "com.apple.mobileslideshow");
        driver.executeScript("mobile : launchApp", params);

        driver.findElement(AppiumBy.iOSNsPredicateString("label == 'All Photos'")).click();
        List<WebElement> photos = driver.findElements(AppiumBy.iOSClassChain("**/XCUIElementTypeCell"));
        System.out.println(photos.size());
        driver.findElement(AppiumBy.xpath("//XCUIElementTypeCell[1]")).click();
        for(int i=0; i<photos.size(); i++)
        {
            String date = driver.findElement(AppiumBy.xpath("//XCUIElementTypeNavigationBar")).getAttribute("name");
            System.out.println(date);
            Map<String, Object> params1 = new HashMap<>();
            params1.put("direction", "left");
            driver.executeScript("mobile : swipe", params1);
        }
        driver.navigate().back();
        driver.findElement(AppiumBy.iOSNsPredicateString("label == 'Albums'")).click();

    }
}
