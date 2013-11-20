package com.elementcollection;

import com.elementcollection.executors.Executor;
import com.elementcollection.executors.ValidatableReturnValue;
import com.elementcollection.executors.WithinExecutor;
import com.elementcollection.executors.functions.*;
import com.elementcollection.executors.supplier.ElementSupplier;
import com.elementcollection.functions.select.SelectByIndex;
import com.elementcollection.functions.select.SelectByValue;
import com.elementcollection.functions.select.SelectByVisibleText;
import com.elementcollection.functions.select.SelectFunction;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.openqa.selenium.WebElement;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

import static com.google.common.base.Preconditions.*;

@ParametersAreNonnullByDefault
public class ElementCollectionImpl implements ElementCollection {

    private final List<WebElement> webElements;
    private final String selectorString;

    private Executor executor;

    private ElementCollectionImpl(@Nullable String selectorString, Executor executor, List<WebElement> webElements) {
        this.webElements = checkNotNull(webElements, "webElements");
        this.selectorString = selectorString;
        this.executor = executor;
    }

    public ElementCollectionImpl(@Nullable final String selectorString, final List<WebElement> webElements) {
        this(selectorString, new Executor(), webElements);
    }

    public ElementCollectionImpl(@Nullable final String selectorString, final WebElement... webElements) {
        this(selectorString, Lists.newArrayList(checkNotNull(webElements)));
    }

    public ElementCollectionImpl(final WebElement... webElements) {
        this(null, Lists.newArrayList(checkNotNull(webElements)));
    }

    @Override
    public ElementCollection find(String cssSelector) {
        return new ElementCollectionImpl(cssSelector, new Executor(), executeWithMultiple(new Find(cssSelector)));
    }

    private List<WebElement> executeWithMultiple(Function<List<WebElement>, ValidatableReturnValue<List<WebElement>>> operation) {
        return executor.execute(operation, ElementSupplier.multiple(webElements));
    }

    @Override
    public ElementCollection click() {
        return new ElementCollectionImpl(selectorString, new Executor(), executeWithMultiple(new Click()));
    }

    @Override
    public ElementCollection submit() {
        return new ElementCollectionImpl(selectorString, new Executor(), executeWithMultiple(new Submit()));
    }

    @Override
    public ElementCollection get(final int index) {
        return new ElementCollectionImpl(selectorString, new Executor(), executeWithMultiple(new Get(index, selectorString)));
    }

    @Override
    public ElementCollection first() {
        return new ElementCollectionImpl(selectorString, new Executor(), executeWithMultiple(new First()));
    }

    @Override
    public ElementCollection last() {
        return new ElementCollectionImpl(selectorString, new Executor(), executeWithMultiple(new Last()));
    }

    @Override
    public ElementCollection val(final String value) {
        return val(value, new SelectByValue(value));
    }

    @Override
    public ElementCollection valByIndex(final int index) {
        return val(String.valueOf(index), new SelectByIndex(index));
    }

    @Override
    public ElementCollection valByVisibleText(final String text) {
        return val(text, new SelectByVisibleText(text));
    }

    @Override
    public String val() {
        return executor.execute(new GetVal(), ElementSupplier.multiple(webElements));
    }

    @Override
    public String attr(final String name) {
        return executor.execute(new Attr(name), ElementSupplier.multiple(webElements));
    }

    @Override
    public ElementCollection val(final boolean value) {
        for (WebElement element : webElements) {
            setValue(element, value);
        }
        return new ElementCollectionImpl(selectorString, new Executor(), webElements);
    }

    @Override
    public List<ElementCollection> getElements() {
        List<ElementCollection> elements = Lists.newArrayList();
        for (WebElement webElement : webElements) {
            elements.add(new ElementCollectionImpl(selectorString, new Executor(), Lists.newArrayList(webElement)));
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

    private ElementCollection val(final String text, final SelectFunction selectFunction) {
        checkState(webElements.size() > 0, "Trying to set text:\"" + text + "\" on empty webElements. Selector used \"" + selectorString + "\"");
        for (WebElement element : webElements) {
            if (isSelectBox(element)) {
                selectFunction.apply(element);
            } else {
                setValue(element, text);
            }
        }
        return new ElementCollectionImpl(selectorString, new Executor(), webElements);
    }

    private boolean isSelectBox(final WebElement element) {
        return isTag(element, "select");
    }

    private boolean isTag(WebElement element, String tagName) {
        return hasTagName(element) && tagNameIs(element, tagName);
    }

    private boolean tagNameIs(WebElement element, String tagName) {
        return tagName.toLowerCase().equals(element.getTagName().toLowerCase());
    }

    private boolean hasTagName(WebElement element) {
        return element.getTagName() != null;
    }

    private boolean isCheckbox(final WebElement element) {
        return isInputOfType(element, "checkbox");
    }

    private boolean isInputOfType(WebElement element, String type) {
        return isTag(element, "input") && typeIs(element, type);
    }

    private boolean typeIs(WebElement element, String type) {
        return type.equals(element.getAttribute("type"));
    }

    private boolean isRadioButton(final WebElement element) {
        return isInputOfType(element, "radio");
    }

    @Override
    public ElementCollection val(final int value) {
        return val(String.valueOf(value));
    }

    @Override
    public ElementCollection within(int secs) {
        return new ElementCollectionImpl(selectorString, new WithinExecutor(secs), webElements);
    }

    private void setValue(WebElement element, String value) {
        if (element.isDisplayed()) {
            element.clear();
            element.sendKeys(value);
        }
    }

    private void setValue(WebElement element, boolean value) {
        checkArgument(isCheckbox(element) || isRadioButton(element) || isSelectOption(element),
                "Boolean values can only be set to checkboxes, radio buttons and select options");

        if (isSelectedButShouldBeDeselected(element, value) || isNotSelectedButShouldBeSelected(element, value)) {
            element.click();
        }
    }

    private boolean isSelectOption(WebElement element) {
        return isTag(element, "option");
    }

    private boolean isNotSelectedButShouldBeSelected(WebElement element, boolean value) {
        return !element.isSelected() && value;
    }

    private boolean isSelectedButShouldBeDeselected(WebElement element, boolean value) {
        return element.isSelected() && !value;
    }

}
