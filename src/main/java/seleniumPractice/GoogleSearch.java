package seleniumPractice;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;

public class GoogleSearch {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://www.google.com/ncr");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("Selenium");

        List<WebElement> suggestions = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath("//ul[@role='listbox']//li")
                )
        );

        System.out.println("Suggestions Count: " + suggestions.size());
        for (WebElement option : suggestions) {
            System.out.println(option.getText());
        }

        searchBox.sendKeys(Keys.ENTER);

        wait.until(ExpectedConditions.titleContains("Selenium"));

        String title = driver.getTitle();
        System.out.println("Page Title: " + title);

        if (title.toLowerCase().contains("selenium")) {
            System.out.println("Search Successful");
        } else {
            System.out.println("Search Failed");
        }

        driver.quit();
    }
}