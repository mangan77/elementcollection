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
        this.findContext = defaultFindContext();
    }

    public List<WebElement> find(String cssSelector) {
        final List<WebElement> webElements = findContext.find(cssSelector, new WebDriverFindFunction(webDriver));
        findContext = defaultFindContext();
        return webElements;
    }

    public WebDriverElementCollectionFinder within(int delayInMillis) {
        this.findContext = new Delayed(delayInMillis);
        return this;
    }

    private RightNow defaultFindContext() {
        return new RightNow();
    }
}
