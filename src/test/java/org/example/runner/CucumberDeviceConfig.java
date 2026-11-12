package org.example.runner;

public final class CucumberDeviceConfig {
    private static final ThreadLocal<DeviceParameters> PARAMETERS = new ThreadLocal<>();

    private CucumberDeviceConfig() {
    }

    public static void set(String udid, String systemPort, String appiumPort) {
        PARAMETERS.set(new DeviceParameters(udid, systemPort, appiumPort));
    }

    public static DeviceParameters get() {
        DeviceParameters parameters = PARAMETERS.get();
        if (parameters == null) {
            throw new IllegalStateException("Cucumber device parameters were not initialized by the TestNG runner");
        }
        return parameters;
    }

    public static void clear() {
        PARAMETERS.remove();
    }

    public record DeviceParameters(String udid, String systemPort, String appiumPort) {
    }
}