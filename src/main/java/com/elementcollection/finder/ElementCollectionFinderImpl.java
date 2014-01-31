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
    private final FindContext findContext;

    ElementCollectionFinderImpl(WebDriver webDriver) {
        this(webDriver, FindContexts.immediate());
    }

    private ElementCollectionFinderImpl(WebDriver webDriver, FindContext findContext) {
        this.webDriver = webDriver;
        this.findContext = findContext;
    }

    @Override
    public ElementCollection find(String cssSelector) {
        return ElementCollections.create(cssSelector, findElements(cssSelector));
    }

    private List<WebElement> findElements(String cssSelector) {
        return findContext.find(checkNotNull(cssSelector), new WebDriverFindFunction(webDriver));
    }

    @Override
    public ElementCollectionFinder within(TimeUnit delay) {
        return new ElementCollectionFinderImpl(webDriver, FindContexts.delayed(delay));
    }

    @Override
    public ElementCollectionFinder wait(TimeUnit delay) {
        return new ElementCollectionFinderImpl(webDriver, FindContexts.waiting(delay));
    }

}
