package AutomationAssignment;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class UIAutomationTest {
    WebDriver driver;

    @BeforeMethod
    public void startDriver() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
    }

    //we are entering valid info into the inputs and verifying
    @Test
    public void enteringValidInputs() {
        driver.get("https://www.tutorialrepublic.com/codelab.php?topic=javascript&file=form-validation");
        WebElement iFrameDriver = driver.findElement(By.id("preview"));
        driver.switchTo().frame(iFrameDriver);
        driver.findElement(By.cssSelector("input[name='name']")).sendKeys("krish");
        driver.findElement(By.cssSelector("input[name='email']")).sendKeys("krish.09@yahoo.com");
        driver.findElement(By.cssSelector("input[name='mobile']")).sendKeys("9876543211");
        WebElement dropdown = driver.findElement(By.xpath("//select[@name='country']"));
        Select s = new Select(dropdown);
        s.selectByVisibleText("India");
        driver.findElement(By.cssSelector("input[value='male']")).click();
        driver.findElement(By.cssSelector("input[value='Submit']")).click();
        driver.switchTo().alert().accept();
        Assert.assertEquals(driver.findElement(By.tagName("h1")).getText(), "Confirmation");

    }


    //we are entering invalid email into the input and verifying
    @Test
    public void enteringInvalidEmail() {
        driver.get("https://www.tutorialrepublic.com/codelab.php?topic=javascript&file=form-validation");
        WebElement iFrameDriver = driver.findElement(By.id("preview"));
        driver.switchTo().frame(iFrameDriver);
        driver.findElement(By.cssSelector("input[name='name']")).sendKeys("krish");
        driver.findElement(By.cssSelector("input[name='email']")).sendKeys("krish.09@yah");
        driver.findElement(By.cssSelector("input[name='mobile']")).sendKeys("9876547321");
        WebElement dropdown = driver.findElement(By.xpath("//select[@name='country']"));
        Select s = new Select(dropdown);
        s.selectByVisibleText("India");
        driver.findElement(By.cssSelector("input[value='male']")).click();
        driver.findElement(By.cssSelector("input[value='Submit']")).click();
        Assert.assertTrue(driver.findElement(By.id("emailErr")).getText().equalsIgnoreCase("Please enter a valid email address"));


    }

    //we are not giving any inputs and clicking on the submit button and verifying
    @Test
    public void notEnteringAnyField() {
        int expectedErrorCount = 5;
        driver.get("https://www.tutorialrepublic.com/codelab.php?topic=javascript&file=form-validation");
        WebElement iFrameDriver = driver.findElement(By.id("preview"));
        driver.switchTo().frame(iFrameDriver);
        driver.findElement(By.cssSelector("input[value='Submit']")).click();
        int actualErrors = driver.findElements(By.cssSelector(".error")).size();
        Assert.assertEquals(actualErrors, expectedErrorCount);


    }

    @Test
    public void validLoginForm(){
        driver.get("https://the-internet.herokuapp.com/login");
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.tagName("button")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector(".success")).isDisplayed());

    }

    @Test
    public void invalidUsername(){
        driver.get("https://the-internet.herokuapp.com/login");
        driver.findElement(By.id("username")).sendKeys("tomsmith ");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.tagName("button")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector(".error")).isDisplayed());

    }


    @AfterMethod
    public void closeDriver() {
        driver.close();
    }
}
