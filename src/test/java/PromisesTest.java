import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.File;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.JavascriptExecutor;

public class PromisesTest {

    private static WebDriver webDriver;

    @BeforeAll
    public static void setUp() {
        String browserName = BrowserUtils.getWebDriverName();

        switch (browserName) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("headless");
                webDriver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("-headless");
                webDriver = new FirefoxDriver(firefoxOptions);
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--headless");
                webDriver = new EdgeDriver(edgeOptions);
                break;

            case "ie":
                WebDriverManager.iedriver().setup();
                InternetExplorerOptions ieOptions = new InternetExplorerOptions();
                ieOptions.addCommandSwitches("-headless");
                webDriver = new InternetExplorerDriver(ieOptions);
                break;

            default:
                throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }

        File file = new File("src/main/java/index.html");
        String path = "file://" + file.getAbsolutePath();
        webDriver.get(path);
    }

    @Test
    public void testIsDivisibleBy5() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
        jsExecutor.executeScript(
                "isDivisibleBy5(5, 5).then(value => {document.getElementById(\"output2\").innerText = value;});");

        try {
            Thread.sleep(3500);
        } catch (Exception e) {
            e.printStackTrace();
        }

        WebElement outputElement = webDriver.findElement(By.id("output2"));
        String output = outputElement.getText().trim();

        Assertions.assertEquals("The sum is divisible by 5!", output);
    }

    @Test
    public void testIsDivisibleBy5Again() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
        jsExecutor.executeScript(
                "isDivisibleBy5(5, 4).then(value => {document.getElementById(\"output2\").innerText = value;});");

        try {
            Thread.sleep(3500);
        } catch (Exception e) {
            e.printStackTrace();
        }

        WebElement outputElement = webDriver.findElement(By.id("output2"));
        String output = outputElement.getText().trim();

        Assertions.assertEquals("The sum is NOT divisible by 5!", output);
    }

    @Test
    public void testIsDivisibleBy5Handler() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
        jsExecutor.executeScript(
                "isDivisibleBy5Handler(isDivisibleBy5(5, 5));");

        try {
            Thread.sleep(3500);
        } catch (Exception e) {
            e.printStackTrace();
        }

        WebElement outputElement = webDriver.findElement(By.id("output2"));
        String output = outputElement.getText().trim();

        Assertions.assertEquals("The sum is divisible by 5!", output);
    }

    @Test
    public void testIsDivisibleBy5HandlerAgain() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
        jsExecutor.executeScript(
                "isDivisibleBy5Handler(isDivisibleBy5(5, 4));");

        try {
            Thread.sleep(3500);
        } catch (Exception e) {
            e.printStackTrace();
        }

        WebElement outputElement = webDriver.findElement(By.id("output2"));
        String output = outputElement.getText().trim();

        Assertions.assertEquals("The sum is NOT divisible by 5!", output);
    }
}

class BrowserUtils {
    public static String getWebDriverName() {
        String[] browsers = { "chrome", "firefox", "edge", "ie" };

        for (String browser : browsers) {
            try {
                switch (browser) {
                    case "chrome":
                        WebDriverManager.chromedriver().setup();
                        new ChromeDriver().quit();
                        break;
                    case "firefox":
                        WebDriverManager.firefoxdriver().setup();
                        new FirefoxDriver().quit();
                        break;
                    case "edge":
                        WebDriverManager.edgedriver().setup();
                        new EdgeDriver().quit();
                        break;
                    case "ie":
                        WebDriverManager.iedriver().setup();
                        new InternetExplorerDriver().quit();
                        break;
                }
                return browser;
            } catch (Exception e) {
                continue;
            }
        }
        return "Unsupported Browser";
    }
}
