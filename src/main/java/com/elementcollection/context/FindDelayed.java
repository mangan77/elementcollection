package com.elementcollection.context;

import java.util.List;

import javax.annotation.ParametersAreNonnullByDefault;

import com.elementcollection.api.Element;
import com.elementcollection.api.TimeUnit;
import com.elementcollection.util.Function;
import com.elementcollection.util.Lists;

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
        return Lists.newList();
    }

    private long calculateEndTime(TimeUnit delay) {
        return System.currentTimeMillis() + delay.inMilliseconds();
    }

    private boolean shouldTryAgain(long endTime) {
        return endTime - System.currentTimeMillis() > 0;
    }

}
