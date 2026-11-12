package org.example.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.example.driver.DriverFactory;
import org.example.runner.CucumberDeviceConfig;

import java.net.MalformedURLException;

public class LoginHooks {

    @Before
    public void startDriver() throws MalformedURLException {
        CucumberDeviceConfig.DeviceParameters parameters = CucumberDeviceConfig.get();
        DriverFactory.initializeDriver(
                parameters.udid(),
                parameters.systemPort(),
                parameters.appiumPort()
        );
    }

    @After
    public void stopDriver() {
        DriverFactory.quitDriver();
    }
}