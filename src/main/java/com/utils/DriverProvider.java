package com.utils;

import org.openqa.selenium.WebDriver;

public class DriverProvider {

    public static WebDriver driver;

    public static  WebDriver getDriverInstance() {
        return driver;
    }
    public static  void seetDriverInstance(WebDriver webDriver) {
       driver = webDriver;
    }
}
