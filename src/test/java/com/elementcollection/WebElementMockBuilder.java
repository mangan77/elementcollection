package com.elementcollection;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * <br> User: Mangan <br> Date: 13/11/13
 */
@ParametersAreNonnullByDefault
public class WebElementMockBuilder {

    private final WebElement mock;

    public WebElementMockBuilder() {
        mock = mock(WebElement.class);
    }

    public WebElement build() {
        return mock;
    }

    public WebElementMockBuilder withTagName(String tagName) {
        when(mock.getTagName()).thenReturn(tagName);
        return this;
    }

    public WebElementMockBuilder withAttribute(String attribute, String value) {
        when(mock.getAttribute(attribute)).thenReturn(value);
        return this;
    }

    public WebElementMockBuilder setSelected(boolean isSelected) {
        when(mock.isSelected()).thenReturn(isSelected);
        return this;
    }

    public WebElementMockBuilder isDisplayed(boolean isDisplayed) {
        when(mock.isDisplayed()).thenReturn(isDisplayed);
        return this;
    }

    public WebElementMockBuilder withText(String text) {
        when(mock.getText()).thenReturn(text);
        return this;
    }

    public WebElementMockBuilder finds(List<WebElement> children, By by) {
        when(mock.findElements(by)).thenReturn(children);
        return this;
    }
}
