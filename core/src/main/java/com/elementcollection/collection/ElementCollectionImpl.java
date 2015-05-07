package com.elementcollection.collection;

import com.elementcollection.collection.select.SetVal;
import com.elementcollection.context.FindContext;
import com.elementcollection.context.FindContexts;
import com.elementcollection.element.Element;
import com.elementcollection.finder.ElementCollectionFinder;
import com.elementcollection.type.TimeUnit;
import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

import static com.elementcollection.util.ElementUtil.*;

@ParametersAreNonnullByDefault
class ElementCollectionImpl implements ElementCollection {

    private final List<Element> elements;
    private final String selectorString;
    private FindContext findContext;

    ElementCollectionImpl(FindContext findContext, @Nullable String selectorString, List<Element> elements) {
        this.elements = Preconditions.checkNotNull(elements, "elements");
        this.selectorString = selectorString;
        this.findContext = findContext;
    }

    private ElementCollectionImpl(@Nullable String selectorString, Element... elements) {
        this(FindContexts.immediate(), selectorString, Lists.newArrayList(Preconditions.checkNotNull(elements)));
    }

    @Override
    public ElementCollection find(String cssSelector) {
        List<Element> foundElements = findContext.find(cssSelector, new ElementFindFunction(elements));
        return new ElementCollectionImpl(FindContexts.immediate(), cssSelector, foundElements);
    }

    @Override
    public ElementCollection click() {
        Preconditions.checkState(!elements.isEmpty(), "Trying to click on non existing element.");
        for (Element element : elements) {
            element.click();
        }
        return new ElementCollectionImpl(FindContexts.immediate(), selectorString, elements);
    }

    @Override
    public ElementCollection submit() {
        Preconditions.checkState(!elements.isEmpty(), "Trying to submit a non existing element.");
        for (Element element : elements) {
            element.submit();
        }
        return new ElementCollectionImpl(FindContexts.immediate(), selectorString, elements);
    }

    @Override
    public ElementCollection get(int index) {
        try {
            return new ElementCollectionImpl(selectorString, elements.get(index));
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalStateException("Element collection selected with \"" + selectorString + "\" returned null for index:" + index, e);
        }
    }

    @Override
    public ElementCollection first() {
        try {
            Preconditions.checkArgument(elements.size() > 0);
            return new ElementCollectionImpl(selectorString, Iterables.getFirst(elements, null));
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException("Element collection is empty", e);
        }
    }

    @Override
    public ElementCollection last() {
        try {
            Preconditions.checkArgument(elements.size() > 0);
            return new ElementCollectionImpl(selectorString, Iterables.getLast(elements));
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException("Element collection is empty", e);
        }
    }

    @Override
    public ElementCollection val(String value) {
        List<Element> elementsWithSetValue = SetVal.forValue(value).apply(elements);
        return new ElementCollectionImpl(FindContexts.immediate(), selectorString, elementsWithSetValue);
    }

    @Override
    public ElementCollection valByIndex(int index) {
        List<Element> elementsWithSetValue = SetVal.forIndex(index).apply(elements);
        return new ElementCollectionImpl(FindContexts.immediate(), selectorString, elementsWithSetValue);
    }

    @Override
    public ElementCollection valByVisibleText(String text) {
        List<Element> elementsWithSetValue = SetVal.forVisibleValue(text).apply(elements);
        return new ElementCollectionImpl(FindContexts.immediate(), selectorString, elementsWithSetValue);
    }

    @Override
    public String val() {
        Element first = Iterables.getFirst(elements, null);
        return first != null ? first.getText() : null;
    }

    @Override
    public String attr(String name) {
        Element element = Iterables.getFirst(elements, null);
        return element != null ? element.getAttribute(name) : null;
    }

    @Override
    public ElementCollection val(boolean value) {
        for (Element element : elements) {
            setValue(element, value);
        }
        return new ElementCollectionImpl(FindContexts.immediate(), selectorString, elements);
    }

    private void setValue(Element element, boolean value) {
        Preconditions.checkArgument(isCheckbox(element) || isRadioButton(element) || isSelectOption(element),
                "Boolean values can only be set to checkboxes, radio buttons and select options");

        if (isSelectedButShouldBeDeselected(element, value) || isNotSelectedButShouldBeSelected(element, value)) {
            element.click();
        }
    }

    private boolean isNotSelectedButShouldBeSelected(Element element, boolean value) {
        return !element.isSelected() && value;
    }

    private boolean isSelectedButShouldBeDeselected(Element element, boolean value) {
        return element.isSelected() && !value;
    }

    @Override
    public List<ElementCollection> getElements() {
        List<ElementCollection> elements = Lists.newArrayList();
        for (Element element : this.elements) {
            elements.add(new ElementCollectionImpl(FindContexts.immediate(), selectorString, Lists.newArrayList(element)));
        }
        return elements;
    }

    @Override
    public boolean isDisplayed() {
        if (elements.isEmpty()) {
            return false;
        }
        for (Element element : elements) {
            if (!element.isDisplayed()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int length() {
        return elements.size();
    }

    @Override
    public boolean isEmpty() {
        return length() == 0;
    }

    @Override
    public boolean isNotEmpty() {
        return length() > 0;
    }

    @Override
    public boolean hasElements() {
        return length() > 0;
    }

    @Override
    public ElementCollection val(int value) {
        return val(String.valueOf(value));
    }

    @Override
    public boolean hasClass(String cssClass) {
        if (isEmpty()) return false;

        for (Element element : elements) {
            String cssClasses = element.getAttribute("class");
            if (cssClasses == null || doesNotContainCssClass(cssClasses, cssClass)) {
                return false;
            }
        }
        return true;
    }

    private boolean doesNotContainCssClass(String cssClasses, String cssClass) {
        String[] splitCssClasses = StringUtils.split(cssClasses);
        for (String splitCssClass : splitCssClasses) {
            if (StringUtils.trimToEmpty(splitCssClass).equals(cssClass))
                return false;
        }
        return true;
    }

    @Override
    public ElementCollectionFinder within(TimeUnit timeUnit) {
        return new ElementCollectionImpl(FindContexts.delayed(timeUnit), selectorString, elements);
    }

    @Override
    public ElementCollectionFinder wait(TimeUnit delay) {
        return new ElementCollectionImpl(FindContexts.waiting(delay), selectorString, elements);
    }

    @Nonnull
    @Override
    public ElementCollectionFinder visibleWithin(TimeUnit delay) {
        return new ElementCollectionImpl(FindContexts.visibility(delay), selectorString, elements);
    }
}
