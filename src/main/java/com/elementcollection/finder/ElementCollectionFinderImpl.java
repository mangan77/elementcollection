package com.elementcollection.finder;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.elementcollection.api.Driver;
import com.elementcollection.api.Element;
import com.elementcollection.api.ElementCollection;
import com.elementcollection.api.ElementCollectionFinder;
import com.elementcollection.api.TimeUnit;
import com.elementcollection.collection.ElementCollections;
import com.elementcollection.context.FindContext;
import com.elementcollection.context.FindContexts;
import com.elementcollection.util.Checks;

/**
 * <br> User: Mangan <br> Date: 09/12/13
 */
@ParametersAreNonnullByDefault
class ElementCollectionFinderImpl implements ElementCollectionFinder {

    private final Driver driver;
    private final FindContext findContext;

    ElementCollectionFinderImpl(Driver driver) {
        this(driver, FindContexts.immediate());
    }

    private ElementCollectionFinderImpl(Driver driver, FindContext findContext) {
        this.driver = driver;
        this.findContext = findContext;
    }

    @Override
    public ElementCollection find(String cssSelector) {
        return ElementCollections.create(cssSelector, findElements(cssSelector));
    }

    private List<Element> findElements(String cssSelector) {
        return findContext.find(Checks.checkNotNull(cssSelector), new DriverFindFunction(driver));
    }

    @Override
    public ElementCollectionFinder within(TimeUnit delay) {
        return new ElementCollectionFinderImpl(driver, FindContexts.delayed(delay));
    }

    @Override
    public ElementCollectionFinder wait(TimeUnit delay) {
        return new ElementCollectionFinderImpl(driver, FindContexts.waiting(delay));
    }

    @Nonnull
    @Override
    public ElementCollectionFinder visibleWithin(TimeUnit delay) {
        return new ElementCollectionFinderImpl(driver, FindContexts.visibility(delay));
    }
}
