package com.elementcollection.util;

import com.elementcollection.element.Element;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * <br> User: Mangan <br> Date: 20/11/13
 */
@ParametersAreNonnullByDefault
public final class ElementUtil {

    private ElementUtil() {
    }

    public static boolean isCheckbox(final Element element) {
        return isInputOfType(element, "checkbox");
    }

    public static boolean isRadioButton(final Element element) {
        return isInputOfType(element, "radio");
    }

    public static boolean isSelectOption(Element element) {
        return isTag(element, "option");
    }

    public static boolean isSelectBox(final Element element) {
        return isTag(element, "select");
    }

    private static boolean isTag(Element element, String tagName) {
        return hasTagName(element) && tagNameIs(element, tagName);
    }

    private static boolean tagNameIs(Element element, String tagName) {
        return tagName.toLowerCase().equals(element.getTagName().toLowerCase());
    }

    private static boolean hasTagName(Element element) {
        return element.getTagName() != null;
    }

    private static boolean isInputOfType(Element element, String type) {
        return isTag(element, "input") && typeIs(element, type);
    }

    private static boolean typeIs(Element element, String type) {
        return type.equals(element.getAttribute("type"));
    }
}
