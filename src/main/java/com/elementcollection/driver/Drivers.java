package com.elementcollection.driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Drivers {
    public static Driver fromWebDriver(final WebDriver webDriver) {
        return new Driver() {
            @Override
            public List<WebElement> findElements(String cssSelector) {
                return webDriver.findElements(By.cssSelector(cssSelector));
            }
        };
    }
}
