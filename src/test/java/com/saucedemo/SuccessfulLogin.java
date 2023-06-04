package com.saucedemo;

import base.TestUtil;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class SuccessfulLogin extends TestUtil {

    @DataProvider(name = "correctLoginData")
    public Object[][] readLoginData() {
        try {
            CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/correctLoginData.csv"));
            List<String[]> csvData = csvReader.readAll();
            Object[][] csvDataObj = new Object[csvData.size()][2];
            for (int i = 0; i < csvData.size(); i++) {
                csvDataObj[i] = csvData.get(i);
            }
            return csvDataObj;
        } catch (IOException a) {
            System.out.println("File is missing");
            return null;
        } catch (CsvException a) {
            return null;
        }
    }


    @Test(dataProvider = "correctLoginData")
    public void successfulLoginSauceDemo(String username, String password) throws InterruptedException {

        WebElement userNameInput = driver.findElement(By.cssSelector("#user-name"));
        userNameInput.click();
        userNameInput.clear();
        userNameInput.sendKeys(username);

        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.click();
        passwordInput.clear();
        passwordInput.sendKeys(password);

        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        WebElement productPage = driver.findElement(By.cssSelector(".title"));
        Assert.assertTrue(productPage.isDisplayed());

        WebElement menuBtn = driver.findElement(By.id("react-burger-menu-btn"));
        Assert.assertTrue(menuBtn.isDisplayed());
        menuBtn.click();

        WebElement logoutLink = driver.findElement(By.id("logout_sidebar_link"));
        WebDriverWait wait5 = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait5.until(ExpectedConditions.visibilityOf(logoutLink));
        Assert.assertTrue(logoutLink.isEnabled());
        logoutLink.click();
    }
}


