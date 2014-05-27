package com.elementcollection.adapter.selenium;

import com.elementcollection.driver.Driver;
import com.elementcollection.element.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class Drivers {

    private Drivers() {
    }

    public static Driver fromWebDriver(final WebDriver webDriver) {
        return new Driver() {
            @Override
            public List<Element> findElements(String cssSelector) {
                return Elements.fromWebElements(webDriver.findElements(By.cssSelector(cssSelector)));
            }
        };
    }
}
