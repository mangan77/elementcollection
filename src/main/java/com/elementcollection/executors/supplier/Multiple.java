package com.elementcollection.executors.supplier;

import com.google.common.base.Supplier;
import org.openqa.selenium.WebElement;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

/**
 * <br> User: Mangan <br> Date: 19/11/13
 */
@ParametersAreNonnullByDefault
public class Multiple implements Supplier<List<WebElement>> {

    private final List<WebElement> webElements;

    Multiple(List<WebElement> webElements) {
        this.webElements = webElements;
    }

    @Override
    public List<WebElement> get() {
        return webElements;
    }
}
