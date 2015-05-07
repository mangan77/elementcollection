package com.elementcollection.finder;

import com.elementcollection.collection.ElementCollection;
import com.elementcollection.collection.ElementCollections;
import com.elementcollection.context.FindContext;
import com.elementcollection.context.FindContexts;
import com.elementcollection.driver.Driver;
import com.elementcollection.element.Element;
import com.elementcollection.type.TimeUnit;
import com.google.common.base.Preconditions;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

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
        return findContext.find(Preconditions.checkNotNull(cssSelector), new DriverFindFunction(driver));
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
