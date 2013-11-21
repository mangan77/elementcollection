package com.elementcollection.impl.context.returnvalue;

import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * <br> User: Mangan <br> Date: 19/11/13
 */
public class WebElementsReturnValue implements ValidatableReturnValue<List<WebElement>> {

    public WebElementsReturnValue(List<WebElement> webElements) {
        this.webElements = webElements;
    }

    private final List<WebElement> webElements;

    @Override
    public List<WebElement> getReturnValue() {
        return webElements;
    }

    @Override
    public boolean isValid() {
        return webElements != null && webElements.size() > 0;
    }
}
