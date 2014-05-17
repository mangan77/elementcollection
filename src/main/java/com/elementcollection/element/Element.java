package com.elementcollection.element;

import org.openqa.selenium.WebElement;

import java.util.List;

public interface Element extends WebElement {


    List<Element> findElements(String cssSelector);

    SelectElement asSelect();
}
