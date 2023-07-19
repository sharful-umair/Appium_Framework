import io.appium.java_client.AppiumBy;
import org.testng.annotations.Test;

public class IOSBasics extends IOSBaseTest {

    @Test
    public void IOSBasicTests()
    {
        //Types of locator : xpath, classname, accessibility id, id. Extra locators: iosClassChain, IOSPredicateString
        driver.findElement(AppiumBy.accessibilityId("Alert Views")).click();
        driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeStaticText[`lable == 'Text Entry']")).click();
        driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeCell")).sendKeys("Hello World");
        driver.findElement(AppiumBy.accessibilityId("OK")).click();

        driver.findElement(AppiumBy.iOSNsPredicateString("type == 'XCUIElementTypeStaticText' AND value == 'Confirm / Cancel'")).click();
//        driver.findElement(AppiumBy.iOSNsPredicateString("type == 'XCUIElementTypeStaticText' AND value BEGINSWITH 'Confirm'")).click();
//        driver.findElement(AppiumBy.iOSNsPredicateString("type == 'XCUIElementTypeStaticText' AND value ENDSWITH 'Cancel'")).click();
//        driver.findElement(AppiumBy.iOSNsPredicateString("value == 'Confirm / Cancel'")).click();
//        driver.findElement(AppiumBy.iOSNsPredicateString("type == 'XCUIElementTypeStaticText' AND value BEGINSWITH[c] 'Confirm'")).click();
        driver.findElement(AppiumBy.iOSNsPredicateString("label == 'Confirm'")).click();
        String msg = driver.findElement(AppiumBy.iOSNsPredicateString("name BEGINSWITH[c] 'A message'")).getText();
        System.out.println(msg);


    }
}
