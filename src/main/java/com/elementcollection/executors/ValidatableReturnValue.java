package com.elementcollection.executors;

/**
 * <pre>
 * User: Mangan
 * Date: 19/11/13
 * </pre>
 */
public interface ValidatableReturnValue<T> {

    T getReturnValue();

    boolean isValid();


}
