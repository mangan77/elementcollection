package com.elementcollection.util;

import org.openqa.selenium.WebElement;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * <br> User: Mangan <br> Date: 20/11/13
 */
@ParametersAreNonnullByDefault
public final class ElementUtil {

    private ElementUtil() {
    }

    public static boolean isCheckbox(final WebElement element) {
        return isInputOfType(element, "checkbox");
    }

    public static boolean isRadioButton(final WebElement element) {
        return isInputOfType(element, "radio");
    }

    public static boolean isSelectOption(WebElement element) {
        return isTag(element, "option");
    }

    public static boolean isSelectBox(final WebElement element) {
        return isTag(element, "select");
    }

    private static boolean isTag(WebElement element, String tagName) {
        return hasTagName(element) && tagNameIs(element, tagName);
    }

    private static boolean tagNameIs(WebElement element, String tagName) {
        return tagName.toLowerCase().equals(element.getTagName().toLowerCase());
    }

    private static boolean hasTagName(WebElement element) {
        return element.getTagName() != null;
    }

    private static boolean isInputOfType(WebElement element, String type) {
        return isTag(element, "input") && typeIs(element, type);
    }

    private static boolean typeIs(WebElement element, String type) {
        return type.equals(element.getAttribute("type"));
    }
}
