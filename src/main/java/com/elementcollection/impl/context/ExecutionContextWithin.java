package com.elementcollection.impl.context;

import com.elementcollection.impl.context.returnvalue.ValidatableReturnValue;
import com.google.common.base.Function;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * <br> User: Mangan <br> Date: 19/11/13
 */
public class ExecutionContextWithin extends ExecutionContext {

    private final int withinSecs;

    public ExecutionContextWithin(int withinSecs) {
        super();
        this.withinSecs = withinSecs;
    }

    @Override
    public <T> T execute(Function<List<WebElement>, ValidatableReturnValue<T>> operation, List<WebElement> webElements) {
        long endMillis = System.currentTimeMillis() + withinSecs * 1000;
        RuntimeException exceptionCausingRetry = null;
        boolean shouldRetry = true;
        T retValue = null;
        while (shouldRetry && stillWithinTimeLimit(endMillis)) {
            try {
                ValidatableReturnValue<T> tmpRetVal = operation.apply(webElements);
                retValue = tmpRetVal.getReturnValue();
                shouldRetry = !tmpRetVal.isValid();
                if (tmpRetVal.isValid()) {
                    return retValue;
                }
            } catch (RuntimeException e) {
                exceptionCausingRetry = e;
            }
        }
        if (shouldRetry) {
            if (exceptionCausingRetry != null)
                throw exceptionCausingRetry;
        }
        return retValue;
    }

    private boolean stillWithinTimeLimit(long endMillis) {
        return endMillis - System.currentTimeMillis() > 0;
    }

}
