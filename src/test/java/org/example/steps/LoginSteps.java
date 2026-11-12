package org.example.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.driver.DriverFactory;
import org.example.pages.LoginPage;
import org.example.pages.ProductsPage;
import org.testng.Assert;

import java.time.Duration;

public class LoginSteps {
    private LoginPage loginPage;
    private ProductsPage productsPage;

    @Given("the login screen is displayed")
    public void loginScreenIsDisplayed() {
        ProductsPage currentProductsPage = new ProductsPage(DriverFactory.getDriver());
        if (currentProductsPage.isProductsPageDisplayed(Duration.ofSeconds(2))) {
            currentProductsPage.logout();
        }
        loginPage = new LoginPage(DriverFactory.getDriver());
        Assert.assertTrue(loginPage.isLoginScreenDisplayed(), "Login screen is not displayed");
    }

    @When("I log in as {string} with password {string}")
    public void logIn(String username, String password) {
        productsPage = loginPage.login(username, password);
    }

    @Then("the products page is displayed")
    public void productsPageIsDisplayed() {
        Assert.assertTrue(productsPage.isProductsPageDisplayed(), "Products page is not displayed");
    }

    @Then("I see the login error {string}")
    public void loginErrorIsDisplayed(String expectedMessage) {
        Assert.assertEquals(loginPage.getLoginErrorMessage(), expectedMessage);
    }

    @Then("I see the empty fields error {string}")
    public void emptyFieldsErrorIsDisplayed(String expectedMessage) {
        Assert.assertEquals(loginPage.getEmptyFieldsErrorMessage(), expectedMessage);
    }

    @When("I log out")
    public void logOut() {
        productsPage.logout();
    }

    @Then("I am returned to the login screen")
    public void loginScreenIsDisplayedAfterLogout() {
        Assert.assertTrue(loginPage.isLoginScreenDisplayed(), "Login screen is not displayed after logout");
        Assert.assertFalse(
                productsPage.isProductsPageDisplayed(Duration.ofSeconds(2)),
                "Products screen is still displayed after logout"
        );
    }
}