package com.elementcollection.collection;

import com.elementcollection.context.FindContexts;
import com.elementcollection.element.Element;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

/**
 * <br> User: Mangan <br> Date: 21/11/13
 */
@ParametersAreNonnullByDefault
public final class ElementCollections {

    private ElementCollections() {
    }

    @Nonnull
    public static ElementCollection create(String cssSelector, List<Element> webElements) {
        return new ElementCollectionImpl(FindContexts.immediate(), cssSelector, webElements);
    }
}
