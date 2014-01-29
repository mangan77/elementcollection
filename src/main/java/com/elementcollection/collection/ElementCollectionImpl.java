package com.elementcollection.collection;

import com.elementcollection.collection.select.SetVal;
import com.elementcollection.context.FindContext;
import com.elementcollection.context.FindContexts;
import com.elementcollection.finder.ElementCollectionFinder;
import com.elementcollection.type.TimeUnit;
import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.openqa.selenium.WebElement;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

import static com.elementcollection.util.ElementUtil.*;
import static com.google.common.base.Preconditions.*;

@ParametersAreNonnullByDefault
class ElementCollectionImpl implements ElementCollection {

    private final List<WebElement> webElements;
    private final String selectorString;
    private FindContext findContext;

    ElementCollectionImpl(FindContext findContext, @Nullable String selectorString, List<WebElement> webElements) {
        this.webElements = checkNotNull(webElements, "webElements");
        this.selectorString = selectorString;
        this.findContext = findContext;
    }

    private ElementCollectionImpl(@Nullable final String selectorString, final WebElement... webElements) {
        this(FindContexts.immediate(), selectorString, Lists.newArrayList(checkNotNull(webElements)));
    }

    @Override
    public ElementCollection find(String cssSelector) {
        final List<WebElement> foundElements = findContext.find(cssSelector, new WebElementFindFunction(webElements));
        return new ElementCollectionImpl(FindContexts.immediate(), cssSelector, foundElements);
    }

    @Override
    public ElementCollection click() {
        checkState(!webElements.isEmpty(), "Trying to click on non existing element.");
        for (WebElement element : webElements) {
            element.click();
        }
        return new ElementCollectionImpl(FindContexts.immediate(), selectorString, webElements);
    }

    @Override
    public ElementCollection submit() {
        checkState(!webElements.isEmpty(), "Trying to submit a non existing element.");
        for (WebElement element : webElements) {
            element.submit();
        }
        return new ElementCollectionImpl(FindContexts.immediate(), selectorString, webElements);
    }

    @Override
    public ElementCollection get(final int index) {
        try {
            return new ElementCollectionImpl(selectorString, webElements.get(index));
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalStateException("Element collection selected with \"" + selectorString + "\" returned null for index:" + index, e);
        }
    }

    @Override
    public ElementCollection first() {
        try {
            Preconditions.checkArgument(webElements.size() > 0);
            return new ElementCollectionImpl(selectorString, Iterables.getFirst(webElements, null));
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException("Element collection is empty", e);
        }
    }

    @Override
    public ElementCollection last() {
        try {
            Preconditions.checkArgument(webElements.size() > 0);
            return new ElementCollectionImpl(selectorString, Iterables.getLast(webElements));
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException("Element collection is empty", e);
        }
    }

    @Override
    public ElementCollection val(final String value) {
        final List<WebElement> elementsWithSetValue = SetVal.forValue(value).apply(webElements);
        return new ElementCollectionImpl(FindContexts.immediate(), selectorString, elementsWithSetValue);
    }

    @Override
    public ElementCollection valByIndex(final int index) {
        final List<WebElement> elementsWithSetValue = SetVal.forIndex(index).apply(webElements);
        return new ElementCollectionImpl(FindContexts.immediate(), selectorString, elementsWithSetValue);
    }

    @Override
    public ElementCollection valByVisibleText(final String text) {
        final List<WebElement> elementsWithSetValue = SetVal.forVisibleValue(text).apply(webElements);
        return new ElementCollectionImpl(FindContexts.immediate(), selectorString, elementsWithSetValue);
    }

    @Override
    public String val() {
        final WebElement webElement = Iterables.getFirst(webElements, null);
        return webElement != null ? webElement.getText() : null;
    }

    @Override
    public String attr(final String name) {
        final WebElement webElement = Iterables.getFirst(webElements, null);
        return webElement != null ? webElement.getAttribute(name) : null;
    }

    @Override
    public ElementCollection val(final boolean value) {
        for (WebElement element : webElements) {
            setValue(element, value);
        }
        return new ElementCollectionImpl(FindContexts.immediate(), selectorString, webElements);
    }

    private void setValue(WebElement element, boolean value) {
        checkArgument(isCheckbox(element) || isRadioButton(element) || isSelectOption(element),
                "Boolean values can only be set to checkboxes, radio buttons and select options");

        if (isSelectedButShouldBeDeselected(element, value) || isNotSelectedButShouldBeSelected(element, value)) {
            element.click();
        }
    }

    private boolean isNotSelectedButShouldBeSelected(WebElement element, boolean value) {
        return !element.isSelected() && value;
    }

    private boolean isSelectedButShouldBeDeselected(WebElement element, boolean value) {
        return element.isSelected() && !value;
    }

    @Override
    public List<ElementCollection> getElements() {
        List<ElementCollection> elements = Lists.newArrayList();
        for (WebElement webElement : webElements) {
            elements.add(new ElementCollectionImpl(FindContexts.immediate(), selectorString, Lists.newArrayList(webElement)));
        }
        return elements;
    }

    @Override
    public boolean isDisplayed() {
        if (webElements.isEmpty()) {
            return false;
        }
        for (WebElement webElement : webElements) {
            if (!webElement.isDisplayed()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int length() {
        return webElements.size();
    }

    @Override
    public ElementCollection val(final int value) {
        return val(String.valueOf(value));
    }

    @Override
    public ElementCollectionFinder within(TimeUnit timeUnit) {
        return new ElementCollectionImpl(FindContexts.delayed(timeUnit), selectorString, webElements);
    }

    @Override
    public ElementCollectionFinder wait(TimeUnit delay) {
        return new ElementCollectionImpl(FindContexts.waiting(delay), selectorString, webElements);
    }
}
