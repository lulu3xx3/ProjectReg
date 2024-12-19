package AutomationAssignment;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class WebAutomationAssignmentTest {
    WebDriver driver;
    @BeforeMethod
    public void StartDriverAndOpenURL(){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();

    }

    @AfterMethod
    public void closeDriver(){
        driver.quit();
    }


    /* these tests are for "input valid and invalid data, such as missing required fields or incorrect
    formats (e.g., invalid email addresses), and validate error messages." */
    @Test
    public void ValidLoginTest(){
        driver.get("https://practicetestautomation.com/practice-test-login/");

        driver.findElement(By.id("username")).sendKeys("student");
        driver.findElement(By.id("password")).sendKeys("Password123");
        driver.findElement(By.id("submit")).click();
        String successText = driver.findElement(By.cssSelector(".post-title")).getText();
        Assert.assertEquals(successText, "Logged In Successfully");

    }

    @Test
    public void InvalidPasswordTest(){
        driver.get("https://practicetestautomation.com/practice-test-login/");
        driver.findElement(By.id("username")).sendKeys("student");
        driver.findElement(By.id("password")).sendKeys("password123");
        driver.findElement(By.id("submit")).click();
        String errorMessage = driver.findElement(By.id("error")).getText();
        Assert.assertEquals(errorMessage,"Your password is invalid!");

    }

    @Test
    public void InvalidUsernameTest(){
        driver.get("https://practicetestautomation.com/practice-test-login/");
        driver.findElement(By.id("username")).sendKeys("students");
        driver.findElement(By.id("password")).sendKeys("Password123");
        driver.findElement(By.id("submit")).click();
        String errorMessage = driver.findElement(By.id("error")).getText();
        Assert.assertEquals(errorMessage,"Your username is invalid!");

    }

    /* these tests are for "Automate tests for searching by valid criteria (e.g., patient name or ID) and
invalid scenarios (e.g., nonexistent entries)" */

    @Test
    public void validSearch(){
        driver.get("https://rahulshettyacademy.com/seleniumPractise/");
        String filterWord = "ca";
        driver.findElement(By.cssSelector(".search-keyword")).sendKeys(filterWord);
        List<WebElement> productList =  driver.findElements(By.cssSelector("h4.product-name"));
        Assert.assertTrue(productList.stream().allMatch(s->s.getText().contains(filterWord)));
    }

    @Test
    public void InvalidSearch(){
        driver.get("https://rahulshettyacademy.com/seleniumPractise/");
        String filterWord = "chicken";
        driver.findElement(By.cssSelector(".search-keyword")).sendKeys(filterWord);
        Assert.assertTrue(driver.findElement(By.xpath("//h2[contains(text(),'Sorry, no products matched your search!')]")).isDisplayed());

    }


    /* this test is for "Test pagination behavior for large datasets and sorting by columns such as
patient names or dates." */

    @Test
    public void sortingTable() {
        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
        driver.findElement(By.xpath("//span[contains(text(),'Price')]")).click();

        List<WebElement> prices = driver.findElements(By.cssSelector("tr td:nth-child(2)"));


        for(int i=0;i < prices.size() - 1;i++){

            double currentPrice = Double.parseDouble(prices.get(i).getText());
            double nextPrice = Double.parseDouble(prices.get(i + 1).getText());

            if(currentPrice>nextPrice){
                System.out.println("Table is not sorted correctly.");
                Assert.fail();
            }
        }

    }




}
