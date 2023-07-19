import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class AppOpenUsingActivity extends BaseTest {

    @Test
    public void WifiSettingNameWithActivity() throws MalformedURLException {

        //Automation Code
        String appPackage = "io.appium.android.apis";
        String appActivity = "io.appium.android.apis.preference.PreferenceDependencies";
        Activity activity = new Activity(appPackage, appActivity);
        driver.startActivity(activity);
//        driver.findElement(AppiumBy.accessibilityId("Preference")).click();
//        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc='3. Preference dependencies']")).click();
        driver.findElement(AppiumBy.id("android:id/checkbox")).click();
        driver.findElement(AppiumBy.xpath("(//android.widget.RelativeLayout)[2]")).click();

        String alertTitle = driver.findElement(AppiumBy.id("android:id/alertTitle")).getText();
        Assert.assertEquals(alertTitle, "WiFi settings");

        driver.findElement(AppiumBy.id("android:id/edit")).sendKeys("SharfulUmair");
        driver.findElement(AppiumBy.id("android:id/button1")).click();

    }
}
