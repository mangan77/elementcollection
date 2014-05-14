package com.elementcollection.element;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
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

    public static List<Element> fromWebElements(List<WebElement> elements) {
        return transform(elements, new Function<WebElement, Element>() {

            @Nullable
            @Override
            public Element apply(@Nullable WebElement input) {
                return fromWebElement(input);
            }
        });
    }

    public static List<Element> fromWebElements(WebElement... webElements) {
        return fromWebElements(Lists.newArrayList(webElements));
    }

    public static List<WebElement> toWebElements(List<Element> elements) {
        return transform(elements, new Function<Element, WebElement>() {
            @Nullable
            @Override
            public WebElement apply(@Nullable Element input) {
                return (WebElement) input;
            }
        });
    }
}
