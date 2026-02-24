package seleniumPractice;

//import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.*;

public class LinksCollector {
    public static void main(String[] args) {
       // WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.selenium.dev/");
        driver.manage().window().maximize();

        List<WebElement> allLinks = driver.findElements(By.tagName("a"));
        List<String> linkTexts = new ArrayList<>();

        for (WebElement link : allLinks) {
            String text = link.getText().trim();
            if (!text.isEmpty()) {
                linkTexts.add(text);
            }
        }

        Set<String> uniqueLinks = new LinkedHashSet<>(linkTexts);

        System.out.println("Total Links: " + linkTexts.size());
        System.out.println("Unique Links: " + uniqueLinks.size());
        System.out.println("----- Unique Link Texts -----");
        for (String link : uniqueLinks) {
            System.out.println(link);
        }

        driver.quit();
    }
}
