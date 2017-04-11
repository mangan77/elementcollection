package com.elementcollection.context;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.elementcollection.api.Element;
import com.elementcollection.api.TimeOut;
import com.elementcollection.util.Function;

/**
 * <br> User: Mangan <br> Date: 08/05/15
 */
@ParametersAreNonnullByDefault
class FindOrContinue implements FindContext {


    private final TimeOut timeOut;
    private final List<Element> previousElements;

    public FindOrContinue(TimeOut timeOut, List<Element> previousElements) {
        this.timeOut = timeOut;
        this.previousElements = previousElements;
    }

    @Nonnull
    @Override
    public List<Element> find(String cssSelector, Function<String, List<Element>> findFunction) {
        List<Element> elements;
        while (timeOut.notReached()) {
            try {
                elements = findFunction.apply(cssSelector);
                if (elements.size() > 0) {
                    return elements;
                }
            } catch (Exception allExceptions) {
                // Ignore
            }
        }
        return previousElements;
    }
}
