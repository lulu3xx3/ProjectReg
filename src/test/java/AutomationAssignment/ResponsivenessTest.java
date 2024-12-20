package AutomationAssignment;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class ResponsivenessTest {
    WebDriver driver;

    @BeforeMethod
    public void startDriver() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    }

    @Test
    public void desktopSizeTest(){
        driver.get("https://chatgpt.com/");
        driver.manage().window().setSize(new Dimension(1920, 1080));
        boolean isPromptEnabled = driver.findElement(By.xpath("//div[@id='prompt-textarea']")).isEnabled();
        Assert.assertTrue(isPromptEnabled, "Prompt Is NOT ENABLED");
    }

    @Test
    public void tabletSizeTest(){
        driver.get("https://chatgpt.com/");
        driver.manage().window().setSize(new Dimension(768, 1024));
        boolean isPromptEnabled = driver.findElement(By.xpath("//div[@id='prompt-textarea']")).isEnabled();
        Assert.assertTrue(isPromptEnabled, "Prompt Is NOT ENABLED");
    }

    @Test
    public void mobileSizeTest(){
        driver.get("https://chatgpt.com/");
        driver.manage().window().setSize(new Dimension(375, 667));
        boolean isPromptEnabled = driver.findElement(By.xpath("//div[@id='prompt-textarea']")).isEnabled();
        Assert.assertTrue(isPromptEnabled, "Prompt Is NOT ENBLED");
    }


    @AfterMethod
    public void closeDriver() {
        driver.close();
    }
}
