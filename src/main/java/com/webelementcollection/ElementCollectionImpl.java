package com.webelementcollection;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.webelementcollection.functions.select.SelectByIndex;
import com.webelementcollection.functions.select.SelectByValue;
import com.webelementcollection.functions.select.SelectByVisibleText;
import com.webelementcollection.functions.select.SelectFunction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

import static com.google.common.base.Preconditions.*;

@ParametersAreNonnullByDefault
class ElementCollectionImpl implements ElementCollection {

    private final List<WebElement> webElements;
    private final WebDriver driver;
    private final String selectorString;

    ElementCollectionImpl(final WebDriver driver, @Nullable final String selectorString, final List<WebElement> webElements) {
        this.driver = checkNotNull(driver, "driver");
        this.webElements = checkNotNull(webElements, "webElements");
        this.selectorString = selectorString;
    }

    ElementCollectionImpl(final WebDriver driver, @Nullable final String selectorString, final WebElement... webElements) {
        this(driver, selectorString, Lists.newArrayList(checkNotNull(webElements)));
    }

    ElementCollectionImpl(final WebDriver driver, final WebElement... webElements) {
        this(driver, null, Lists.newArrayList(checkNotNull(webElements)));
    }

    @Override
    public ElementCollection find(String cssSelector) {
        return getElementCollection(cssSelector, By.cssSelector(cssSelector));
    }

    @Override
    public ElementCollection click() {
        checkState(!webElements.isEmpty(), "Trying to click on non existing element. Selector used \"" + selectorString + "\"");
        for (WebElement element : webElements) {
            element.click();
        }
        return this;
    }

    @Override
    public ElementCollection submit() {
        checkState(!webElements.isEmpty(), "Trying to submit a non existing element. Selector used \"" + selectorString + "\"");
        for (WebElement element : webElements) {
            element.submit();
        }
        return this;
    }

    @Override
    public ElementCollection get(final int index) {
        try {
            return new ElementCollectionImpl(driver, selectorString, webElements.get(index));
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalStateException("Element collection selected with \"" + selectorString + "\" returned null for index:" + index, e);
        }
    }

    @Override
    public ElementCollection first() {
        return get(0);
    }

    @Override
    public ElementCollection last() {
        return get(webElements.size() - 1);
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
        return this;
    }

    @Override
    public ElementCollection findByName(final String name) {
        return find("[name='" + name + "']");
    }

    @Override
    public ElementCollection findByXpath(final String xpath) {
        return getElementCollection(xpath, By.xpath(xpath));
    }

    @Override
    public List<ElementCollection> getElements() {
        List<ElementCollection> elements = Lists.newArrayList();
        for (WebElement webElement : webElements) {
            elements.add(new ElementCollectionImpl(driver, selectorString, webElement));
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

    private ElementCollection getElementCollection(final String selectorString, final By by) {
        List<WebElement> newList = Lists.newArrayList();
        for (WebElement element : webElements) {
            newList.addAll(element.findElements(by));
        }
        return new ElementCollectionImpl(driver, selectorString, newList);
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
        return this;
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
