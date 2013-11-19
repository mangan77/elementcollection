package com.elementcollection.executors;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * <br> User: Mangan <br> Date: 19/11/13
 */
@ParametersAreNonnullByDefault
public class BooleanReturnValue implements ValidatableReturnValue<Boolean> {

    public BooleanReturnValue(boolean value) {
        this.value = value;
    }

    private final boolean value;

    @Override
    public Boolean getReturnValue() {
        return value;
    }

    @Override
    public boolean isValid() {
        return value;
    }
}
