package com.elementcollection.impl.context.function;

import com.elementcollection.impl.context.returnvalue.ValidatableReturnValue;
import com.elementcollection.impl.context.returnvalue.WebElementsReturnValue;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

/**
 * <br> User: Mangan <br> Date: 19/11/13
 */
@ParametersAreNonnullByDefault
public class Find implements Function<List<WebElement>, ValidatableReturnValue<List<WebElement>>> {
    private final String cssSelector;

    public Find(String cssSelector) {
        this.cssSelector = cssSelector;
    }

    @Nullable
    @Override
    public ValidatableReturnValue<List<WebElement>> apply(@Nullable List<WebElement> input) {
        List<WebElement> newList = Lists.newArrayList();
        for (WebElement element : input) {
            newList.addAll(element.findElements(By.cssSelector(cssSelector)));
        }
        return new WebElementsReturnValue(newList);
    }
}
