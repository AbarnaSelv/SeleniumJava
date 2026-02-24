package SeleniumPractice;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class HighlightAllButtons {

    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    @BeforeClass
    public void setup() {
        //WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        js = (JavascriptExecutor) driver;
    }

    @Test
    public void highlightAllButtons() {
        driver.get("https://demoqa.com/buttons");

        // Wait for buttons to be present in DOM
        List<WebElement> buttons = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("button"))
        );

        System.out.println("===== BUTTON HIGHLIGHT REPORT =====");
        System.out.println("Total buttons found: " + buttons.size());
        System.out.println("----------------------------------");

        int count = 1;
        for (WebElement button : buttons) {
            // Scroll to button
            js.executeScript("arguments[0].scrollIntoView(true);", button);
            // Add border
            js.executeScript("arguments[0].style.border='3px solid purple'", button);
            // Flash background
            for (int i = 0; i < 2; i++) {
                js.executeScript("arguments[0].style.backgroundColor='lightblue'", button);
                try { Thread.sleep(250); } catch (InterruptedException e) {}
                js.executeScript("arguments[0].style.backgroundColor='white'", button);
                try { Thread.sleep(250); } catch (InterruptedException e) {}
            }

            // Professional console log
            System.out.printf("Button %d: Text='%s', Enabled=%s, Displayed=%s%n",
                    count++, button.getText(), button.isEnabled(), button.isDisplayed());
        }

        System.out.println("==================================");
        System.out.println("All buttons highlighted successfully!");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
