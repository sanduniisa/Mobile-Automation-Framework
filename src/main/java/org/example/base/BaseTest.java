package org.example.base;

import org.example.driver.DriverFactory;
import org.testng.annotations.*;

import java.net.MalformedURLException;

public class BaseTest {

    @BeforeMethod
    @Parameters({
            "udid",
            "systemPort",
            "appiumPort"
    })
    public void setUp(
            String udid,
            String systemPort,
            String appiumPort
    ) throws MalformedURLException {

        DriverFactory.initializeDriver(
                udid,
                systemPort,
                appiumPort
        );
    }

    @AfterMethod
    public void tearDown() {

        DriverFactory.quitDriver();
    }
}