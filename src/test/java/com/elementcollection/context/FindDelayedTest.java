package com.elementcollection.context;

import com.elementcollection.api.Element;
import com.elementcollection.exception.ElementNotVisibleException;
import com.elementcollection.type.TimeUnits;
import com.google.common.base.Function;
import org.testng.annotations.Test;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Mockito.*;

public class FindDelayedTest {

    @Test
    public void WhenElementsAreFoundRightAwayThenElementsShouldBeReturnedRightAway() {
        Function<String, List<Element>> findFunc = new Function<String, List<Element>>() {
            @Nullable
            @Override
            public List<Element> apply(String input) {
                return elements();
            }
        };

        FindDelayed findDelayed = new FindDelayed(TimeUnits.millis(10));
        assertThat(findDelayed.find("selector", findFunc)).hasSize(2);
    }

    private List<Element> elements() {
        List<Element> elements = new ArrayList<>();
        elements.add(mock(Element.class));
        elements.add(mock(Element.class));
        return elements;
    }

    @Test
    public void WhenFindOnlyThrowsExceptionTheExceptionShouldBeRethrownAsRuntimeException() {
        Function<String, List<Element>> findFunc = spy(new Function<String, List<Element>>() {
            @Nullable
            @Override
            public List<Element> apply(@Nullable String input) {
                throw new ElementNotVisibleException();
            }
        });

        try {
            FindDelayed findDelayed = new FindDelayed(TimeUnits.millis(10));
            findDelayed.find("selector", findFunc);
            fail("find() should rethrow exception");
        } catch (Exception e) {
            verify(findFunc, atLeastOnce()).apply("selector");
            assertThat(e).isInstanceOf(RuntimeException.class);
        }
    }

    @Test
    public void WhenFindDoesNotFindAnythingAnEmptyListShouldBeReturned() {
        Function<String, List<Element>> function = new Function<String, List<Element>>() {
            @Nullable
            @Override
            public List<Element> apply(@Nullable String input) {
                return new ArrayList<>();
            }
        };

        FindDelayed findDelayed = new FindDelayed(TimeUnits.millis(10));
        assertThat(findDelayed.find("selector", function)).isEmpty();
    }

    @Test
    public void WhenFunctionThrowsBeforeReturningAListTheExceptionsShouldBeIgnoredAndTheListShouldBeReturned() {
        Function<String, List<Element>> function = spy(new Function<String, List<Element>>() {
            int callCount = 0;

            @Nullable
            @Override
            public List<Element> apply(@Nullable String input) {
                callCount++;
                if (callCount < 3) {
                    throw new RuntimeException("Nothing found yet");
                }

                return elements();
            }
        });

        FindDelayed findDelayed = new FindDelayed(TimeUnits.millis(10));
        assertThat(findDelayed.find("selector", function)).hasSize(2);
        verify(function, times(3)).apply("selector");
    }


}