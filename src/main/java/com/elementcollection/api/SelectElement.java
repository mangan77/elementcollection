package com.elementcollection.api;

public interface SelectElement {
    void selectByVisibleText(String text);

    void selectByValue(String value);

    void selectByIndex(int index);
}
