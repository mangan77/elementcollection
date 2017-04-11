package com.elementcollection.collection;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import com.elementcollection.api.Element;
import com.elementcollection.util.Function;

/**
 * <br> User: Mangan <br> Date: 09/12/13
 */
@ParametersAreNonnullByDefault
class ElementFindFunction implements Function<String, List<Element>> {
    private final List<Element> elements;

    ElementFindFunction(List<Element> elements) {
        this.elements = elements;
    }

    @Nullable
    @Override
    public List<Element> apply(@Nullable String input) {
        List<Element> newList = new ArrayList<>();
        for (Element element : elements) {
            newList.addAll(element.findElements(input));
        }
        return newList;

    }
}
