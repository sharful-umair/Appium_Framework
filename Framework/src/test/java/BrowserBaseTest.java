import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BrowserBaseTest {

    public AppiumDriverLocalService service;
    public AndroidDriver driver;


    @BeforeTest
    public void Configure() throws MalformedURLException {
        File mainJsPath = new File("C://Users//sharf//AppData//Roaming//npm//node_modules//appium//build//lib//main.js");
        AppiumServiceBuilder builder = new AppiumServiceBuilder().withAppiumJS(mainJsPath)
                .withIPAddress("127.0.0.1")
                .usingPort(4723);

        service = AppiumDriverLocalService.buildService(builder);

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Pixel 6 Sharful");
        options.setChromedriverExecutable("C://Users//sharf//Downloads//chromedriver_win32 (7)//chromedriver.exe");

        options.setCapability("browserName", "Chrome");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        //Timeout
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
}
