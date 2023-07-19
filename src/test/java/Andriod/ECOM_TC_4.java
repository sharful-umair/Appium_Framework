package Andriod;

import BaseTestUtils.AndroidBaseTest;
import PageObjectsAndroid.CartPage;
import PageObjectsAndroid.FormPage;
import PageObjectsAndroid.ProductCatalogue;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.json.simple.parser.*;
import java.io.FileReader;



public class ECOM_TC_4 extends AndroidBaseTest {


    @BeforeMethod(alwaysRun = true)
    public void preSetup()
    {
        formPage.setActivity();
    }


    @Test(dataProvider = "userData", groups = {"Smoke"})
    public void E2E(String name, String gender, String country) throws InterruptedException {

        formPage = new FormPage(driver);
        formPage.setNameField(name);
        formPage.setGender(gender);
        formPage.setCountrySelection(country);
        ProductCatalogue productCatalogue = formPage.submitForm();
        Thread.sleep(3000);

        //ProductCatalogue productCatalogue = new ProductCatalogue(driver);
        productCatalogue.addItemToCartByIndex(0);
        productCatalogue.addItemToCartByIndex(0);
        CartPage cartpage = productCatalogue.goToCartPage();
        Thread.sleep(3000);

        double totalSum = cartpage.getProductSum();
        double displayedSum = cartpage.getTotalAmountDisplayed();
        Assert.assertEquals(totalSum, displayedSum);

        cartpage.acceptTermsConditions();
        cartpage.submitOrder();
    }


    @DataProvider(name = "userDataJson")
    public Object[][] getDataJson() throws IOException {

        //\\src\\main\\java\\TestData\\eCommerce.json
        List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir")+"//src//main//java//TestData//eCommerce.json");
        return new Object[][]{{data.get(0), data.get(1)}};
    }

    @DataProvider(name = "userData")
    public Object[][] getData() throws IOException {

        return new Object[][]{{"Sharful Umair", "Female", "Argentina"}, {"Md Ali Sabir", "Male", "Argentina"}};
    }
}
