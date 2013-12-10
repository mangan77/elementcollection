package com.elementcollection.finder;

import org.openqa.selenium.WebDriver;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * <br> User: Mangan <br> Date: 10/12/13
 */
@ParametersAreNonnullByDefault
public final class ElementCollectionFinders {

    private ElementCollectionFinders() {
    }

    public static ElementCollectionFinder fromWebDriver(WebDriver webDriver) {
        return new ElementCollectionFinderImpl(webDriver);
    }
}