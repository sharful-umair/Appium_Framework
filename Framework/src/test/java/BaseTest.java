import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
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

public class BaseTest {

    public AndroidDriver driver;
    public AppiumDriverLocalService service;

    @BeforeClass
    public void configureAppium() throws MalformedURLException {
        File mainJsPath = new File("C://Users//sharf//AppData//Roaming//npm//node_modules//appium//build//lib//main.js");
        AppiumServiceBuilder builder = new AppiumServiceBuilder().withAppiumJS(mainJsPath)
                .withIPAddress("127.0.0.1")
                .usingPort(4723);

//                .usingDriverExecutable (new File ("C://Program Files//nodejs//node.exe"))
//                .withArgument (BASEPATH, "/wd/hub")
//                .withArgument (GeneralServerFlag.SESSION_OVERRIDE)
//                .withArgument (GeneralServerFlag.LOG_LEVEL, "debug");

        service = AppiumDriverLocalService.buildService(builder);

        //service.start();

        //Device Configuration
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("RahulShetty");
        options.setChromedriverExecutable("C://Users//sharf//Downloads//chromedriver_win32 (6)//chromedriver.exe");
        //App One
        //options.setApp("C://Users//sharf//IdeaProjects//AppiumRahulShetty//src//test//resources//ApiDemos-debug.apk");

        //App Two
        options.setApp("C://Users//sharf//IdeaProjects//AppiumRahulShetty//src//test//resources//General-Store.apk");

        //Creating android driver
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        //Timeout
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public void LongPress(WebElement ele)
    {
        ((JavascriptExecutor)driver).executeScript("mobile: longClickGesture",
                ImmutableMap.of("elementId", ((RemoteWebElement)ele).getId(),
                        "duration", 2000));
    }

    public void scrollDonwAction()
    {
        boolean canScrollMore;
        do
        {
            canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
                    "left", 300, "top", 300, "width", 200, "height", 200,
                    "direction", "down",
                    "percent", 3.0
            ));
        }while(canScrollMore);
    }

    public void swipeGesturesAction(WebElement ele, String direction)
    {
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement)ele).getId(),
                "direction", direction,
                "percent", 0.90
        ));
    }

    public Double FormattedAmount(String amount)
    {
        Double price = Double.parseDouble(amount.substring(1));
        return price;
    }

    @AfterClass
    public void tearDown()
    {
        driver.quit();

        //Stopping Appium Server
        //service.stop();
    }
}
