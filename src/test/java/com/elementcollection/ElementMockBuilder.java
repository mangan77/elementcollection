package com.elementcollection;

import com.elementcollection.api.Element;
import com.elementcollection.api.SelectElement;
import com.elementcollection.api.TimeUnit;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Collections;
import java.util.List;

import static com.elementcollection.type.TimeUnits.millis;
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
        when(mock.asSelect()).thenReturn(new SelectElementMock(mock));
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

    public ElementMockBuilder isDisplayedAfter(TimeUnit delay) {
        final long whenToDisplay = System.currentTimeMillis() + delay.inMilliseconds();
        when(mock.isDisplayed()).thenAnswer(new Answer<Boolean>() {
            @Override
            public Boolean answer(InvocationOnMock invocation) throws Throwable {
                while (whenToDisplay - System.currentTimeMillis() > 0) {
                    return false;
                }
                return true;
            }
        });
        return this;
    }

    public ElementMockBuilder finds(final List<Element> children, String cssSelector, TimeUnit delay) {
        final long whenToFind = System.currentTimeMillis() + delay.inMilliseconds();
        when(mock.findElements(cssSelector)).thenAnswer(new Answer<List<Element>>() {
            @Override
            public List<Element> answer(InvocationOnMock invocation) throws Throwable {
                while (whenToFind - System.currentTimeMillis() > 0) {
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
        return finds(children, cssSelector, millis(0));
    }

    private class SelectElementMock implements SelectElement {
        @ParametersAreNonnullByDefault
        private final Element element;

        public SelectElementMock(Element element) {
            this.element = element;
        }

        @Override
        public void selectByVisibleText(String text) {
            List<Element> options = getOptions();
            for (Element option : options) {
                String optionText = option.getText();
                if (optionText != null && text.equals(optionText)) {
                    option.click();
                    if (isSingleSelect()) {
                        return;
                    }
                }
            }
        }

        @Override
        public void selectByValue(String value) {
            List<Element> options = getOptions();
            for (Element option : options) {
                String optionValue = option.getAttribute("value");
                if (optionValue != null && value.equals(optionValue)) {
                    option.click();
                    if (isSingleSelect()) {
                        return;
                    }
                }
            }
        }

        @Override
        public void selectByIndex(int index) {
            String indexAsString = "" + index;
            List<Element> options = getOptions();
            for (Element option : options) {
                String optionIndex = option.getAttribute("index");
                if (optionIndex != null && indexAsString.equals(optionIndex)) {
                    option.click();
                    return;
                }
            }
        }

        private List<Element> getOptions() {
            return element.findElements("option");
        }

        private boolean isSingleSelect() {
            return element.getAttribute("multiple") == null || element.getAttribute("multiple").equals("false");
        }

    }
}
