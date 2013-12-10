package com.elementcollection.context;

import com.elementcollection.type.TimeUnit;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.openqa.selenium.WebElement;

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
    public List<WebElement> find(String cssSelector, Function<String, List<WebElement>> findFunction) {
        RuntimeException thrownException = null;
        while (shouldTryAgain(endTime)) {
            try {
                final List<WebElement> elements = findFunction.apply(cssSelector);
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