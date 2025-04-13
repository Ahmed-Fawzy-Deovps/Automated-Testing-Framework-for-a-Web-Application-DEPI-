package AddToCart;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Card {

    @Test
    public void login() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/");

        driver.findElement(By.linkText("Log in")).click();
        Thread.sleep(Duration.ofMillis(1000).toMillis());
        driver.findElement(By.id("loginusername")).sendKeys("Marinamarina");
        driver.findElement(By.id("loginpassword")).sendKeys("123");
        Thread.sleep(Duration.ofMillis(1000).toMillis());

        driver.findElement(By.xpath("//button[@onclick='logIn()']")).click();

        String expectedText = "Welcome Marinamarina";
        Thread.sleep(Duration.ofMillis(3000).toMillis());

        WebElement userText = driver.findElement(By.id("nameofuser"));

        Assert.assertEquals(userText.getText(), "Welcome Marinamarina", "Login greeting text mismatch!");

        // Close the browser after test
         driver.quit();
    }

    @Test
    public void emptyCartWithLogin() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/");

        driver.findElement(By.linkText("Log in")).click();
        Thread.sleep(Duration.ofMillis(1000).toMillis());
        driver.findElement(By.id("loginusername")).sendKeys("Marinamarina");
        driver.findElement(By.id("loginpassword")).sendKeys("123");
        Thread.sleep(Duration.ofMillis(1000).toMillis());

        driver.findElement(By.xpath("//button[@onclick='logIn()']")).click();

        String expectedText = "Welcome Marinamarina";
        Thread.sleep(Duration.ofMillis(3000).toMillis());

        WebElement userText = driver.findElement(By.id("nameofuser"));

        if (userText.getText().equals(expectedText)) {
            driver.findElement(By.linkText("Cart")).click();
            String actualResult = "";
            String expectedResult = "You should select a product!";
            Assert.assertTrue(actualResult.contains(expectedResult));
            // Close the browser after test
             driver.quit();
        }
    }
//    @Test
//    public void emptyCartWithoutLogin(){
//        WebDriverManager.chromedriver().setup();
//        WebDriver driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        driver.get("https://www.demoblaze.com/");
//
//        driver.findElement(By.linkText("Cart")).click();
//
//        String actualResult = "";
//        WebElement expectedResult = driver.findElement(By.xpath("//button[@onclick='logIn()']"));
//        expectedResult.click();
//
//        Assert.assertEquals(expectedResult.getText(), actualResult);
//        // Close the browser after test
//        // driver.quit();
//    }

    @Test
    public void AddItemToCartAsVisitor() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/");

        Thread.sleep(Duration.ofMillis(1000).toMillis());
        driver.findElement(By.xpath("//a[@href='prod.html?idp_=1']")).click();
        Thread.sleep(Duration.ofMillis(1000).toMillis());
        driver.findElement(By.linkText("Add to cart")).click();

        WebElement expectedResult = driver.findElement(By.linkText("Log in"));
        expectedResult.click();

        // Switch to alert and accept it
        Alert alert = driver.switchTo().alert();
