package Andriod;

import BaseTestUtils.AndroidBaseTest;
import PageObjectsAndroid.ProductCatalogue;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class ECOM_TC_2 extends AndroidBaseTest {

//    @BeforeMethod
//    public void preSetup()
//    {
//        formPage.setActivity();
//    }

    @Test
    public void AddToCartButtonValidation() throws InterruptedException {
        int index = 0;
        String expectedTextPre = "ADD TO CART";
        boolean expectedResultTrue = true;
        String expectedTextPost = "ADDED TO CART";
        ProductCatalogue productCatalogue = AndroidLogin(driver);
        String addToCartButtonPreText =  productCatalogue.addToCartButton(index).getText();
        Assert.assertEquals(addToCartButtonPreText, expectedTextPre);
        boolean addToCartButtonEnabledTrue = productCatalogue.addToCartButton(index).isEnabled();
        Assert.assertEquals(addToCartButtonEnabledTrue, expectedResultTrue);
        productCatalogue.addToCartButton(index).click();
        String addToCartButtonPostText = productCatalogue.addedToCartButton(index).getText();
        Assert.assertEquals(addToCartButtonPostText, expectedTextPost);
    }

}
