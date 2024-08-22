
import java.io.File;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.JavascriptExecutor;

public class SeleniumTest {

    private WebDriver webDriver;

    @BeforeEach
    public void setUp() {
        // Set up ChromeDriver path
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver");

        // Get file
        File file = new File("src/main/java/index.html");
        String path = "file://" + file.getAbsolutePath();

        // Create a new ChromeDriver instance
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        webDriver = new ChromeDriver(options);

        // Open the HTML file
        webDriver.get(path);
    }

    @AfterEach
    public void tearDown() {
        // Close the browser
        webDriver.quit();
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
        "isDivisibleBy5(5, 4).then(value => {document.getElementById(\"output2\").innerText = value;})"
        + ".catch(error => {document.getElementById(\"output2\").innerText = error;});"
    );

    try {
        Thread.sleep(3500); // Wait for the async operation to complete
    } catch (InterruptedException e) {
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

