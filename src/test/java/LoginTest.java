import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.junit.Assert;
import org.junit.Test;

public class LoginTest {

    @Test
    public void testInvalidLogin() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        WebDriver driver = new ChromeDriver(options);

        try {
            driver.get("https://the-internet.herokuapp.com/login");

            WebElement usernameField = driver.findElement(By.id("username"));
            WebElement passwordField = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));

            usernameField.sendKeys("wronguser");
            passwordField.sendKeys("wrongpassword");
            loginButton.click();

            WebElement errorMessage = driver.findElement(By.id("flash"));
            String errorText = errorMessage.getText();

            Assert.assertTrue(errorText.contains("Invalid credentials"));

        } finally {
            driver.quit();
        }
    }
}
