package com.elementcollection.finder;

import com.elementcollection.collection.ElementCollection;
import com.elementcollection.driver.Driver;
import com.elementcollection.type.TimeUnit;
import com.google.common.collect.Lists;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * <br> User: Mangan <br> Date: 09/12/13
 */
@Test(groups = "unit")
public class ElementCollectionFinderImplTest {

    public void testFindingElementsThatCanBeFoundWithoutDelayShouldReturnTheElementsAtOnce() {
        final WebElement webElementOne = mock(WebElement.class);
        final WebElement webElementTwo = mock(WebElement.class);

        Answer<List<WebElement>> answer = new Answer<List<WebElement>>() {
            @Override
            public List<WebElement> answer(InvocationOnMock invocation) throws Throwable {
                return Lists.newArrayList(webElementOne, webElementTwo);
            }
        };

        ElementCollection elements = ElementCollectionFinders.create(driverThatAnswers(answer)).find("someCssSelector");
        assertThat(elements.length(), is(2));
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void testFindingElementsThatThrowExceptionShouldThrowException() {
        Answer<List<WebElement>> answer = new Answer<List<WebElement>>() {
            @Override
            public List<WebElement> answer(InvocationOnMock invocation) throws Throwable {
                throw new NoSuchElementException("test");
            }
        };

        ElementCollectionFinders.create(driverThatAnswers(answer)).find("someCssSelector");
    }


    public void testFindingElementsThatAreOnlyFindableAfter500MillisecondsShouldReturnExpectedElements() {
        final WebElement webElementOne = mock(WebElement.class);
        final WebElement webElementTwo = mock(WebElement.class);
        final Driver webDriver =
                driverThatAnswers(delayedAnswer(getReturnTime(500),
                        Lists.newArrayList(webElementOne, webElementTwo)));

        ElementCollection elements = ElementCollectionFinders.create(webDriver).within(TimeUnit.millis(550)).find("someCssSelector");
        assertThat(elements.length(), is(2));
    }

    public void testFindingElementsThatAreOnlyFindableAfter2500MillisecondsShouldReturnExpectedElements() {
        final WebElement webElementOne = mock(WebElement.class);
        final WebElement webElementTwo = mock(WebElement.class);
        final Driver webDriver =
                driverThatAnswers(delayedAnswer(getReturnTime(2500),
                        Lists.newArrayList(webElementOne, webElementTwo)));

        ElementCollection elements = ElementCollectionFinders.create(webDriver).within(TimeUnit.secs(3)).find("someCssSelector");
        assertThat(elements.length(), is(2));
    }

    public void testFindingElementsThrowExceptionTheFirst3TimesShouldReturnExpectedElements() {
        final WebElement webElementOne = mock(WebElement.class);
        final WebElement webElementTwo = mock(WebElement.class);
        Answer<List<WebElement>> answer = new Answer<List<WebElement>>() {
            private int count = 0;

            @Override
            public List<WebElement> answer(InvocationOnMock invocation) throws Throwable {
                if (count < 3) {
                    count++;
                    throw new NoSuchElementException("test");
                }
                return Lists.newArrayList(webElementOne, webElementTwo);
            }
        };

        ElementCollection elements = ElementCollectionFinders.create(driverThatAnswers(answer)).within(TimeUnit.millis(2500)).find("someCssSelector");
        assertThat(elements.length(), is(2));
    }

    private Driver driverThatAnswers(Answer<List<WebElement>> answer) {
        final Driver driver = mock(Driver.class);
        when(driver
                .findElements("someCssSelector"))
                .thenAnswer(answer);
        return driver;
    }

    private static long getReturnTime(int delayInMillis) {
        return System.currentTimeMillis() + delayInMillis;
    }

    private Answer<List<WebElement>> delayedAnswer(final long returnTime, final List<WebElement> webElements) {
        return new Answer<List<WebElement>>() {
            @Override
            public List<WebElement> answer(InvocationOnMock invocation) throws Throwable {
                if (returnTime - System.currentTimeMillis() > 0) return Collections.emptyList();
                return webElements;
            }
        };
    }

}
