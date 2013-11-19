package com.elementcollection.executors.supplier;

import com.google.common.base.Supplier;
import com.google.common.collect.Iterables;
import org.openqa.selenium.WebElement;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

/**
 * <br> User: Mangan <br> Date: 19/11/13
 */
@ParametersAreNonnullByDefault
public class Single implements Supplier<WebElement> {

    private final List<WebElement> webElements;

    Single(List<WebElement> webElements) {
        this.webElements = webElements;
    }

    @Override
    public WebElement get() {
        return Iterables.getFirst(webElements, null);
    }
}
