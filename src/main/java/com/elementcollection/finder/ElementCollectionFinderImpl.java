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

import static com.google.common.base.Preconditions.checkNotNull;

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
        findContext = defaultFindContext();
        return ElementCollections.create(cssSelector, findElements(cssSelector));
    }

    private List<WebElement> findElements(String cssSelector) {
        return findContext.find(checkNotNull(cssSelector), new WebDriverFindFunction(webDriver));
    }

    @Override
    public ElementCollectionFinder within(TimeUnit delay) {
        this.findContext = FindContexts.delayed(delay);
        return this;
    }

    @Override
    public ElementCollectionFinder wait(TimeUnit delay) {
        findContext = FindContexts.waiting(delay);
        return this;
    }

    private FindContext defaultFindContext() {
        return FindContexts.immediate();
    }
}
