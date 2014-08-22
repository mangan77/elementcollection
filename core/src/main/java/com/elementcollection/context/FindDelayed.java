package com.elementcollection.context;

import com.elementcollection.element.Element;
import com.elementcollection.type.TimeUnit;
import com.google.common.base.Function;
import com.google.common.collect.Lists;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

/**
 * <br> User: Mangan <br> Date: 09/12/13
 */
@ParametersAreNonnullByDefault
class FindDelayed implements FindContext {

    private final long endTime;

    FindDelayed(TimeUnit delay) {
        this.endTime = calculateEndTime(delay);
    }


    @Override
    public List<Element> find(String cssSelector, Function<String, List<Element>> findFunction) {
        RuntimeException thrownException = null;
        while (shouldTryAgain(endTime)) {
            try {
                List<Element> elements = findFunction.apply(cssSelector);
                if (elements.size() > 0) {
                    return elements;
                }
            } catch (Exception e) {
                thrownException = new RuntimeException(e);
            }
        }
        if (thrownException != null) throw thrownException;
        return Lists.newArrayList();
    }

    private long calculateEndTime(TimeUnit delay) {
        return System.currentTimeMillis() + delay.inMilliseconds();
    }

    private boolean shouldTryAgain(long endTime) {
        return endTime - System.currentTimeMillis() > 0;
    }

}
