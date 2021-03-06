package com.elementcollection.context;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import com.elementcollection.api.Element;
import com.elementcollection.api.TimeUnit;
import com.elementcollection.exception.ElementNotVisibleException;
import com.elementcollection.util.Function;

public class WaitForVisibility implements FindContext {

    private final long endTime;

    public WaitForVisibility(TimeUnit delay) {
        this.endTime = calculateEndTime(delay);
    }

    @Nonnull
    @Override
    public List<Element> find(String cssSelector, Function<String, List<Element>> findFunction) {
        List<Element> elements = new ArrayList<>();
        while (shouldTryAgain(endTime)) {
            elements = findFunction.apply(cssSelector);
            if (elements.size() > 0 && allElementsAreVisible(elements)) {
                return elements;
            }
        }

        if (elements.isEmpty()) return elements;

        throw new ElementNotVisibleException();
    }

    private boolean allElementsAreVisible(List<Element> elements) {
        for (Element element : elements) {
            if (!element.isDisplayed()) {
                return false;
            }
        }
        return true;
    }

    private long calculateEndTime(TimeUnit delay) {
        return System.currentTimeMillis() + delay.inMilliseconds();
    }

    private boolean shouldTryAgain(long endTime) {
        return endTime - System.currentTimeMillis() > 0;
    }

}
