package SignUp;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SU_TC_03 {

    WebDriver driver;

    @BeforeMethod
    public void setUp() throws InterruptedException{
        driver = new ChromeDriver();
        driver.get("https://demoblaze.com/index.html"); // Replace with your actual sign-in page URL
        driver.manage().window().maximize();

        WebElement loginNavBtn = driver.findElement(By.id("signin2"));
        loginNavBtn.click();

        Thread.sleep(1000);
    }

    @Test
    public void verifyErrorOnEmptyFormSubmission() throws InterruptedException{



        // Step 1: Test with username less than 4 characters
        WebElement usernameField = driver.findElement(By.id("sign-username"));
        WebElement passwordField = driver.findElement(By.id("sign-password"));
        usernameField.clear();
        passwordField.clear();
        usernameField.sendKeys("abcdefghijabcdefghijabcdefghijabcdefghijabcdefghij");  // Username < 4 characters
        passwordField.sendKeys("testpass");

        WebElement loginBtn = driver.findElement(By.xpath("//button[text()='Sign up']"));
        loginBtn.click();

        Thread.sleep(1000);

        Alert alert = driver.switchTo().alert();

        // Get alert text
        String alertText = alert.getText();


        alert.accept();

        Assert.assertTrue(alertText.contains("too long"), "Username should not exceed 50 characters.");
        Thread.sleep(1000);


    }

    @AfterMethod
    public void tearDown() throws InterruptedException{
        Thread.sleep(1000);
        driver.quit();
    }

}
