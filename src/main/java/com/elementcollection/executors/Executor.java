package com.elementcollection.executors;

import com.google.common.base.Function;
import com.google.common.base.Supplier;

/**
 * <br> User: Mangan <br> Date: 19/11/13
 */
public class Executor {

    public <F, T> T execute(Function<F, ValidatableReturnValue<T>> operation, Supplier<F> supplier) {
        return operation.apply(supplier.get()).getReturnValue();
    }

}
