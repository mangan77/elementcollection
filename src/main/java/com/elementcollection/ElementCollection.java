package com.elementcollection;


import java.util.List;

/**
 * <p/>
 * <h5>Wrapper for Selenium WebElement</h5>
 * <p/>
 * <pre>
 * User: Mangan
 * Date: 6/29/12
 * </pre>
 */
public interface ElementCollection extends ElementCollectionFinder {

    ElementCollection click();

    ElementCollection submit();

    ElementCollection get(int index);

    ElementCollection first();

    ElementCollection last();

    ElementCollection val(String value);

    ElementCollection val(boolean value);

    List<ElementCollection> getElements();

    ElementCollection val(int value);

    String val();

    String attr(String name);

    int length();

    ElementCollection valByIndex(int index);

    boolean isDisplayed();

    ElementCollection valByVisibleText(String text);

}
