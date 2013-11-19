package com.elementcollection.executors;

import com.google.common.base.Function;
import com.google.common.base.Supplier;

/**
 * <br> User: Mangan <br> Date: 19/11/13
 */
public class WithinExecutor extends Executor {

    private final int withinSecs;

    public WithinExecutor(int withinSecs) {
        super();
        this.withinSecs = withinSecs;
    }

    @Override
    public <F, T> T execute(Function<F, ValidatableReturnValue<T>> operation, Supplier<F> supplier) {
        long endMillis = System.currentTimeMillis() + withinSecs * 1000;
        RuntimeException exceptionCausingRetry = null;
        boolean shouldRetry = true;
        T retValue = null;
        while (shouldRetry && stillWithinTimeLimit(endMillis)) {
            try {
                ValidatableReturnValue<T> tmpRetVal = operation.apply(supplier.get());
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
