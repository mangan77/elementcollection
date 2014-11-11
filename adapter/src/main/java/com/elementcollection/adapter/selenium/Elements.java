package com.elementcollection.adapter.selenium;

import com.elementcollection.adapter.util.Function;
import com.elementcollection.adapter.util.Lists;
import com.elementcollection.element.Element;
import org.openqa.selenium.WebElement;

import javax.annotation.Nullable;
import java.util.List;

public class Elements {

    private Elements() {

    }

    static Element fromWebElement(WebElement webElement) {
        return new WebElementWrapper(webElement);
    }

    public static List<Element> fromWebElements(List<WebElement> elements) {
        return Lists.applyFunction(elements, new Function<WebElement, Element>() {

            @Nullable
            @Override
            public Element apply(@Nullable WebElement input) {
                return fromWebElement(input);
            }
        });
    }

}
