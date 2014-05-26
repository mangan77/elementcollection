package com.elementcollection.collection.spy;

public abstract class MethodExecutor<T> {
    private final String methodName;

    public MethodExecutor(String methodName) {
        this.methodName = methodName;
    }

    public abstract T execute();

    public String getMethodName() {
        return methodName;
    }
}
