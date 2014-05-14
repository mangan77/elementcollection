package com.elementcollection.element;

import com.google.common.base.Function;
import org.openqa.selenium.WebElement;

import javax.annotation.Nullable;
import java.util.List;

import static com.google.common.collect.Lists.transform;

public class Elements {

    private Elements() {

    }

    static Element fromWebElement(WebElement webElement) {
        return new WebElementWrapper(webElement);
    }

    static List<Element> fromWebElements(List<WebElement> elements) {
        return transform(elements, new Function<WebElement, Element>() {

            @Nullable
            @Override
            public Element apply(@Nullable WebElement input) {
                return fromWebElement(input);
            }
        });
    }
}
