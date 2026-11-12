package org.example.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.example.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final AndroidDriver driver;
    private final WaitUtils wait;

    private By usernameField =
            AppiumBy.accessibilityId("test-Username");

    private By passwordField =
            AppiumBy.accessibilityId("test-Password");

    private By loginButton =
            AppiumBy.androidUIAutomator("new UiSelector().text(\"LOGIN\")");

    private By loginErrorMessage =
            AppiumBy.androidUIAutomator(
                    "new UiSelector().text(\"Username and password do not match any user in this service.\")"
            );
    private By emptyFieldsErrorMessage =
            AppiumBy.accessibilityId("test-Error message");

    public LoginPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver);
    }

    public void enterUsername(String username) {
        wait.waitForVisible(usernameField)
                .sendKeys(username);
    }

    public void enterPassword(String password) {
        wait.waitForVisible(passwordField)
                .sendKeys(password);
    }

    public void clickLogin() {
        wait.waitForClickable(loginButton)
                .click();
    }

    public ProductsPage login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
        return new ProductsPage(driver);
    }
    public String getLoginErrorMessage() {
        return wait.waitForVisible(loginErrorMessage)
                .getText();
    }

    public String getEmptyFieldsErrorMessage() {
        return wait.waitForVisible(emptyFieldsErrorMessage)
                                .findElement(By.xpath(".//android.widget.TextView"))
                                .getText();
    }
    public boolean isLoginScreenDisplayed() {
        return wait.waitForVisible(usernameField).isDisplayed();
    }
}
