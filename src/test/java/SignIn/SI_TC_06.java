package SignIn;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SI_TC_06 {

    WebDriver driver;

    @BeforeMethod
    public void setUp() throws InterruptedException{
        driver = new ChromeDriver();
        driver.get("https://demoblaze.com/index.html"); // Replace with your actual sign-in page URL
        driver.manage().window().maximize();

        WebElement loginNavBtn = driver.findElement(By.id("login2"));
        loginNavBtn.click();

        Thread.sleep(1000);
    }

    @Test
    public void verifyErrorOnEmptyFormSubmission() throws InterruptedException{

        WebElement usernameField = driver.findElement(By.id("loginusername"));
        usernameField.sendKeys("validuser5");

        WebElement passwordField = driver.findElement(By.id("loginpassword"));
        passwordField.sendKeys("user123");

        WebElement loginBtn = driver.findElement(By.xpath("//button[text()='Log in']"));
        loginBtn.click();

        Thread.sleep(2000);

        WebElement msg = driver.findElement(By.id("nameofuser"));

        assert msg.isDisplayed();


    }

    @AfterMethod
    public void tearDown() throws InterruptedException{
        Thread.sleep(1000);
        driver.quit();
    }

}
