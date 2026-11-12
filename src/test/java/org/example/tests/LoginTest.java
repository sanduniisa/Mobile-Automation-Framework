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
    @Test(priority = 1, description = "TC_001 -Enter standard_user and valid password to verify successful redirection to the products page", groups = {
            "smoke", "login" })
    public void validateLogin() throws Exception {
        // Wrap the login flow so failures can be logged before being rethrown.
        try {
            // Create the products page using this test thread's Appium driver.
            ProductsPage productsPage = new ProductsPage(DriverFactory.getDriver());
            // Check whether the app is already on the products page.
            if (productsPage.isProductsPageDisplayed(Duration.ofSeconds(2))) {
                // Return to the login screen if a previous app state is still active.
                productsPage.logout();
            }

            // Create the login page with the current thread's driver.
            LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
            // Submit valid credentials and retain the products page object.
            productsPage = loginPage.login("standard_user", "secret_sauce");
            // Verify that successful login navigated to the products page.
            if (productsPage.isProductsPageDisplayed()) {
                // Report success when the expected page is displayed.
                System.out.println("Products page displayed successfully after login.");
            } else {
                // Fail the test when login does not reach the expected page.
                throw new Exception("Products page is not displayed after login.");
            }
        } catch (Exception e) {
            // Print the original failure message to the error output.
            System.err.println("Login validation failed: " + e.getMessage());
            // Rethrow the failure so TestNG marks this test as failed.
            throw e;
        }
    }

    @Test(description = "TC_002 - Verify an invalid username displays the expected login error message.", groups = {
            "regression", "login", "negative" })
    public void invalidLogin() throws Exception {
        try {
            LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
            loginPage.login("standard_user2", "secret_sauce");
            String actualErrorMessage = loginPage.getLoginErrorMessage();
            Assert.assertEquals(actualErrorMessage, "Username and password do not match any user in this service.");
            System.out.println("Invalid login error message displayed successfully.");
        } catch (Exception e) {
            System.err.println("Invalid login test failed: " + e.getMessage());
            throw e;
        }
    }

    @Test(priority = 3, description = "TC_003 - Attempt login with empty username or empty password fields.", groups = {
            "regression", "login", "negative" })
    public void emptyFieldsLogin() throws Exception {
        try {
            LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
            loginPage.login("", "");
            String actualErrorMessage = loginPage.getEmptyFieldsErrorMessage();
            Assert.assertEquals(actualErrorMessage, "Username is required");
            System.out.println("Empty fields login error message displayed successfully.");
        } catch (Exception e) {
            System.err.println("Empty fields login test failed: " + e.getMessage());
            throw e;
        }
    }

    @Test(priority = 4, description = "TC_004 - Verify logout returns the user to the login screen.", groups = {
            "regression", "login", "positive" })
    public void logout() throws Exception {
        try {
            LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
            ProductsPage productsPage = loginPage.login("standard_user", "secret_sauce");

            productsPage.logout();

            LoginPage loginPageAfterLogout = new LoginPage(DriverFactory.getDriver());
            Assert.assertTrue(
                    loginPageAfterLogout.isLoginScreenDisplayed(),
                    "User was not returned to the login screen after logout.");
            Assert.assertFalse(
                    productsPage.isProductsPageDisplayed(Duration.ofSeconds(2)),
                    "Products screen is still displayed after logout.");
        } catch (Exception e) {
            System.err.println("Logout test failed: " + e.getMessage());
            throw e;
        }
    }
}
