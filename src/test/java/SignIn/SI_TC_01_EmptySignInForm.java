package SignIn;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SI_TC_01_EmptySignInForm {


    WebDriver driver;

    @BeforeMethod
    public void setUp() throws InterruptedException{
        driver = new ChromeDriver();
        driver.get("https://demoblaze.com/index.html");
        driver.manage().window().maximize();

        WebElement loginNavBtn = driver.findElement(By.id("login2"));
        loginNavBtn.click();

        Thread.sleep(1000);
    }

    @Test
    public void verifyErrorOnEmptyFormSubmission() throws InterruptedException{

        WebElement loginBtn = driver.findElement(By.xpath("//button[text()='Log in']"));
        loginBtn.click();

        Thread.sleep(1000);

        Alert alert = driver.switchTo().alert();

        // Get alert text
        String alertText = alert.getText();
        alert.accept();

        String expectedMessage = "Please fill out Username and Password.";
        Assert.assertEquals(alertText, expectedMessage);
    }

    @AfterMethod
    public void tearDown() throws InterruptedException{
        Thread.sleep(1000);
        driver.quit();
    }

}
