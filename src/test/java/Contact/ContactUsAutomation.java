package Contact;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class ContactUsAutomation {



    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/");
    }

    @Test
    public void showAlertMessageTest() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // افتح نافذة التواصل
        driver.findElement(By.linkText("Contact")).click();

        // انتظر لظهور الفورم
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("exampleModal")));

        // املأ البيانات
        driver.findElement(By.id("recipient-email")).sendKeys("test@example.com");
        driver.findElement(By.id("recipient-name")).sendKeys("Tester");
        driver.findElement(By.id("message-text")).sendKeys("This is a test message.");

        // انتظر قبل الإرسال لرؤية الضغط بوضوح (اختياري)
        Thread.sleep(3000);

        // اضغط زر الإرسال
        driver.findElement(By.xpath("//button[text()='Send message']")).click();

        // انتظر ظهور رسالة التنبيه
        wait.until(ExpectedConditions.alertIsPresent());

        // التعامل مع الـ alert
        Alert alert = driver.switchTo().alert();
        String alertMessage = alert.getText();
        System.out.println("Alert Message: " + alertMessage);

        // تحقق من الرسالة
        Assert.assertTrue(alertMessage.contains("Thanks for the message"));

        // اغلق التنبيه
        alert.accept();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
