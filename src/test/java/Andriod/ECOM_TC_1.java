package Andriod;

import BaseTestUtils.AndroidBaseTest;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;

public class ECOM_TC_1 extends AndroidBaseTest {

    @BeforeMethod
    public void preSetup()
    {
        formPage.setActivity();
    }

    @Test(dataProvider = "userData")
    public void FillFormPositive(String data) throws InterruptedException, IOException, ParseException {

        String[] users = split(data);
        String name = users[0];
        String gender = users[1];
        String country = users[2];

        formPage.setNameField(name);
        formPage.setGender(gender);
        formPage.setCountrySelection(country);
        formPage.submitForm();
        Thread.sleep(3000);
    }

    @Test
    public void FillFormNegative() throws InterruptedException {

        formPage.setGender("Female");
        formPage.setCountrySelection("Argentina");
        formPage.submitForm();
        Thread.sleep(3000);
        String toastMsg = formPage.getToastMessage();
        //String toastMsg = driver.findElement(AppiumBy.xpath("//android.widget.Toast[1]")).getAttribute("name");
        Assert.assertEquals(toastMsg, "Please enter your name");
    }

    @DataProvider(name = "userData")
    public String[] getData() throws IOException, ParseException {
        return jsonReader();
    }
}
