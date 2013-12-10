package com.elementcollection.context;

import com.google.common.base.Function;
import org.openqa.selenium.WebElement;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

/**
 * <br> User: Mangan <br> Date: 09/12/13
 */
@ParametersAreNonnullByDefault
class FindRightNow implements FindContext {

    @Override
    public List<WebElement> find(String cssSelector, Function<String, List<WebElement>> findFunction) {
        return findFunction.apply(cssSelector);
    }

}
