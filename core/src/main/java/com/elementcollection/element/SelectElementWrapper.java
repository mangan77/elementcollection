package com.elementcollection.element;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SelectElementWrapper implements SelectElement {
    private final Select select;

    public SelectElementWrapper(WebElement webElement) {
        this.select = new Select(webElement);
    }


    @Override
    public void selectByVisibleText(String text) {
        select.selectByVisibleText(text);
    }

    @Override
    public void selectByValue(String value) {
        select.selectByValue(value);
    }

    @Override
    public void selectByIndex(int index) {
        select.selectByIndex(index);
    }
}
