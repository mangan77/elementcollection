package com.elementcollection;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

/**
 * <br> User: Mangan <br> Date: 09/12/13
 */
@ParametersAreNonnullByDefault
public class WebElementFindFunction implements Function<String, List<WebElement>> {
    private final List<WebElement> elements;

    public WebElementFindFunction(List<WebElement> elements) {
        this.elements = elements;
    }

    @Nullable
    @Override
    public List<WebElement> apply(@Nullable String input) {
        List<WebElement> newList = Lists.newArrayList();
        for (WebElement element : elements) {
            newList.addAll(element.findElements(By.cssSelector(input)));
        }
        return newList;

    }
}
