package com.elementcollection;

import com.elementcollection.impl.ElementCollectionImpl;
import com.google.common.collect.Lists;
import org.openqa.selenium.WebElement;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

/**
 * <br> User: Mangan <br> Date: 21/11/13
 */
@ParametersAreNonnullByDefault
public final class ElementCollectionFactory {

    private ElementCollectionFactory() {
    }

    public static ElementCollection create(WebElement... webElements) {
        return create(Lists.newArrayList(webElements));
    }

    public static ElementCollection create(List<WebElement> webElements) {
        return create(null, webElements);
    }

    public static ElementCollection create(String cssSelector, List<WebElement> webElements) {
        return new ElementCollectionImpl(cssSelector, webElements);
    }
}
