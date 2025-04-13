package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

    public class ProductsPage {
        WebDriver driver;

        private By productTitles = By.cssSelector(".card-title");
        private By productPrices = By.cssSelector(".card-block .price-container");

        public ProductsPage(WebDriver driver) {
            this.driver = driver;
        }

        public List<String> getAllProductNames() {
            List<WebElement> elements = driver.findElements(productTitles);
            List<String> names = new ArrayList<>();
            for (WebElement el : elements) {
                names.add(el.getText().trim());
            }
            return names;
        }

        public List<Double> getAllProductPrices() {
            List<WebElement> elements = driver.findElements(productPrices);
            List<Double> prices = new ArrayList<>();
            for (WebElement el : elements) {
                String text = el.getText().replaceAll("[^\\d.]", ""); // Removes $ and non-numeric
                if (!text.isEmpty()) {
                    prices.add(Double.parseDouble(text));
                }
            }
            return prices;
        }
    }



