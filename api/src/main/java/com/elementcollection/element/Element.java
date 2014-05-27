package com.elementcollection.element;

import java.util.List;

public interface Element {


    List<Element> findElements(String cssSelector);

    void click();

    void submit();

    String getText();

    String getAttribute(String name);

    boolean isSelected();

    boolean isDisplayed();

    String getTagName();

    void clear();

    void sendKeys(CharSequence... value);

    SelectElement asSelect();
}
