package com.elementcollection;

import com.elementcollection.element.Element;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * <br> User: Mangan <br> Date: 13/11/13
 */
@ParametersAreNonnullByDefault
public class ElementMockBuilder {

    private final Element mock;

    public ElementMockBuilder() {
        mock = mock(Element.class);
    }

    public Element build() {
        return mock;
    }

    public ElementMockBuilder withTagName(String tagName) {
        when(mock.getTagName()).thenReturn(tagName);
        return this;
    }

    public ElementMockBuilder withAttribute(String attribute, String value) {
        when(mock.getAttribute(attribute)).thenReturn(value);
        return this;
    }

    public ElementMockBuilder setSelected(boolean isSelected) {
        when(mock.isSelected()).thenReturn(isSelected);
        return this;
    }

    public ElementMockBuilder isDisplayed(boolean isDisplayed) {
        when(mock.isDisplayed()).thenReturn(isDisplayed);
        return this;
    }

    public ElementMockBuilder isDisplayedAfter(int secs) {
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

    public ElementMockBuilder finds(final List<Element> children, String cssSelector, int afterSecs) {
        final long whenToFind = System.currentTimeMillis() + afterSecs * 1000;
        when(mock.findElements(cssSelector)).thenAnswer(new Answer<List<Element>>() {
            @Override
            public List<Element> answer(InvocationOnMock invocation) throws Throwable {
                while (whenToFind - System.currentTimeMillis() > 0){
                    return Collections.emptyList();
                }
                return children;
            }
        });
        return this;
    }

    public ElementMockBuilder withText(String text) {
        when(mock.getText()).thenReturn(text);
        return this;
    }

    public ElementMockBuilder finds(List<Element> children, String cssSelector) {
        return finds(children, cssSelector, 0);
    }
}
