package com.elementcollection;

import com.elementcollection.element.Element;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * <br> User: Mangan <br> Date: 13/11/13
 */
@ParametersAreNonnullByDefault
public class WebElementMockBuilder {

    private final Element mock;

    public WebElementMockBuilder() {
        mock = mock(Element.class);
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

    public WebElementMockBuilder isDisplayedAfter(int secs) {
        final long whenToDisplay = System.currentTimeMillis() + secs * 1000;
        when(mock.isDisplayed()).thenAnswer(new Answer<Boolean>() {
            @Override
            public Boolean answer(InvocationOnMock invocation) throws Throwable {
                while (whenToDisplay - System.currentTimeMillis() > 0){
                    return false;
                }
                return true;
            }
        });
        return this;
    }

    public WebElementMockBuilder finds(final List<WebElement> children, By by, int afterSecs){
        final long whenToFind = System.currentTimeMillis() + afterSecs * 1000;
        when(mock.findElements(by)).thenAnswer(new Answer<List<WebElement>>() {
            @Override
            public List<WebElement> answer(InvocationOnMock invocation) throws Throwable {
                while (whenToFind - System.currentTimeMillis() > 0){
                    return Collections.emptyList();
                }
                return children;
            }
        });
        return this;
    }

    public WebElementMockBuilder withText(String text) {
        when(mock.getText()).thenReturn(text);
        return this;
    }

    public WebElementMockBuilder finds(List<WebElement> children, By by) {
        return finds(children, by, 0);
    }
}
