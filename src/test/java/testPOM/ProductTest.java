package testPOM;

import base.TestUtil;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;
import pages.ProductPage;

public class ProductTest extends TestUtil {

    @Test
    public void addItemToCart(){
        LoginPage loginTest = new LoginPage(driver);
        ProductPage productPage =  loginTest.login("standard_user", "secret_sauce");

        SoftAssert softAssert = new SoftAssert();

        productPage.addItemToTheCart("bolt-t-shirt");
        softAssert.assertEquals(productPage.getItemsInCart(), 1);

        productPage.addItemToTheCart("bike-light");
        softAssert.assertEquals(productPage.getItemsInCart(), 2);

        softAssert.assertAll();
    }
}
