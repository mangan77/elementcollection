package com.elementcollection.executors;

/**
 * <br> User: Mangan <br> Date: 19/11/13
 */
public class StringReturnValue implements ValidatableReturnValue<String> {

    public StringReturnValue(String returnValue) {
        this.returnValue = returnValue;
    }

    private final String returnValue;

    @Override
    public String getReturnValue() {
        return returnValue;
    }

    @Override
    public boolean isValid() {
        return returnValue != null;
    }
}
