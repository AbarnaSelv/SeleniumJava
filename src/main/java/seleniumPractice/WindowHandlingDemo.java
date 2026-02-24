package seleniumPractice;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Set;

public class WindowHandlingDemo {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://the-internet.herokuapp.com/windows");

        // Get parent window ID
        String parentWindow = driver.getWindowHandle();
        System.out.println("Parent Window ID: " + parentWindow);

        // Click link to open new window
        driver.findElement(By.linkText("Click Here")).click();

        // Get all windows
        Set<String> allWindows = driver.getWindowHandles();
        System.out.println("Total windows: " + allWindows.size());

        // Switch to child window
        for (String window : allWindows) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                System.out.println("Child Window Title: " + driver.getTitle());
            }
        }

        // Close child
        driver.close();

        // Switch back to parent
        driver.switchTo().window(parentWindow);
        System.out.println("Back to Parent Title: " + driver.getTitle());

        driver.quit();
    }
}