package com.elementcollection.finder;

import com.elementcollection.collection.ElementCollection;
import com.elementcollection.collection.ElementCollections;
import com.elementcollection.context.FindContext;
import com.elementcollection.context.FindContexts;
import com.elementcollection.type.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

/**
 * <br> User: Mangan <br> Date: 09/12/13
 */
@ParametersAreNonnullByDefault
class ElementCollectionFinderImpl implements ElementCollectionFinder {

    private final WebDriver webDriver;
    private FindContext findContext;

    ElementCollectionFinderImpl(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.findContext = defaultFindContext();
    }

    @Override
    public ElementCollection find(String cssSelector) {
        final List<WebElement> webElements = findContext.find(cssSelector, new WebDriverFindFunction(webDriver));
        findContext = defaultFindContext();
        return ElementCollections.create(cssSelector, webElements);
    }

    @Override
    public ElementCollectionFinder within(TimeUnit delay) {
        this.findContext = FindContexts.delayed(delay);
        return this;
    }

    private FindContext defaultFindContext() {
        return FindContexts.immediate();
    }
}
