package SeleniumPractice;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WindowHandlingTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void multipleWindowHandlingTest() {

        driver.get("https://www.selenium.dev/");

        String parentWindow = driver.getWindowHandle();

        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://github.com");

        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://stackoverflow.com");

        Set<String> allWindows = driver.getWindowHandles();

        Assert.assertEquals(allWindows.size(), 3);

        List<String> windows = new ArrayList<>(allWindows);

        for (String window : windows) {

            driver.switchTo().window(window);

            System.out.println("Title : " + driver.getTitle());
            System.out.println("URL   : " + driver.getCurrentUrl());

            Assert.assertFalse(driver.getTitle().isEmpty());
        }

        driver.switchTo().window(parentWindow);

        Assert.assertTrue(driver.getTitle().contains("Selenium"));

        for (String window : windows) {

            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                driver.close();
            }
        }

        driver.switchTo().window(parentWindow);

        Assert.assertEquals(driver.getWindowHandles().size(), 1);

        System.out.println("Successfully returned to parent window");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}