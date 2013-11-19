package com.elementcollection.executors.supplier;

import org.openqa.selenium.WebElement;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

/**
 * <br> User: Mangan <br> Date: 19/11/13
 */
@ParametersAreNonnullByDefault
public final class ElementSupplier {

    private ElementSupplier() {
    }

    public static Single single(List<WebElement> webElements) {
        return new Single(webElements);
    }

    public static Multiple multiple(List<WebElement> webElements) {
        return new Multiple(webElements);
    }
}
