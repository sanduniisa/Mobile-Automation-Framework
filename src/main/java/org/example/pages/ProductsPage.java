package org.example.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.example.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class ProductsPage {
    private final AndroidDriver driver;
    private final WaitUtils wait;
    private final By productsTitle = By.xpath("//android.widget.TextView[@text=\"PRODUCTS\"]");
    private final By menuButton = AppiumBy.accessibilityId("test-Menu");
    private final By logoutButton = AppiumBy.accessibilityId("test-LOGOUT");
    public ProductsPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver);
    }

    public boolean isProductsPageDisplayed() {
        return wait.waitForVisible(productsTitle).isDisplayed();
    }

    public boolean isProductsPageDisplayed(Duration timeout) {
        // This short probe is specific to the login test's starting-state check.
        try {
            return new WebDriverWait(driver, timeout)
                    .until(ExpectedConditions.visibilityOfElementLocated(productsTitle))
                    .isDisplayed();

        } catch (TimeoutException ignored) {

            return false;
        }
    }

    public void logout() {
        wait.waitForClickable(menuButton).click();
        wait.waitForClickable(logoutButton).click();
    }
}
