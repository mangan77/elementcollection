package com.elementcollection.element;

import java.util.List;

public interface Element {


    List<Element> findElements(String cssSelector);

    void click();

    void submit();

    String getText();

    /**
     * @param name Attribute name
     * @return The attribute/property's current value or null if the attribute or value is not set
     */
    String getAttribute(String name);

    boolean isSelected();

    boolean isDisplayed();

    String getTagName();

    void clear();

    void sendKeys(CharSequence... value);

    SelectElement asSelect();
}
