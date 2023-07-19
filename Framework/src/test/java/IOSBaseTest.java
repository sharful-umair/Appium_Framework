import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class IOSBaseTest {

    public IOSDriver driver;
    public AppiumDriverLocalService service;

    @BeforeClass
    public void configureAppium() throws MalformedURLException {
        File mainJsPath = new File("C://Users//sharf//AppData//Roaming//npm//node_modules//appium//build//lib//main.js");
        AppiumServiceBuilder builder = new AppiumServiceBuilder().withAppiumJS(mainJsPath)
                .withIPAddress("127.0.0.1")
                .usingPort(4723);

        service = AppiumDriverLocalService.buildService(builder);

        //service.start();

        //Device Configuration
        XCUITestOptions options = new XCUITestOptions();
        options.setDeviceName("iPhone 14");
        options.setApp("//desktop//UIKitCatalog.app");
        options.setPlatformVersion("15.5");
        //Appium -> Webdriver Agents -> Ios APPS
        options.setWdaLaunchTimeout(Duration.ofSeconds(20));

        //Creating IOS driver
        driver = new IOSDriver(new URL("http://127.0.0.1:4723"), options);
        //Timeout
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterClass
    public void tearDown()
    {
        driver.quit();

        //Stopping Appium Server
        //service.stop();
    }
}
