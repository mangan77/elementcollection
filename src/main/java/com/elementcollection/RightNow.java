package com.elementcollection;

import com.google.common.base.Function;
import org.openqa.selenium.WebElement;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

/**
 * <br> User: Mangan <br> Date: 09/12/13
 */
@ParametersAreNonnullByDefault
public class RightNow implements FindContext {

    private final Function<String, List<WebElement>> findFunction;

    public RightNow(Function<String, List<WebElement>> findFunction) {
        this.findFunction = findFunction;
    }

    @Override
    public List<WebElement> find(String cssSelector) {
        return findFunction.apply(cssSelector);
    }

}
