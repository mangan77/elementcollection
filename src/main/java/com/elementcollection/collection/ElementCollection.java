package com.elementcollection.collection;


import com.elementcollection.finder.ElementCollectionFinder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
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
@ParametersAreNonnullByDefault
public interface ElementCollection extends ElementCollectionFinder {

    @Nonnull
    ElementCollection click();

    @Nonnull
    ElementCollection submit();

    @Nonnull
    ElementCollection get(int index);

    @Nonnull
    ElementCollection first();

    @Nonnull
    ElementCollection last();

    @Nonnull
    ElementCollection val(String value);

    @Nonnull
    ElementCollection val(boolean value);

    @Nonnull
    List<ElementCollection> getElements();

    @Nonnull
    ElementCollection val(int value);

    @Nullable
    String val();

    @Nullable
    String attr(String name);

    int length();

    @Nonnull
    ElementCollection valByIndex(int index);

    boolean isDisplayed();

    @Nonnull
    ElementCollection valByVisibleText(String text);

    boolean isEmpty();

    boolean hasElements();
}
