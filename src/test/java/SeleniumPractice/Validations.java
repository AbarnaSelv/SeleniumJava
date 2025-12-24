package SeleniumPractice;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class Validations {

	WebDriver driver;
	JavascriptExecutor js;

	@BeforeClass
	public void launchBrowser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		js = (JavascriptExecutor) driver;
		driver.manage().window().maximize();
		driver.get("https://www.selenium.dev/selenium/web/web-form.html");
		System.out.println("Browser launched and application opened successfully");
	}

	@Test
	public void validate() {

		WebElement textInput = driver.findElement(By.name("my-text"));
		Assert.assertTrue(textInput.isDisplayed());
		System.out.println("Text input field is displayed");

		WebElement submitButton = driver.findElement(By.cssSelector("button"));
		Assert.assertTrue(submitButton.isEnabled());
		System.out.println("Submit button is enabled");

		WebElement radioButton = driver.findElement(By.id("my-radio-2"));
		js.executeScript("arguments[0].scrollIntoView(true);", radioButton);
		radioButton.click();
		Assert.assertTrue(radioButton.isSelected());
		System.out.println("Radio button is selected successfully");
	}

	@AfterClass
	public void closeBrowser() {
		driver.quit();
		System.out.println("Browser closed and test execution completed");
	}
}
