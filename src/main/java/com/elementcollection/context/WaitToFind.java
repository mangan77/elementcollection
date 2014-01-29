package com.elementcollection.context;

import com.elementcollection.type.TimeUnit;
import com.google.common.base.Function;
import org.openqa.selenium.WebElement;

import java.util.List;

public class WaitToFind implements FindContext {
    private final TimeUnit delay;

    public WaitToFind(TimeUnit delay) {
        this.delay = delay;
    }

    @Override
    public List<WebElement> find(String cssSelector, Function<String, List<WebElement>> findFunction) {
        try {
            Thread.sleep(delay.inMilliseconds());
            return findFunction.apply(cssSelector);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
