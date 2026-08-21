package org.example.base;

import org.example.driver.DriverFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
public class BaseTest {
    @BeforeMethod
    public void setUp() throws MalformedURLException {

        DriverFactory.initializeDriver();
    }

    @AfterMethod
    public void tearDown() {

        DriverFactory.quitDriver();
    }

}
