import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.DeviceRotation;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class Miscellanous extends BaseTest {

    @Test
    public void WifiSettingName() throws MalformedURLException {

        //Automation Code
        driver.findElement(AppiumBy.accessibilityId("Preference")).click();
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc='3. Preference dependencies']")).click();
        driver.findElement(AppiumBy.id("android:id/checkbox")).click();

        //Landscape mode
        //DeviceRotation landscape = new DeviceRotation(0, 0, 90);
        //driver.rotate(landscape);

        driver.findElement(AppiumBy.xpath("(//android.widget.RelativeLayout)[2]")).click();

        String alertTitle = driver.findElement(AppiumBy.id("android:id/alertTitle")).getText();
        Assert.assertEquals(alertTitle, "WiFi settings");

        //Copy Paste - > Copy to clipboard paste to clipboard
        driver.setClipboardText("SharfulUmair");
        driver.findElement(AppiumBy.id("android:id/edit")).sendKeys(driver.getClipboardText());
        //Using Key Event
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
        driver.findElement(AppiumBy.id("android:id/button1")).click();
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        driver.pressKey(new KeyEvent(AndroidKey.HOME));
    }
}
