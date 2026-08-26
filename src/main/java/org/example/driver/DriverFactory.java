package org.example.driver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.example.utils.ConfigReader;

import java.net.MalformedURLException;
import java.net.URI;

public class DriverFactory {

    private static ThreadLocal<AndroidDriver> driver =
            new ThreadLocal<>();

    public static void initializeDriver(
            String udid,
            String systemPort,
            String appiumPort
    ) throws MalformedURLException {

        UiAutomator2Options options =
                new UiAutomator2Options();

        options.setPlatformName(ConfigReader.get("platformName"));
        options.setAutomationName(ConfigReader.get("automationName"));

        options.setDeviceName(udid);
        options.setUdid(udid);

        options.setAppPackage(ConfigReader.get("appPackage"));

        options.setAppActivity(ConfigReader.get("appActivity"));

        options.setAutoGrantPermissions(
                ConfigReader.getBoolean("autoGrantPermissions")
        );
        options.setNoReset(ConfigReader.getBoolean("noReset"));

        options.setCapability(
                "appium:systemPort",
                Integer.parseInt(systemPort)
        );
        options.setCapability(
                "appium:adbExecTimeout",
                Integer.parseInt(ConfigReader.get("adbExecTimeout"))
        );

        AndroidDriver androidDriver =
                new AndroidDriver(
                        URI.create(
                                ConfigReader.get("appiumHost") + ":"
                                        + appiumPort
                        ).toURL(),
                        options
                );

        driver.set(androidDriver);
    }

    public static AndroidDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {

        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
