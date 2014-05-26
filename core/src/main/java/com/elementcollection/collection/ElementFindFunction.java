package com.elementcollection.collection;

import com.elementcollection.element.Element;
import com.google.common.base.Function;
import com.google.common.collect.Lists;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

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
        List<Element> newList = Lists.newArrayList();
        for (Element element : elements) {
            newList.addAll(element.findElements(input));
        }
        return newList;

    }
}
