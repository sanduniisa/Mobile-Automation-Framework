package org.example.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "org.example.steps",
        plugin = {"pretty", "html:target/cucumber-report.html"}
)
public class CucumberTestRunner extends AbstractTestNGCucumberTests {

    @BeforeClass(alwaysRun = true)
    @Parameters({"udid", "systemPort", "appiumPort"})
    public void configureDevice(String udid, String systemPort, String appiumPort) {
        CucumberDeviceConfig.set(udid, systemPort, appiumPort);
    }

    @AfterClass(alwaysRun = true)
    public void clearDeviceConfiguration() {
        CucumberDeviceConfig.clear();
    }
}