package Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

import static Utils.ExtentReporterNG.extentReport;

public class Listeners extends AppiumCommanUtils implements ITestListener {

    AppiumDriver driver;
    ExtentTest test;
    ExtentReports extentReport = ExtentReporterNG.getExtentReportObject();
    @Override
    public void onTestStart(ITestResult result) {

        test = extentReport.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        test.log(Status.PASS, "Test Passed Successfully");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        test.fail(result.getThrowable());

        try
        {
           driver = (AppiumDriver) result.getTestClass().getRealClass().getField("driver")
                    .get(result.getInstance());
        }
        catch (NoSuchFieldException e1)
        {
            e1.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            throw new RuntimeException(e);
        }

        try
        {
            test.addScreenCaptureFromPath(getScreenShot(result.getMethod().getMethodName(), driver), result.getMethod().getMethodName());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {

        extentReport.flush();
    }
}
