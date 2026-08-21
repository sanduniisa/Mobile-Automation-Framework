package org.example.driver;

import  io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import org.example.utils.ConfigReader;

public class DriverFactory {

    private static AndroidDriver driver;

    public static void initializeDriver() throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(
                "platformName",
                ConfigReader.get("platformName")
        );

        capabilities.setCapability(
                "appium:automationName",
                ConfigReader.get("automationName")
        );

        capabilities.setCapability(
                "appium:deviceName",
                ConfigReader.get("deviceName")
        );

        capabilities.setCapability(
                "appium:appPackage",
                ConfigReader.get("appPackage")
        );

        capabilities.setCapability(
                "appium:appActivity",
                ConfigReader.get("appActivity")
        );

        capabilities.setCapability(
                "appium:autoGrantPermissions",
                ConfigReader.getBoolean("autoGrantPermissions")
        );

        driver = new AndroidDriver(
                new URL(
                        ConfigReader.get("appiumServer")
                ),
                capabilities
        );
    }

    public static AndroidDriver getDriver() {
        return driver;
    }

    public static void quitDriver() {

        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}


//public class DriverFactory {
//    private static AndroidDriver driver;
////driver factory creates driver, configure capabilities, return driver and close driver
//    public static void initializeDriver() throws MalformedURLException {
//
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//
//        capabilities.setCapability("platformName", "Android");
//        capabilities.setCapability("appium:automationName", "UiAutomator2");
//        capabilities.setCapability("appium:deviceName", "emulator-5554");
//        capabilities.setCapability("appium:appPackage", "com.swaglabsmobileapp");
//        capabilities.setCapability("appium:appActivity", "com.swaglabsmobileapp.MainActivity");
//        capabilities.setCapability("appium:autoGrantPermissions", true);
//
//        driver = new AndroidDriver(
//                new URL("http://127.0.0.1:4723"),
//                capabilities
//        );
//    }
//
//    public static AndroidDriver getDriver() {
//        return driver;
//    }
//
//    public static void quitDriver() {
//        if (driver != null) {
//            driver.quit();
//            driver = null;
//        }
//    }
//}
