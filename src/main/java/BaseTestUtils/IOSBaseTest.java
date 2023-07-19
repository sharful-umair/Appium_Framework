package BaseTestUtils;

import PageObjectsIOS.HomePage;
import Utils.AppiumCommanUtils;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public class IOSBaseTest extends AppiumCommanUtils {

    public IOSDriver driver;
    public AppiumDriverLocalService service;
    public HomePage homePage;

    @BeforeClass(alwaysRun = true)
    public void configureAppium() throws IOException {

        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//Resources//data.properties");
        prop.load(fis);
        String ipAddress = prop.getProperty("ipAddress");
        String port = prop.getProperty("port");
        service = startAppiumServer(ipAddress, Integer.parseInt(port));

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
        homePage = new HomePage(driver);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown()
    {
        driver.quit();

        //Stopping Appium Server
        //service.stop();
    }
}
