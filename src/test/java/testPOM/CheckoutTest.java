package testPOM;

import base.TestUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ProductPage;

public class CheckoutTest extends TestUtil {

    @Test
    public void checkoutOrder() {
        LoginPage loginTest = new LoginPage(driver);
        ProductPage productPage =  loginTest.login("standard_user", "secret_sauce");

        SoftAssert softAssert = new SoftAssert();

        productPage.addItemToTheCart("bolt-t-shirt");
        softAssert.assertEquals(productPage.getItemsInCart(), 1);

        productPage.addItemToTheCart("bike-light");
        softAssert.assertEquals(productPage.getItemsInCart(), 2);

        softAssert.assertAll();

        WebElement shoppingCart = driver.findElement(By.className("shopping_cart_badge"));
        shoppingCart.click();

        WebElement checkoutBtn = driver.findElement(By.id("checkout"));
        checkoutBtn.click();


        CheckoutPage checkoutTest = new CheckoutPage(driver);
        CheckoutPage checkoutPage = checkoutTest.checkInfo("Nikolay", "Kostadinov", "2340");

        WebElement backHomeBtn = driver.findElement(By.id("back-to-products"));
        Assert.assertTrue(backHomeBtn.isDisplayed());
        backHomeBtn.click();
    }
}
