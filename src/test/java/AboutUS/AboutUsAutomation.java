package AboutUS;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class AboutUsAutomation {


    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testAboutUsModal() {
        // Click on "About us"
        driver.findElement(By.linkText("About us")).click();

        // Wait for modal to appear
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("videoModal")));

        // Verify modal title is displayed
        WebElement modalTitle = driver.findElement(By.xpath("//h5[@id='videoModalLabel']"));
        assert modalTitle.getText().equalsIgnoreCase("About us");

        // Verify video or error message is present
        WebElement videoContainer = driver.findElement(By.xpath("//div[@id='videoModal']//video | //div[@id='videoModal']//div[contains(text(),'could not be loaded')]"));
        assert videoContainer.isDisplayed();

        System.out.println("About Us modal opened successfully.");

        // Close the modal
        driver.findElement(By.xpath("//div[@id='videoModal']//button[text()='Close']")).click();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}


