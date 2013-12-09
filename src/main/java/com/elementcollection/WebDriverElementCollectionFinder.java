package com.elementcollection;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

/**
 * <br> User: Mangan <br> Date: 09/12/13
 */
@ParametersAreNonnullByDefault
public class WebDriverElementCollectionFinder {

    private final WebDriver webDriver;
    private FindContext findContext;

    public WebDriverElementCollectionFinder(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.findContext = defaultFindContext(webDriver);
    }

    public List<WebElement> find(String cssSelector) {
        final List<WebElement> webElements = findContext.find(cssSelector);
        findContext = defaultFindContext(webDriver);
        return webElements;
    }

    public WebDriverElementCollectionFinder within(int delayInMillis) {
        this.findContext = new Delayed(new WebDriverFindFunction(webDriver), delayInMillis);
        return this;
    }

    private RightNow defaultFindContext(WebDriver webDriver) {
        return new RightNow(new WebDriverFindFunction(webDriver));
    }
}
