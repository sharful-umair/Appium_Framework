package IOS;

import BaseTestUtils.IOSBaseTest;
import PageObjectsIOS.AlertViewPage;
import io.appium.java_client.AppiumBy;
import org.testng.Assert;
import org.testng.annotations.Test;

public class IOS_TC_1 extends IOSBaseTest {

    @Test
    public void IOSBasicTests()
    {
        AlertViewPage alertViewPage = homePage.selectAlertViews();
        alertViewPage.setTextEntry();
        alertViewPage.setEnterText("Hello World");
        alertViewPage.clickOKButton();
        alertViewPage.clickConfirmCancelButton();
        alertViewPage.clickConfirmButton();
        String msg = alertViewPage.getConfirmMessage();
        Assert.assertEquals(msg, "A message should be a short, complete sentence");
    }
}
