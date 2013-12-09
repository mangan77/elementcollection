package com.elementcollection;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.openqa.selenium.WebElement;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

/**
 * <br> User: Mangan <br> Date: 09/12/13
 */
@ParametersAreNonnullByDefault
public class Delayed implements FindContext {

    private final Function<String, List<WebElement>> findFunction;
    private final long endTime;

    public Delayed(Function<String, List<WebElement>> findFunction, int delayInMillis) {
        this.findFunction = findFunction;
        this.endTime = calculateEndTime(delayInMillis);
    }


    @Override
    public List<WebElement> find(String cssSelector) {
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

    private long calculateEndTime(int delayInMillis) {
        return System.currentTimeMillis() + delayInMillis;
    }

    private boolean shouldTryAgain(long endTime) {
        return endTime - System.currentTimeMillis() > 0;
    }

}
