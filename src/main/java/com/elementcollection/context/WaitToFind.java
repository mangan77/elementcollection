package com.elementcollection.context;

import java.util.List;

import com.elementcollection.api.Element;
import com.elementcollection.api.TimeUnit;
import com.elementcollection.util.Function;

public class WaitToFind implements FindContext {
    private final TimeUnit delay;

    public WaitToFind(TimeUnit delay) {
        this.delay = delay;
    }

    @Override
    public List<Element> find(String cssSelector, Function<String, List<Element>> findFunction) {
        try {
            Thread.sleep(delay.inMilliseconds());
            return findFunction.apply(cssSelector);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