//        String alertText = alert.getText(); // Optional: get the alert text
//        alert.accept(); // Accept the alert

        Assert.assertEquals(expectedResult.getText(), alert.getText());
        driver.quit();

    }

    @Test
    public void AddItemToCartAsGuest() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/");

        Thread.sleep(Duration.ofMillis(1000).toMillis());
        driver.findElement(By.xpath("//a[@href='prod.html?idp_=1']")).click();

        //check login
        Thread.sleep(Duration.ofMillis(1000).toMillis());
        driver.findElement(By.linkText("Log in")).click();
        driver.findElement(By.id("loginusername")).sendKeys("Marinamarina");
        driver.findElement(By.id("loginpassword")).sendKeys("123");
        Thread.sleep(Duration.ofMillis(1000).toMillis());

        driver.findElement(By.xpath("//button[@onclick='logIn()']")).click();

        String expected = "Welcome Marinamarina";
        Thread.sleep(Duration.ofMillis(3000).toMillis());

        WebElement userText = driver.findElement(By.id("nameofuser"));
        if (userText.getText().equals(expected)) {

            Thread.sleep(Duration.ofMillis(1000).toMillis());
            driver.findElement(By.linkText("Add to cart")).click();

            WebElement expectedResult = driver.findElement(By.linkText("Log in"));
            expectedResult.click();

            // Switch to alert and accept it
            Alert alert = driver.switchTo().alert();
//        String alertText = alert.getText(); // Optional: get the alert text
//        alert.accept(); // Accept the alert

            Assert.assertEquals(expectedResult.getText(), alert.getText());
            driver.quit();

        }
    }





    @Test
    public void addItemToCart() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/");


        // login steps
        driver.findElement(By.linkText("Log in")).click();
        Thread.sleep(Duration.ofMillis(1000).toMillis());
        driver.findElement(By.id("loginusername")).sendKeys("Marinamarina");
        driver.findElement(By.id("loginpassword")).sendKeys("123");
        Thread.sleep(Duration.ofMillis(1000).toMillis());

        driver.findElement(By.xpath("//button[@onclick='logIn()']")).click();

        String expected = "Welcome Marinamarina";
        Thread.sleep(Duration.ofMillis(3000).toMillis());

        WebElement userText = driver.findElement(By.id("nameofuser"));
        if (userText.getText().equals(expected)) {


            Thread.sleep(Duration.ofMillis(1000).toMillis());
            driver.findElement(By.xpath("//a[@href='prod.html?idp_=1']")).click();
            Thread.sleep(Duration.ofMillis(1000).toMillis());
            driver.findElement(By.linkText("Add to cart")).click();
            Thread.sleep(Duration.ofMillis(1000).toMillis());
            driver.switchTo().alert().accept();
            driver.findElement(By.linkText("Cart")).click();

            String expectedResult = "Samsung galaxy s6";
            Thread.sleep(Duration.ofMillis(1000).toMillis());
            WebElement actualR = driver.findElement(By.xpath("//table[@class='table table-bordered table-hover table-striped']//tr[td[contains(text(), 'Samsung galaxy s6')]]/td[2]"));

            String actualResult = actualR.getText();
            Assert.assertEquals(actualResult, expectedResult, "Product name does not match the expected value.");

        }
    }


    @Test
    public void removeItemFromCart() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/");

        // login steps
        driver.findElement(By.linkText("Log in")).click();
        Thread.sleep(Duration.ofMillis(1000).toMillis());
        driver.findElement(By.id("loginusername")).sendKeys("Marinamarina");
        driver.findElement(By.id("loginpassword")).sendKeys("123");
        Thread.sleep(Duration.ofMillis(1000).toMillis());

        driver.findElement(By.xpath("//button[@onclick='logIn()']")).click();

        String expected = "Welcome Marinamarina";
        Thread.sleep(Duration.ofMillis(3000).toMillis());

        WebElement userText = driver.findElement(By.id("nameofuser"));
        if (userText.getText().equals(expected)) {

            Thread.sleep(Duration.ofMillis(1000).toMillis());
            driver.findElement(By.xpath("//a[@href='prod.html?idp_=1']")).click();
            Thread.sleep(Duration.ofMillis(1000).toMillis());
            driver.findElement(By.linkText("Add to cart")).click();
            Thread.sleep(Duration.ofMillis(1000).toMillis());
            driver.switchTo().alert().accept();
            driver.findElement(By.linkText("Cart")).click();
            Thread.sleep(Duration.ofMillis(1000).toMillis());
            // driver.findElement(By.xpath("//a[contains(text(),'Delete')]")).click();


            // Locate the "Delete" button for the product (Samsung Galaxy S6)
            WebElement deleteButton = driver.findElement(By.xpath("//table[@class='table table-bordered table-hover table-striped']//tr[td[contains(text(), 'Samsung galaxy s6')]]/td/a[contains(text(),'Delete')]"));
            deleteButton.click();






            Thread.sleep(Duration.ofMillis(1000).toMillis());
            // Now, verify that the row has been removed by checking if the product is still in the table
            boolean isProductDeleted = driver.findElements(By.xpath("//table[@class='table table-bordered table-hover table-striped']//tr[td[contains(text(), 'Samsung galaxy s6')]]")).isEmpty();

            // Define the expected result: product should be deleted, i.e., the row should no longer exist
            boolean expectedResult = true; // The row should no longer exist

            // Actual result: Verify the result after the deletion
            Assert.assertEquals(isProductDeleted, expectedResult, "The product was not deleted successfully.");
            driver.quit();

        }
    }

    @Test
    public void PlaceOrderWithoutAllRequiredDetails() throws InterruptedException{
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/");


        // login steps
        driver.findElement(By.linkText("Log in")).click();
        Thread.sleep(Duration.ofMillis(1000).toMillis());
        driver.findElement(By.id("loginusername")).sendKeys("Marinamarina");
        driver.findElement(By.id("loginpassword")).sendKeys("123");
        Thread.sleep(Duration.ofMillis(1000).toMillis());

        driver.findElement(By.xpath("//button[@onclick='logIn()']")).click();

        String expected = "Welcome Marinamarina";
        Thread.sleep(Duration.ofMillis(3000).toMillis());

        WebElement userText = driver.findElement(By.id("nameofuser"));
        if (userText.getText().equals(expected)) {


            Thread.sleep(Duration.ofMillis(1000).toMillis());
            driver.findElement(By.xpath("//a[@href='prod.html?idp_=1']")).click();
            Thread.sleep(Duration.ofMillis(1000).toMillis());
            driver.findElement(By.linkText("Add to cart")).click();
            Thread.sleep(Duration.ofMillis(1000).toMillis());
            driver.switchTo().alert().accept();
            driver.findElement(By.linkText("Cart")).click();

            //btn-success
            driver.findElement(By.className("btn-success")).click();

            Thread.sleep(Duration.ofMillis(1000).toMillis());

            driver.findElement(By.xpath("//button[text()='Purchase']")).click();


            String expectedResult = "Please fill out Name and Creditcard.";

            // Switch to alert and accept it
            Alert actualAlert = driver.switchTo().alert();
            String alertText = actualAlert.getText(); // Optional: get the alert text
            // actualAlert.accept(); // Accept the alert


            Assert.assertEquals(actualAlert.getText(), "Please fill out Name and Creditcard.", "check text mismatch!");
            driver.quit();

        }
    }

    @Test
    public void PlaceOrderWithAllRequiredDetails() throws InterruptedException{
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/");


        // login steps
        driver.findElement(By.linkText("Log in")).click();
        Thread.sleep(Duration.ofMillis(1000).toMillis());
        driver.findElement(By.id("loginusername")).sendKeys("Marinamarina");
        driver.findElement(By.id("loginpassword")).sendKeys("123");
        Thread.sleep(Duration.ofMillis(1000).toMillis());

        driver.findElement(By.xpath("//button[@onclick='logIn()']")).click();

        String expected = "Welcome Marinamarina";
        Thread.sleep(Duration.ofMillis(3000).toMillis());

        WebElement userText = driver.findElement(By.id("nameofuser"));
        if (userText.getText().equals(expected)) {


            Thread.sleep(Duration.ofMillis(1000).toMillis());
            driver.findElement(By.xpath("//a[@href='prod.html?idp_=1']")).click();
            Thread.sleep(Duration.ofMillis(1000).toMillis());
            driver.findElement(By.linkText("Add to cart")).click();
            Thread.sleep(Duration.ofMillis(1000).toMillis());
            driver.switchTo().alert().accept();
            driver.findElement(By.linkText("Cart")).click();

            //btn-success
            driver.findElement(By.className("btn-success")).click();

            Thread.sleep(Duration.ofMillis(1000).toMillis());

            driver.findElement(By.id("name")).sendKeys("Marina");
            driver.findElement(By.id("country")).sendKeys("Egypt");
            driver.findElement(By.id("city")).sendKeys("Giza");
            driver.findElement(By.id("card")).sendKeys("12345");
            driver.findElement(By.id("month")).sendKeys("March");
            driver.findElement(By.id("year")).sendKeys("2000");

            driver.findElement(By.xpath("//button[text()='Purchase']")).click();

            Thread.sleep(Duration.ofMillis(1000).toMillis());
            WebElement actualResult = driver.findElement(By.xpath("//h2[text()='Thank you for your purchase!']"));


            String expectedResult = "Thank you for your purchase!";

            Assert.assertEquals(actualResult.getText(), "Thank you for your purchase!", "check text mismatch!");
            driver.quit();

        }
    }



    @Test
    public void CheckTotalPriceCalculation() throws InterruptedException{
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/");


        // login steps
        driver.findElement(By.linkText("Log in")).click();
        Thread.sleep(Duration.ofMillis(1000).toMillis());
        driver.findElement(By.id("loginusername")).sendKeys("Marinamarina");
        driver.findElement(By.id("loginpassword")).sendKeys("123");
        Thread.sleep(Duration.ofMillis(1000).toMillis());

        driver.findElement(By.xpath("//button[@onclick='logIn()']")).click();

        String expected = "Welcome Marinamarina";
        Thread.sleep(Duration.ofMillis(3000).toMillis());

        WebElement userText = driver.findElement(By.id("nameofuser"));
        if (userText.getText().equals(expected)) {


            Thread.sleep(Duration.ofMillis(1000).toMillis());
            driver.findElement(By.xpath("//a[@href='prod.html?idp_=1']")).click();
            Thread.sleep(Duration.ofMillis(1000).toMillis());
            driver.findElement(By.linkText("Add to cart")).click();
            Thread.sleep(Duration.ofMillis(1000).toMillis());
            driver.switchTo().alert().accept();

            Thread.sleep(Duration.ofMillis(1000).toMillis());
            driver.findElement(By.partialLinkText("Home")).click();
            Thread.sleep(Duration.ofMillis(1000).toMillis());
            driver.findElement(By.xpath("//a[@href='prod.html?idp_=2']")).click();
            Thread.sleep(Duration.ofMillis(1000).toMillis());
            driver.findElement(By.linkText("Add to cart")).click();
            Thread.sleep(Duration.ofMillis(1000).toMillis());
            driver.switchTo().alert().accept();

            driver.findElement(By.linkText("Cart")).click();


            WebElement actual = driver.findElement(By.id("totalp"));
            // actual = actual.re("[^0-9]", "");

//              String number = actual.getText();
//            int actualResult = Integer.parseInt(number);
//              System.out.println(actualResult);


            String number = actual.getText();
            System.out.println("[DEBUG] Total price text: '" + number + "'");
            int  actualResult = 0 ;
            if (!number.isEmpty() && number.matches("\\d+")) {
                actualResult = Integer.parseInt(number);
                System.out.println("[DEBUG] Parsed total: " + actualResult);
            } else {
                System.out.println("[ERROR] Total price is empty or invalid!");
            }


            List<WebElement> priceCells = driver.findElements(By.xpath("//tbody[@id='tbodyid']//tr[@class='success']/td[3]"));

            int expectedResult = 0;
            for (WebElement cell : priceCells) {
                String priceText = cell.getText().trim();
                int price = Integer.parseInt(priceText);
                expectedResult += price;
            }

            System.out.println("Total sum of prices: " + expectedResult);



            Assert.assertEquals(actualResult, expectedResult, "The total price displayed does not match the expected value.");
            // Assert.assertEquals(actualResult,expectedResult);
//
//            Assert.assertEquals(actualResult.getText(), "Thank you for your purchase!", "check text mismatch!");
            driver.quit();

        }
    }


    @Test
    public void reach() throws InterruptedException{
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/");



        driver.findElement(By.linkText("Cart")).click();
        Thread.sleep(Duration.ofMillis(1000).toMillis());
        driver.findElement(By.partialLinkText("Home")).click();
        driver.quit();


    }



}
