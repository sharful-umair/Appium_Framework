import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MobileBrowserTest extends BrowserBaseTest {

    @Test
    public void browserTest() throws InterruptedException {
        /*driver.get("https://www.google.com/");
        String title = driver.getTitle();
        System.out.println(title);
        driver.findElement(By.name("q")).sendKeys("Rahul Shetty Academy");
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
        Thread.sleep(5000);*/

        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        driver.findElement(By.xpath("//button[@aria-label='Toggle navigation']")).click();
        driver.findElement(By.xpath("//li[@class='nav-item active']//a[@class='nav-link']")).click();
        driver.findElement(By.xpath("//a[@routerlink='/products']")).click();
        ((JavascriptExecutor)driver).executeScript("window.scrollBy(0,1000)", "");
        String product = driver.findElement(By.xpath("//a[normalize-space()='Devops']")).getText();
        Assert.assertEquals(product, "Devops");
    }
}
