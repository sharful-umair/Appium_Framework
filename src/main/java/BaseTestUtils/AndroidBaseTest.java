package BaseTestUtils;

import PageObjectsAndroid.FormPage;
import PageObjectsAndroid.ProductCatalogue;
import Utils.AppiumCommanUtils;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class AndroidBaseTest extends AppiumCommanUtils {

    public AndroidDriver driver;
    public AppiumDriverLocalService service;
    public FormPage formPage;

    @BeforeClass(alwaysRun = true)
    public void configureAppium() throws IOException {

        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//Resources//data.properties");
        String ipAddress = System.getProperty("ipAddress") != null ? System.getProperty("ipAddress") : prop.getProperty("ipAddress");
        prop.load(fis);
        //String ipAddress = prop.getProperty("ipAddress");
        String port = prop.getProperty("port");
        service = startAppiumServer(ipAddress, Integer.parseInt(port));

        //Device Configuration
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName(prop.getProperty("AndroidDeviceName"));
        options.setChromedriverExecutable("C://Users//sharf//Downloads//chromedriver_win32 (6)//chromedriver.exe");
        //App One
        //options.setApp("C://Users//sharf//IdeaProjects//AppiumRahulShetty//src//test//resources//ApiDemos-debug.apk");

        //App Two
        options.setApp(System.getProperty("user.dir") + "//src//main//java//AppResources//General-Store.apk");

        //Creating android driver //http://127.0.0.1:4723
        driver = new AndroidDriver(service.getUrl(), options);
        //Timeout
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        formPage = new FormPage(driver);

    }

    @AfterClass(alwaysRun = true)
    public void tearDown()
    {
        driver.quit();

        //Stopping Appium Server
        //service.stop();
    }

    public ProductCatalogue AndroidLogin(AndroidDriver driver) throws InterruptedException {
        formPage = new FormPage(driver);
        formPage.setNameField("Mr Test User");
        formPage.setGender("Male");
        formPage.setCountrySelection("Argentina");
        formPage.submitForm();
        Thread.sleep(3000);
        return new ProductCatalogue(driver);
    }
}
