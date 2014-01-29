package com.elementcollection.collection.spy;

public class MethodSpy {
    private long startTime;
    private long endTime;
    private String input;

    public void start() {
        startTime = System.currentTimeMillis();
    }

    public void input(String input) {
        this.input = input;
    }

    public void end() {
        endTime = System.currentTimeMillis();
    }

    public long getDuration() {
        return endTime - startTime;
    }
}
