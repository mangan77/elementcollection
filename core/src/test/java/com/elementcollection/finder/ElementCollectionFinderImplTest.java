package com.elementcollection.finder;

import com.elementcollection.collection.ElementCollection;
import com.elementcollection.driver.Driver;
import com.elementcollection.element.Element;
import com.google.common.collect.Lists;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

import static com.elementcollection.type.TimeUnits.millis;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * <br> User: Mangan <br> Date: 09/12/13
 */
@Test(groups = "unit")
public class ElementCollectionFinderImplTest {

    public void Finding_Elements_That_Can_Be_Found_Without_Delay_Should_Return_The_Elements_At_Once() {
        final Element elementOne = mock(Element.class);
        final Element elementTwo = mock(Element.class);

        Answer<List<Element>> answer = new Answer<List<Element>>() {
            @Override
            public List<Element> answer(InvocationOnMock invocation) throws Throwable {
                return Lists.newArrayList(elementOne, elementTwo);
            }
        };

        ElementCollection elements = ElementCollectionFinders.create(driverThatAnswers(answer)).find("someCssSelector");
        assertThat(elements.length(), is(2));
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void Finding_Elements_That_Throw_Exception_Should_Throw_Exception() {
        Answer<List<Element>> answer = new Answer<List<Element>>() {
            @Override
            public List<Element> answer(InvocationOnMock invocation) throws Throwable {
                throw new NoSuchElementException("test");
            }
        };

        ElementCollectionFinders.create(driverThatAnswers(answer)).find("someCssSelector");
    }


    public void Finding_Elements_That_Are_Only_Findable_After_500_Milliseconds_Should_Return_Expected_Elements() {
        final Element elementOne = mock(Element.class);
        final Element elementTwo = mock(Element.class);
        final Driver webDriver =
                driverThatAnswers(delayedAnswer(getReturnTime(500),
                        Lists.newArrayList(elementOne, elementTwo)));

        ElementCollection elements = ElementCollectionFinders.create(webDriver).within(millis(550)).find("someCssSelector");
        assertThat(elements.length(), is(2));
    }

    public void Finding_Elements_Throw_Exception_The_First_3_Times_Should_Return_Expected_Elements() {
        final Element elementOne = mock(Element.class);
        final Element elementTwo = mock(Element.class);
        Answer<List<Element>> answer = new Answer<List<Element>>() {
            private int count = 0;

            @Override
            public List<Element> answer(InvocationOnMock invocation) throws Throwable {
                if (count < 3) {
                    count++;
                    throw new NoSuchElementException("test");
                }
                return Lists.newArrayList(elementOne, elementTwo);
            }
        };

        ElementCollection elements = ElementCollectionFinders.create(driverThatAnswers(answer)).within(millis(2500)).find("someCssSelector");
        assertThat(elements.length(), is(2));
    }

    private Driver driverThatAnswers(Answer<List<Element>> answer) {
        final Driver driver = mock(Driver.class);
        when(driver
                .findElements("someCssSelector"))
                .thenAnswer(answer);
        return driver;
    }

    private static long getReturnTime(int delayInMillis) {
        return System.currentTimeMillis() + delayInMillis;
    }

    private Answer<List<Element>> delayedAnswer(final long returnTime, final List<Element> elements) {
        return new Answer<List<Element>>() {
            @Override
            public List<Element> answer(InvocationOnMock invocation) throws Throwable {
                if (returnTime - System.currentTimeMillis() > 0) return Collections.emptyList();
                return elements;
            }
        };
    }

    private class NoSuchElementException extends Exception {
        private final String message;

        public NoSuchElementException(String message) {
            this.message = message;
        }
    }
}
