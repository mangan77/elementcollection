package com.elementcollection.impl.context;

import com.elementcollection.TimeUnit;
import com.elementcollection.impl.context.returnvalue.ValidatableReturnValue;
import com.google.common.base.Function;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * <br> User: Mangan <br> Date: 19/11/13
 */
public class ExecutionContextWithin extends ExecutionContext {

    private final TimeUnit timeUnit;

    public ExecutionContextWithin(TimeUnit timeUnit) {
        super();
        this.timeUnit = timeUnit;
    }

    @Override
    public <T> T execute(Function<List<WebElement>, ValidatableReturnValue<T>> operation, List<WebElement> webElements) {
        long endMillis = endMilliseconds();
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
        if (shouldRetry && exceptionCausingRetry != null) {
            throw exceptionCausingRetry;
        }
        return retValue;
    }

    private long endMilliseconds() {
        return System.currentTimeMillis() + timeUnit.inMilliseconds();
    }

    private boolean stillWithinTimeLimit(long endMillis) {
        return endMillis - System.currentTimeMillis() > 0;
    }

}
