package com.elementcollection.context;

import com.elementcollection.ElementMockBuilder;
import com.elementcollection.api.Element;
import com.elementcollection.api.TimeOut;
import com.elementcollection.util.Function;

import org.testng.annotations.Test;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <br> User: Mangan <br> Date: 08/05/15
 */
@Test(groups = "unit")
public class FindOrContinueTest {

    @Test
    public void When_Finding_Elements_That_Will_Not_Be_Found_Then_Find_Should_Return_Previous_Elements() {
        Function<String, List<Element>> findFunctionThatThrows = findFunctionThatThrows();

        Element expectedElement = new ElementMockBuilder().withAttribute("id", "previousElement1").build();
        List<Element> expectedElements = createElementList(expectedElement);

        List<Element> elements = new FindOrContinue(get100MillisTimeOut(), expectedElements).find("selector", findFunctionThatThrows);

        assertThat(elements).hasSameSizeAs(expectedElements);
        Element foundElement = elements.get(0);
        assertThat(foundElement.getAttribute("id")).isEqualTo(expectedElement.getAttribute("id"));
    }

    @Test
    public void When_Finding_Elements_Right_Away_Then_Find_Should_Return_Found_Elements() {
        Element expectedElement1 = new ElementMockBuilder().withAttribute("id", "foundElement1").build();
        Element expectedElement2 = new ElementMockBuilder().withAttribute("id", "foundElement2").build();
        final List<Element> expectedElements = createElementList(expectedElement1, expectedElement2);

        Function<String, List<Element>> findFunctionThatFinds = new Function<String, List<Element>>() {
            @Nullable
            @Override
            public List<Element> apply(@Nullable String input) {
                return expectedElements;
            }
        };

        Element previousElement = new ElementMockBuilder().withAttribute("id", "previousElement1").build();
        final List<Element> previousElements = createElementList(previousElement);

        List<Element> elements = new FindOrContinue(get100MillisTimeOut(), previousElements).find("selector", findFunctionThatFinds);

        assertThat(elements).hasSameSizeAs(expectedElements);
        assertThat(elements.get(0).getAttribute("id")).isEqualTo(expectedElement1.getAttribute("id"));
        assertThat(elements.get(1).getAttribute("id")).isEqualTo(expectedElement2.getAttribute("id"));

    }

    @Test
    public void When_Finding_Elements_After_Three_Tries_Then_Find_Should_Return_Found_Elements() {
        Element expectedElement1 = new ElementMockBuilder().withAttribute("id", "foundElement1").build();
        Element expectedElement2 = new ElementMockBuilder().withAttribute("id", "foundElement2").build();
        final List<Element> expectedElements = createElementList(expectedElement1, expectedElement2);

        Function<String, List<Element>> findFunctionThatFindsAfterThreeTries = new Function<String, List<Element>>() {

            private int triesCounter = 0;

            @Nullable
            @Override
            public List<Element> apply(@Nullable String input) {
                if (triesCounter++ < 3) {
                    return Collections.emptyList();
                }
                return expectedElements;
            }
        };

        Element previousElement = new ElementMockBuilder().withAttribute("id", "previousElement1").build();
        final List<Element> previousElements = createElementList(previousElement);

        List<Element> elements = new FindOrContinue(get100MillisTimeOut(), previousElements).find("selector", findFunctionThatFindsAfterThreeTries);

        assertThat(elements).hasSameSizeAs(expectedElements);
        assertThat(elements.get(0).getAttribute("id")).isEqualTo(expectedElement1.getAttribute("id"));
        assertThat(elements.get(1).getAttribute("id")).isEqualTo(expectedElement2.getAttribute("id"));

    }

    @Test
    public void When_Finding_Elements_That_Are_Not_Found_Before_Time_Out_Then_Find_Should_Return_Previous_Elements() {
        Element unexpectedElement1 = new ElementMockBuilder().withAttribute("id", "unexpectedElement1").build();
        Element unexpectedElement2 = new ElementMockBuilder().withAttribute("id", "unexpectedElement2").build();
        final List<Element> unexpectedElements = createElementList(unexpectedElement1, unexpectedElement2);

        Function<String, List<Element>> findFunctionThatFindsAfter100Tries = new Function<String, List<Element>>() {

            private int triesCounter = 0;

            @Nullable
            @Override
            public List<Element> apply(@Nullable String input) {
                if (triesCounter++ < 100) {
                    return Collections.emptyList();
                }
                return unexpectedElements;
            }
        };

        Element previousElement = new ElementMockBuilder().withAttribute("id", "previousElement1").build();
        final List<Element> previousElements = createElementList(previousElement);

        CountingTimeOut timeOut = new CountingTimeOut();
        List<Element> elements = new FindOrContinue(timeOut, previousElements).find("selector", findFunctionThatFindsAfter100Tries);

        assertThat(elements).hasSameSizeAs(previousElements);
        assertThat(elements.get(0).getAttribute("id")).isEqualTo(previousElement.getAttribute("id"));
        assertThat(timeOut.timeOutIsReached()).isTrue();
    }

    private TimeOut get100MillisTimeOut() {
        return new TimeOut() {

            private int currentTime = 0;

            @Override
            public boolean notReached() {
                return currentTime++ < 100;
            }
        };
    }


    private List<Element> createElementList(Element... elements) {
        return Arrays.asList(elements);
    }

    private Function<String, List<Element>> findFunctionThatThrows() {
        return new Function<String, List<Element>>() {

            @Nullable
            @Override
            public List<Element> apply(String input) {
                throw new RuntimeException("Element not found");
            }
        };
    }

    private static class CountingTimeOut implements TimeOut {

        public boolean timeOutIsReached() {
            return numberOfCalls >= 100;
        }

        private int numberOfCalls = 0;

        @Override
        public boolean notReached() {
            return numberOfCalls++ < 100;
        }


    }

}
