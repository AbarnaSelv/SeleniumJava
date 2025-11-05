package seleniumPractice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;

public class WebTableDemo {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.get("https://testautomationpractice.blogspot.com/");
        driver.manage().window().maximize();

       
        List<WebElement> rows = driver.findElements(By.xpath("//table[@name='BookTable']/tbody/tr"));

        String topBook = "";
        double maxPrice = 0.0;

       
        for (int i = 2; i <= rows.size(); i++) {
            String book = driver.findElement(By.xpath("//table[@name='BookTable']/tbody/tr[" + i + "]/td[1]")).getText();
            String priceText = driver.findElement(By.xpath("//table[@name='BookTable']/tbody/tr[" + i + "]/td[4]")).getText();

            double price = Double.parseDouble(priceText);
            System.out.println(book + " -> " + price);

            if (price > maxPrice) {
                maxPrice = price;
                topBook = book;
            }
        }

        System.out.println("\nHighest Book Price: " + topBook + " (" + maxPrice + ")");
        driver.quit();
    }
}
