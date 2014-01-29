package com.elementcollection.collection;

import com.elementcollection.context.FindContexts;
import org.openqa.selenium.WebElement;

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
    public static ElementCollection create(List<WebElement> webElements) {
        return create(null, webElements);
    }

    @Nonnull
    public static ElementCollection create(String cssSelector, List<WebElement> webElements) {
        return new ElementCollectionImpl(FindContexts.immediate(), cssSelector, webElements);
    }
}
