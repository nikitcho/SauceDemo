package com.saucedemo;

import base.TestUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;


public class UnSuccessfulLogin extends TestUtil {

    //public WebDriver driver;

    //@AfterTest
    //public void tearDown(){
       // driver.quit();

   // }

   // @BeforeTest
    //public void setupDriverAndOpenTestSIte(){
        //WebDriverManager.edgedriver().setup();
        //driver = new EdgeDriver();
        //driver.get("https://www.saucedemo.com/");
    //}

    @DataProvider(name = "wrongData")
    public Object [][] getUsers(){
        return new Object[][]{
                {"wrongUsername", "secret_sauce"},
                {"standard_user", "wrongPassword"},
                {"wrong", "wrong"}

        };
    }

    @Test(dataProvider = "wrongData")
    public void unsuccessfulLoginSauceDemo(String username, String password) {
        driver.manage().window().maximize();

        //driver.get("https://www.saucedemo.com/");
        WebElement userNameInput = driver.findElement(By.cssSelector("#user-name"));
        WebDriverWait wait5 = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait5.until(ExpectedConditions.visibilityOf(userNameInput));
        userNameInput.click();
        userNameInput.clear();
        userNameInput.sendKeys(username);

        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.click();
        passwordInput.clear();
        passwordInput.sendKeys(password);
        passwordInput.submit();

        WebElement error = driver.findElement(By.className("error-button"));
        wait5.until(ExpectedConditions.visibilityOf(userNameInput));
        Assert.assertTrue(error.isDisplayed());

    }

}
