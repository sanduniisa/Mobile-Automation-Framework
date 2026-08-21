package org.example.tests;

import org.example.base.BaseTest;
import org.example.driver.DriverFactory;
import org.example.pages.LoginPage;
import org.example.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.time.Duration;

public class LoginTest extends BaseTest {
    @Test
    public void validateLogin() throws MalformedURLException {
        ProductsPage productsPage = new ProductsPage(DriverFactory.getDriver());
        if (productsPage.isProductsPageDisplayed(Duration.ofSeconds(2))) {
            productsPage.logout();
        }

        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        productsPage = loginPage.login("standard_user", "secret_sauce");
        Assert.assertTrue(productsPage.isProductsPageDisplayed(), "Products page is not displayed");
    }
    @Test
    public void invalidLogin() throws MalformedURLException {
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.login("standard_user2", "secret_sauce");
        String actualErrorMessage = loginPage.getLoginErrorMessage();
        Assert.assertEquals(actualErrorMessage,"Username and password do not match any user in this service.");
    }

}

