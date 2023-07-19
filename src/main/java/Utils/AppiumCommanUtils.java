package Utils;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;


public class AppiumCommanUtils {

    public AppiumDriverLocalService service;

    public Double FormattedAmount(String amount)
    {
        Double price = Double.parseDouble(amount.substring(1));
        return price;
    }

    public void waitForElementToAppear(WebElement element, AppiumDriver driver)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.attributeContains((element), "text", "Cart"));
    }

    public List<HashMap<String, String>> getJsonData(String jsonFilePath) throws IOException {
        
        String jsonContent = FileUtils.readFileToString(new File(jsonFilePath), StandardCharsets.UTF_8);

        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent,
                new TypeReference<List<HashMap<String, String>>>() {
                });

        return data;
    }

    public AppiumDriverLocalService startAppiumServer(String ipAddress, int port)
    {
        File mainJsPath = new File("C://Users//sharf//AppData//Roaming//npm//node_modules//appium//build//lib//main.js");
        AppiumServiceBuilder builder = new AppiumServiceBuilder().withAppiumJS(mainJsPath)
                .withIPAddress(ipAddress)
                .usingPort(port);
        service = AppiumDriverLocalService.buildService(builder);
        //service.start();
        return service;
    }

    public String getScreenShot(String testName, AppiumDriver driver) throws IOException {
        File source = driver.getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("user.dir") + "//reports" + testName + ".png";
        FileUtils.copyFile(source, new File(destination));
        return destination;
    }

    public String[] jsonReader() throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        FileReader fileReader = new FileReader(System.getProperty("user.dir") + "//src//main//java//TestData//eCommerce.json");
        Object obj = jsonParser.parse(fileReader);
        JSONObject jsonObj = (JSONObject) obj;
        JSONArray userLoginArr = (JSONArray) jsonObj.get("userlogins");

        String arr[] = new String[userLoginArr.size()];

        for(int i=0; i<userLoginArr.size(); i++)
        {
            JSONObject users = (JSONObject) userLoginArr.get(i);
            String name = (String) users.get("name");
            String gender = (String) users.get("gender");
            String country = (String) users.get("country");

            arr[i] = name + "," + gender + "," + country;
        }
        return arr;
    }

    public String[] split(String str)
    {
        return str.split(",");
    }
}
