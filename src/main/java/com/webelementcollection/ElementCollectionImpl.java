package com.webelementcollection;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static com.webelementcollection.SelectFunction.*;

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

    private ElementCollection getElementCollection(final String selectorString, final By by) {
        List<WebElement> newList = Lists.newArrayList();
        for (WebElement element : webElements) {
            newList.addAll(element.findElements(by));
        }
        return new ElementCollectionImpl(driver, selectorString, newList);
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

    private ElementCollection val(final String text, final SelectFunction selectFunction) {
        checkState(webElements.isEmpty(), "Trying to set text:\"" + text + "\" on empty webElements. Selector used \"" + selectorString + "\"");
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
        return element.getTagName() != null && "select".equals(element.getTagName().toLowerCase());
    }

    private boolean isCheckbox(final WebElement element) {
        return element.getTagName() != null &&
                "input".equals(element.getTagName().toLowerCase()) &&
                "checkbox".equals(element.getAttribute("type"));
    }

    private boolean isRadioButton(final WebElement element) {
        return element.getTagName() != null &&
                "input".equals(element.getTagName().toLowerCase()) &&
                "radio".equals(element.getAttribute("type"));
    }

    @Override
    public ElementCollection val(final int value) {
        return val(String.valueOf(value));
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

    private void setValue(WebElement element, String value) {
        if (element.isDisplayed()) {
            element.clear();
            element.sendKeys(value);
        }
    }

    private void setValue(WebElement element, boolean value) {
        if (element.isSelected() && !value || !element.isSelected() && value) {
            element.click();
        }
    }

}
